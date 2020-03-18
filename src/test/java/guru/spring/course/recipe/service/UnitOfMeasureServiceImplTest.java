package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.UnitOfMeasureModelToUnitOfMeasureDto;
import guru.spring.course.recipe.dto.UnitOfMeasureDto;
import guru.spring.course.recipe.models.UnitOfMeasureModel;
import guru.spring.course.recipe.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Łukasz Staniszewski on 2020-03-16
 * @project recipe
 */
class UnitOfMeasureServiceImplTest {

    UnitOfMeasureServiceImpl service;
    UnitOfMeasureModelToUnitOfMeasureDto converter = new UnitOfMeasureModelToUnitOfMeasureDto();

    @Mock
    UnitOfMeasureRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new UnitOfMeasureServiceImpl(repository,converter);
    }

    @Test
    void listAllUnitsOfMeasure() {
        //given
        Set<UnitOfMeasureModel> uomsSet = new HashSet<>();
        UnitOfMeasureModel unit1 = new UnitOfMeasureModel();
        unit1.setId(1L);
        UnitOfMeasureModel unit2 = new UnitOfMeasureModel();
        uomsSet.add(unit1);
        uomsSet.add(unit2);
        //when
        when(repository.findAll()).thenReturn(uomsSet);
        Set<UnitOfMeasureDto> dtoSet = service.listAllUnitsOfMeasure();
        //then
        assertEquals(2,dtoSet.size());
        verify(repository,times(1)).findAll();
    }
}