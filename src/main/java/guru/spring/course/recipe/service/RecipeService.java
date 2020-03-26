package guru.spring.course.recipe.service;

import guru.spring.course.recipe.dto.RecipeDto;
import guru.spring.course.recipe.models.RecipeModel;

import java.util.List;
import java.util.Set;

/**
 * @author Łukasz Staniszewski on 2020-02-13
 * @project recipe
 */
public interface RecipeService {
    Set<RecipeModel> getRecipes();

    RecipeModel findById(Long id);

    RecipeDto findCommandById(Long id);

    RecipeDto saveRecipeCommand(RecipeDto recipeDto);

    void deleteById(Long idToDelete);
}
