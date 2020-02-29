package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.UnitOfMeasure;
import guru.spring.course.recipe.models.UnitOfMeasureModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-02-28
 * @project recipe
 */
class UnitOfMeasureModelToUnitOfMeasureTest {

    private static final long ID = 1L;
    public static final String DESCRIPTION = "testowy opis";
    UnitOfMeasureModelToUnitOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureModelToUnitOfMeasure();
    }

    @Test
    void testIfObjectIsNull() throws RuntimeException{
        assertThrows(RuntimeException.class, ()->{
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty(){
       assertNotNull(converter.convert(new UnitOfMeasureModel()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasureModel model = new UnitOfMeasureModel();
        model.setId(ID);
        model.setDescription(DESCRIPTION);
        //when
        UnitOfMeasure dto = converter.convert(model);
        //then
        assertNotNull(dto);
        assertEquals(ID, dto.getId());
        assertEquals(DESCRIPTION,dto.getDescription());

    }
}