package guru.spring.course.recipe.service;

import guru.spring.course.recipe.dto.UnitOfMeasureDto;

import java.util.Set;

/**
 * @author Łukasz Staniszewski on 2020-03-16
 * @project recipe
 */
public interface UnitOfMeasureService {

    Set<UnitOfMeasureDto> listAllUnitsOfMeasure();
}
