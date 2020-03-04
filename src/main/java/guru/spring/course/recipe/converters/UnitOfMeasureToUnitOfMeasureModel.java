package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.UnitOfMeasure;
import guru.spring.course.recipe.models.UnitOfMeasureModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-02-29
 * @project recipe
 */
@Component
public class UnitOfMeasureToUnitOfMeasureModel implements Converter<UnitOfMeasure, UnitOfMeasureModel> {

    @Synchronized
    @Override
    public UnitOfMeasureModel convert(UnitOfMeasure unitOfMeasure) {
        if(unitOfMeasure==null){
            throw new IllegalArgumentException("unit of measure is null");
        }

        UnitOfMeasureModel measureModel = new UnitOfMeasureModel();
        measureModel.setId(unitOfMeasure.getId());
        measureModel.setDescription(unitOfMeasure.getDescription());
        return measureModel;
    }
}
