package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.UnitOfMeasure;
import guru.spring.course.recipe.models.UnitOfMeasureModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-02-29
 * @project recipe
 */
class UnitOfMeasureToUnitOfMeasureModelTest {

    private static final long ID = 1L;
    public static final String DESCRIPTION = "testowy opis";
    UnitOfMeasureToUnitOfMeasureModel converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureModel();
    }

    @Test
    void testIfObjectIsNull() throws RuntimeException{
     assertThrows(IllegalArgumentException.class,()->{
         converter.convert(null);
     });

    }

    @Test
    void testIfObjectIsEmpty(){
assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(ID);
        unitOfMeasure.setDescription(DESCRIPTION);
        //when
        UnitOfMeasureModel measureModel = converter.convert(unitOfMeasure);
        //then
        assertNotNull(measureModel);
        assertEquals(ID,unitOfMeasure.getId());
        assertEquals(DESCRIPTION,unitOfMeasure.getDescription());
    }
}