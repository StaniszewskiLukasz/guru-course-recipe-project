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
    @Override
    public IngredientDto convert(IngredientModel ingredientModel) {

        if(ingredientModel ==null){
            throw new IllegalArgumentException("Ingredient can not be null");
        }

        IngredientDto model = new IngredientDto();
        model.setId(ingredientModel.getId());
        model.setDescription(ingredientModel.getDescription());
        model.setAmount(ingredientModel.getAmount());
        model.setUnitOfMeasure(measureModel.convert(ingredientModel.getUnitOfMeasureModel()));
        return model;
    }
}
