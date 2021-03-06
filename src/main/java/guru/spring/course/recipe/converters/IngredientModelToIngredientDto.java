package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.IngredientDto;
import guru.spring.course.recipe.models.IngredientModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-02-29
 * @project recipe
 */
@Component
public class IngredientModelToIngredientDto implements Converter<IngredientModel, IngredientDto> {

    private final UnitOfMeasureModelToUnitOfMeasureDto measureModel;

    public IngredientModelToIngredientDto(UnitOfMeasureModelToUnitOfMeasureDto measureModel) {
        this.measureModel = measureModel;
    }

    @Synchronized
//    @Nullable
    @Override
    public IngredientDto convert(IngredientModel ingredientModel) {

        if(ingredientModel==null){
//            return null;
            throw new IllegalArgumentException("IngredientModel is null");
        }

        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(ingredientModel.getId());
        if(ingredientModel.getRecipeModel()!=null){
            ingredientDto.setRecipeId(ingredientModel.getRecipeModel().getId());
        }
        ingredientDto.setAmount(ingredientModel.getAmount());
        ingredientDto.setDescription(ingredientModel.getDescription());
        if(ingredientModel.getUnitOfMeasureModel()!=null){
            ingredientDto.setUnitOfMeasure(measureModel.convert(ingredientModel.getUnitOfMeasureModel()));
        }
        return ingredientDto;
    }
}
