package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.IngredientModelToIngredientDto;
import guru.spring.course.recipe.dto.IngredientDto;
import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Łukasz Staniszewski on 2020-03-09
 * @project recipe
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientModelToIngredientDto converter;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientModelToIngredientDto converter) {
        this.recipeRepository = recipeRepository;
        this.converter = converter;
    }


    @Override
    public IngredientDto findByRecipeIdAndIngredientId(Long ingredientId, Long recipeId) {
        Optional<IngredientDto> ingredientDto = null;
        Optional<RecipeModel> recipeModel = recipeRepository.findById(recipeId);
        try {
            RecipeModel model = recipeModel.orElseThrow(IllegalAccessException::new);
            ingredientDto = model.getIngredientModels().stream()
                    .filter(e -> e.getId().equals(ingredientId))
                    .map(converter::convert).findFirst();
        } catch (IllegalAccessException e) {
            log.error("Recipe model was null");
        }
        if (!ingredientDto.isPresent()) {
            log.error("Ingredient was not found");
        }
        return ingredientDto.get();
    }
}
