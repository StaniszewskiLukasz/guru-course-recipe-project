package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.UnitOfMeasureModelToUnitOfMeasureDto;
import guru.spring.course.recipe.dto.UnitOfMeasureDto;
import guru.spring.course.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Łukasz Staniszewski on 2020-03-16
 * @project recipe
 */
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    UnitOfMeasureRepository unitOfMeasureRepository;
    UnitOfMeasureModelToUnitOfMeasureDto converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureModelToUnitOfMeasureDto converter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureDto> listAllUnitsOfMeasure() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
                .map(uom->converter.convert(uom))
                .collect(Collectors.toSet());
    }
}
