package guru.spring.course.recipe.service;

import guru.spring.course.recipe.dto.RecipeDto;

import java.util.Set;

/**
 * @author Łukasz Staniszewski on 2020-02-13
 * @project recipe
 */
public interface RecipeService {
    Set<RecipeDto> getRecipes();

    RecipeDto getRecipeById(Long id);

    RecipeDto saveRecipe(RecipeDto recipe);

    void deleteRecipeById(Long id);
}
