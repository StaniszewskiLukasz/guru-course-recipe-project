package guru.spring.course.recipe.models;

import guru.spring.course.recipe.dto.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Łukasz Staniszewski on 2020-02-28
 * @project recipe
 */
@Setter
@Getter
@NoArgsConstructor
public class RecipeModel {

    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientModel> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private NotesModel notes;
    private Set<CategoryModel> categories = new HashSet<>();
}
