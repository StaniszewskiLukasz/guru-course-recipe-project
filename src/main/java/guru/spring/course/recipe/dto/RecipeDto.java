package guru.spring.course.recipe.dto;

import guru.spring.course.recipe.models.DifficultyModel;
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
public class RecipeDto {

    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Byte[] image;
    private Set<IngredientDto> ingredients = new HashSet<>();
    private DifficultyModel difficultyModel;
    private NotesDto notes;
    private Set<CategoryDto> categories = new HashSet<>();
}
