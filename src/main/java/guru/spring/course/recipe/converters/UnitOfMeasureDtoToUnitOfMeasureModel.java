package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.UnitOfMeasureDto;
import guru.spring.course.recipe.models.UnitOfMeasureModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-02-28
 * @project recipe
 */
@Component
public class UnitOfMeasureDtoToUnitOfMeasureModel implements Converter<UnitOfMeasureDto, UnitOfMeasureModel> {

    @Synchronized
    @Override
    public UnitOfMeasureModel convert(UnitOfMeasureDto measureModel) {
        if(measureModel==null){
            throw new RuntimeException("Unit Of Measure doesn't exist");
        }
        final UnitOfMeasureModel unitOfMeasureModel = new UnitOfMeasureModel();
        unitOfMeasureModel.setId(measureModel.getId());
        unitOfMeasureModel.setDescription(measureModel.getDescription());
        return unitOfMeasureModel;
    }
}
