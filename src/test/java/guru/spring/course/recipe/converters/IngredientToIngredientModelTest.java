package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.Ingredient;
import guru.spring.course.recipe.dto.UnitOfMeasure;
import guru.spring.course.recipe.models.IngredientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-03-01
 * @project recipe
 */
class IngredientToIngredientModelTest {

    public static final long INGREDIENT_ID = 1L;
    public static final String DESCRIPTION = "Jakiś opis składnika";
    public static final long UOM_ID = 2L;

    IngredientToIngredientModel converter;

    @BeforeEach
    void setUp() {
        converter=new IngredientToIngredientModel(new UnitOfMeasureToUnitOfMeasureModel());
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        UnitOfMeasure measure = new UnitOfMeasure();
        Ingredient ingredient = new Ingredient();
        ingredient.setUnitOfMeasure(measure);
        assertNotNull(converter.convert(ingredient));
    }

    @Test
    void convert() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setDescription(DESCRIPTION);
        UnitOfMeasure measure = new UnitOfMeasure();
        measure.setId(UOM_ID);
        ingredient.setUnitOfMeasure(measure);
        //when
        IngredientModel model = converter.convert(ingredient);
        //then
        assertNotNull(model);
        assertNotNull(model.getUnitOfMeasure());
        assertEquals(INGREDIENT_ID, model.getId());
        assertEquals(DESCRIPTION, model.getDescription());
        assertEquals(UOM_ID, model.getUnitOfMeasure().getId());
    }
}