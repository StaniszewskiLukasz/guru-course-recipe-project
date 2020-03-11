package guru.spring.course.recipe.service;

import guru.spring.course.recipe.dto.IngredientDto;

/**
 * @author Łukasz Staniszewski on 2020-03-09
 * @project recipe
 */
public interface IngredientService {

    IngredientDto findByRecipeIdAndIngredientId();

}
