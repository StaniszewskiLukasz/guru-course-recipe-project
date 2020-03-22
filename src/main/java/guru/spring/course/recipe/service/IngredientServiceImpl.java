package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.IngredientDtoToIngredientModel;
import guru.spring.course.recipe.converters.IngredientModelToIngredientDto;
import guru.spring.course.recipe.dto.IngredientDto;
import guru.spring.course.recipe.models.IngredientModel;
import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.repositories.RecipeRepository;
import guru.spring.course.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Łukasz Staniszewski on 2020-03-09
 * @project recipe
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientModelToIngredientDto modelConverter;
    private final IngredientDtoToIngredientModel dtoConverter;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientModelToIngredientDto modelConverter,
                                 IngredientDtoToIngredientModel dtoConverter,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.modelConverter = modelConverter;
        this.dtoConverter = dtoConverter;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    public IngredientDto findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<IngredientDto> ingredientDto = null;
        Optional<RecipeModel> recipeModel = recipeRepository.findById(recipeId);
        try {
            RecipeModel model = recipeModel.orElseThrow(IllegalAccessException::new);
            ingredientDto = model.getIngredientModels().stream()
                    .filter(e -> e.getId().equals(ingredientId))
                    .map(modelConverter::convert).findFirst();
        } catch (IllegalAccessException e) {
            log.error("Recipe model was null");
        }
        if (!ingredientDto.isPresent()) {
            log.error("Ingredient was not found");
        }
        return ingredientDto.get();
    }

    @Override
    @Transactional
    public IngredientDto saveIngredient(IngredientDto ingredientDto) {

        Optional<RecipeModel> recipeOptional = recipeRepository.findById(ingredientDto.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + ingredientDto.getRecipeId());
            return new IngredientDto();
        } else {
            RecipeModel recipe = recipeOptional.get();

            Optional<IngredientModel> ingredientOptional = recipe
                    .getIngredientModels()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientDto.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                IngredientModel ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredientDto.getDescription());
                ingredientFound.setAmount(ingredientDto.getAmount());
                ingredientFound.setUnitOfMeasureModel(unitOfMeasureRepository
                        .findById(ingredientDto.getUnitOfMeasure().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                IngredientModel ingredient = dtoConverter.convert(ingredientDto);
                ingredient.setRecipeModel(recipe);
                recipe.addIngredient(ingredient);
            }

            RecipeModel savedRecipe = recipeRepository.save(recipe);

            Optional<IngredientModel> savedIngredientOptional = savedRecipe.getIngredientModels().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientDto.getId()))
                    .findFirst();

            if(!savedIngredientOptional.isPresent()){

                savedIngredientOptional = savedRecipe.getIngredientModels().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientDto.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientDto.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasureModel().getId().equals(ingredientDto.getUnitOfMeasure().getId()))
                        .findFirst();
            }

            //to do check for fail
            return modelConverter.convert(savedIngredientOptional.get());

        }
    }

    @Override
    public void deleteIngredientByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<RecipeModel> recipeOptional = recipeRepository.findById(recipeId);
        if(!recipeOptional.isPresent()){
            log.info("zrobić obslugę błędów");
        }else{
            RecipeModel recipeModel = recipeOptional.get();
            Optional<IngredientModel> ingredientmodelToDelete = recipeOptional.get().getIngredientModels()
                    .stream()
                    .filter(e -> e.getId().equals(ingredientId))
                    .findFirst();

            if(ingredientmodelToDelete.isPresent()){
                IngredientModel ingredientModel = ingredientmodelToDelete.get();
                ingredientModel.setRecipeModel(null);
                recipeModel.getIngredientModels().remove(ingredientModel);
                recipeRepository.save(recipeModel);
            }else{
                log.info("Recipe id not found");
            }
        }
    }
}
