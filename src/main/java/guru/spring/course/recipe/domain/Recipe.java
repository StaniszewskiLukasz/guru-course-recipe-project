package guru.spring.course.recipe.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Łukasz Staniszewski on 2020-02-11
 * @project recipe
 */
@Data
@Entity
public class Recipe {

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
    private Difficulty difficulty;
    //large object powyżej 256 znaków tutaj się przyda do zapisnia zdjęcia

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",joinColumns = @JoinColumn(name = "recipe_id"),inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

//    private void hbsd(){
//        Iterator<Category> iterator = getCategories().iterator();
//        if (iterator.hasNext()){
//            iterator.next()
//        }
//    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }
}
