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

    private final IngredientModelToIngredientDto ingredientModelConverter;
    private final IngredientDtoToIngredientModel ingredientDtoConverter;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientModelToIngredientDto ingredientModelConverter,
                                 IngredientDtoToIngredientModel ingredientDtoConverter,
                                 RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientModelConverter = ingredientModelConverter;
        this.ingredientDtoConverter = ingredientDtoConverter;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientDto findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<RecipeModel> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        RecipeModel recipe = recipeOptional.get();

        Optional<IngredientDto> ingredientCommandOptional = recipe.getIngredientModels().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientModelConverter::convert).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientDto saveIngredientDto(IngredientDto command) {
        Optional<RecipeModel> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientDto();
        } else {
            RecipeModel recipe = recipeOptional.get();

            Optional<IngredientModel> ingredientOptional = recipe
                    .getIngredientModels()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                IngredientModel ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUnitOfMeasureModel(unitOfMeasureRepository
                        .findById(command.getUnitOfMeasure().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                IngredientModel ingredient = ingredientDtoConverter.convert(command);
                ingredient.setRecipeModel(recipe);
                recipe.addIngredient(ingredient);
            }

            RecipeModel savedRecipe = recipeRepository.save(recipe);

            Optional<IngredientModel> savedIngredientOptional = savedRecipe.getIngredientModels().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if(!savedIngredientOptional.isPresent()){
                //not totally safe... But best guess
                savedIngredientOptional = savedRecipe.getIngredientModels().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasureModel().getId().equals(command.getUnitOfMeasure().getId()))
                        .findFirst();
            }

            //to do check for fail
            return ingredientModelConverter.convert(savedIngredientOptional.get());
        }

    }

    @Override
    public void deleteIngredientByRecipeIdAndIngredientId(Long recipeId, Long idToDelete) {

        log.debug("Deleting ingredient: " + recipeId + ":" + idToDelete);

        Optional<RecipeModel> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent()){
            RecipeModel recipe = recipeOptional.get();
            log.debug("found recipe");

            Optional<IngredientModel> ingredientOptional = recipe
                    .getIngredientModels()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(idToDelete))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                log.debug("found Ingredient");
                IngredientModel ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipeModel(null);
                recipe.getIngredientModels().remove(ingredientOptional.get());
                recipeRepository.save(recipe);
            }
        } else {
            log.debug("Recipe Id Not found. Id:" + recipeId);
        }
    }
}
