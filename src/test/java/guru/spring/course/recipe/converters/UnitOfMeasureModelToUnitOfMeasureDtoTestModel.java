package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.UnitOfMeasureDto;
import guru.spring.course.recipe.models.UnitOfMeasureModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-02-28
 * @project recipe
 */
class UnitOfMeasureModelToUnitOfMeasureDtoTestModel {

    private static final long ID = 1L;
    public static final String DESCRIPTION = "testowy opis";
    UnitOfMeasureDtoToUnitOfMeasureModel converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureDtoToUnitOfMeasureModel();
    }

    @Test
    void testIfObjectIsNull() throws RuntimeException{
        assertThrows(RuntimeException.class, ()->{
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty(){
       assertNotNull(converter.convert(new UnitOfMeasureDto()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasureDto model = new UnitOfMeasureDto();
        model.setId(ID);
        model.setDescription(DESCRIPTION);
        //when
        UnitOfMeasureModel dto = converter.convert(model);
        //then
        assertNotNull(dto);
        assertEquals(ID, dto.getId());
        assertEquals(DESCRIPTION,dto.getDescription());

    }
}