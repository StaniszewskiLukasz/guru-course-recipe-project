package guru.spring.course.recipe.service;

import guru.spring.course.recipe.domain.Recipe;

import java.util.Set;

/**
 * @author Łukasz Staniszewski on 2020-02-13
 * @project recipe
 */
public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe getRecipeById(Long id);
}
