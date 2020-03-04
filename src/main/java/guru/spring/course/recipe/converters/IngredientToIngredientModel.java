package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.Ingredient;
import guru.spring.course.recipe.models.IngredientModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-02-29
 * @project recipe
 */
@Component
public class IngredientToIngredientModel implements Converter<Ingredient, IngredientModel> {

    private final UnitOfMeasureToUnitOfMeasureModel measureModel;

    public IngredientToIngredientModel(UnitOfMeasureToUnitOfMeasureModel measureModel) {
        this.measureModel = measureModel;
    }

    @Synchronized
    @Override
    public IngredientModel convert(Ingredient ingredient) {

        if(ingredient==null){
            throw new IllegalArgumentException("Ingredient can not be null");
        }

        IngredientModel model = new IngredientModel();
        model.setId(ingredient.getId());
        model.setDescription(ingredient.getDescription());
        model.setAmount(ingredient.getAmount());
        model.setUnitOfMeasure(measureModel.convert(ingredient.getUnitOfMeasure()));
        return model;
    }
}
