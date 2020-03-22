package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.UnitOfMeasureDto;
import guru.spring.course.recipe.models.UnitOfMeasureModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-02-29
 * @project recipe
 */
@Component
public class UnitOfMeasureModelToUnitOfMeasureDto implements Converter<UnitOfMeasureModel, UnitOfMeasureDto> {

    @Synchronized
//    @Nullable
    @Override
    public UnitOfMeasureDto convert(UnitOfMeasureModel unitOfMeasureModel) {

        if (unitOfMeasureModel == null) {
            throw new IllegalArgumentException("unit of measure is null");
//            return null;
        }

        final UnitOfMeasureDto measureModel = new UnitOfMeasureDto();
        measureModel.setId(unitOfMeasureModel.getId());
        measureModel.setDescription(unitOfMeasureModel.getDescription());
        return measureModel;
    }
}
