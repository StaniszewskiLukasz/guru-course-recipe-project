package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.IngredientDto;
import guru.spring.course.recipe.models.IngredientModel;
import lombok.Synchronized;
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

    @Synchronized
    @Override
    public IngredientModel convert(IngredientDto model) {
        if(model==null){
            throw new IllegalArgumentException("IngredientModel is null");
        }

        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setId(model.getId());
        ingredientModel.setAmount(model.getAmount());
        ingredientModel.setDescription(model.getDescription());
        ingredientModel.setUnitOfMeasureModel(measureModel.convert(model.getUnitOfMeasure()));
        return ingredientModel;
    }
}
