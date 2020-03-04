package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.Ingredient;
import guru.spring.course.recipe.models.IngredientModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-03-01
 * @project recipe
 */
@Component
public class IngredientModelToIngredient implements Converter<IngredientModel, Ingredient> {

    private final UnitOfMeasureModelToUnitOfMeasure measureModel;

    public IngredientModelToIngredient(UnitOfMeasureModelToUnitOfMeasure measureModel) {
        this.measureModel = measureModel;
    }

    @Synchronized
    @Override
    public Ingredient convert(IngredientModel model) {
        if(model==null){
            throw new IllegalArgumentException("IngredientModel is null");
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setId(model.getId());
        ingredient.setAmount(model.getAmount());
        ingredient.setDescription(model.getDescription());
        ingredient.setUnitOfMeasure(measureModel.convert(model.getUnitOfMeasure()));
        return ingredient;
    }
}
