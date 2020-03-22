package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.IngredientDto;
import guru.spring.course.recipe.models.IngredientModel;
import guru.spring.course.recipe.models.RecipeModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-03-01
 * @project recipe
 */
@Component
public class IngredientDtoToIngredientModel implements Converter<IngredientDto, IngredientModel> {

    private final UnitOfMeasureDtoToUnitOfMeasureModel measureModel;

    public IngredientDtoToIngredientModel(UnitOfMeasureDtoToUnitOfMeasureModel measureModel) {
        this.measureModel = measureModel;
    }

//    @Nullable
    @Override
    public IngredientModel convert(IngredientDto ingredientDto) {
        if(ingredientDto==null){
//            return null;
            throw new IllegalArgumentException("IngredientDto is null");
        }

        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setId(ingredientDto.getId());
        if(ingredientDto.getRecipeId()!=null){
            RecipeModel recipeModel = new RecipeModel();
            recipeModel.setId(ingredientDto.getRecipeId());
            ingredientModel.setRecipeModel(recipeModel);
            recipeModel.addIngredient(ingredientModel);
        }
        ingredientModel.setAmount(ingredientDto.getAmount());
        ingredientModel.setDescription(ingredientDto.getDescription());
        ingredientModel.setUnitOfMeasureModel(measureModel.convert(ingredientDto.getUnitOfMeasure()));
        return ingredientModel;
    }
}
