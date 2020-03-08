package guru.spring.course.recipe.service;

import guru.spring.course.recipe.dto.Recipe;
import guru.spring.course.recipe.models.RecipeModel;

import java.util.Set;

/**
 * @author Łukasz Staniszewski on 2020-02-13
 * @project recipe
 */
public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe getRecipeById(Long id);

    RecipeModel saveRecipeModel(RecipeModel model);

    RecipeModel findRecipeModelById(Long id);

    void deleteRecipeById(Long id);
}
