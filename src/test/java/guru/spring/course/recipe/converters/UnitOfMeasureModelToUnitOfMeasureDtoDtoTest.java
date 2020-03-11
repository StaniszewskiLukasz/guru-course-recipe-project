package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.UnitOfMeasureDto;
import guru.spring.course.recipe.models.UnitOfMeasureModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-02-29
 * @project recipe
 */
class UnitOfMeasureModelToUnitOfMeasureDtoDtoTest {

    private static final long ID = 1L;
    public static final String DESCRIPTION = "testowy opis";
    UnitOfMeasureModelToUnitOfMeasureDto converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureModelToUnitOfMeasureDto();
    }

    @Test
    void testIfObjectIsNull() throws RuntimeException {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });

    }

    @Test
    void testIfObjectIsEmpty() {
        assertNotNull(converter.convert(new UnitOfMeasureModel()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasureModel unitOfMeasureModel = new UnitOfMeasureModel();
        unitOfMeasureModel.setId(ID);
        unitOfMeasureModel.setDescription(DESCRIPTION);
        //when
        UnitOfMeasureDto measureModel = converter.convert(unitOfMeasureModel);
        //then
        assertNotNull(measureModel);
        assertEquals(ID, unitOfMeasureModel.getId());
        assertEquals(DESCRIPTION, unitOfMeasureModel.getDescription());
    }
}