package guru.spring.course.recipe.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Łukasz Staniszewski on 2020-02-11
 * @project recipe
 */
@Getter
@Setter
@Entity
public class RecipeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    //te typ domyślny to ordinal i on wtedy numeruje ENUMY jeśli jakiś dodamy to ich numery się zmienią i baza się posypie a string to string i poprzez equals można je porównać
    @Enumerated(value = EnumType.STRING)
    private DifficultyModel difficultyModel;
    //large object powyżej 256 znaków tutaj się przyda do zapisnia zdjęcia

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipeModel")
    private Set<IngredientModel> ingredientModels = new HashSet<>();

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private NotesModel notesModel;

    @ManyToMany
    @JoinTable(name = "recipe_category",joinColumns = @JoinColumn(name = "recipe_id"),inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryModel> categories = new HashSet<>();

    public RecipeModel addIngredient(IngredientModel ingredientModel){
        ingredientModel.setRecipeModel(this);
        this.ingredientModels.add(ingredientModel);
        return this;
    }

    public void setNotesModel(NotesModel notesModel) {
        this.notesModel = notesModel;
        notesModel.setRecipeModel(this);
    }
}
