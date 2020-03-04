package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.UnitOfMeasure;
import guru.spring.course.recipe.models.UnitOfMeasureModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-02-28
 * @project recipe
 */
@Component
public class UnitOfMeasureModelToUnitOfMeasure implements Converter<UnitOfMeasureModel, UnitOfMeasure> {

    @Synchronized
    @Override
    public UnitOfMeasure convert(UnitOfMeasureModel measureModel) {
        if(measureModel==null){
            throw new RuntimeException("Unit Of Measure doesn't exist");
        }
        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(measureModel.getId());
        unitOfMeasure.setDescription(measureModel.getDescription());
        return unitOfMeasure;
    }
}
