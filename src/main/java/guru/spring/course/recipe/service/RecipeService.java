package guru.spring.course.recipe.service;

import guru.spring.course.recipe.dto.RecipeDto;
import guru.spring.course.recipe.models.RecipeModel;

import java.util.Set;

/**
 * @author Łukasz Staniszewski on 2020-02-13
 * @project recipe
 */
public interface RecipeService {
    Set<RecipeModel> getRecipes();

    RecipeDto getRecipeById(Long id);

    RecipeDto saveRecipe(RecipeDto recipe);

    RecipeModel findRecipeModelById(Long id);

    void deleteRecipeById(Long id);
}
