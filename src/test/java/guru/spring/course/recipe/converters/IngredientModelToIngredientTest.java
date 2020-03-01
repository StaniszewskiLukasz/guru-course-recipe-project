package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.Ingredient;
import guru.spring.course.recipe.models.IngredientModel;
import guru.spring.course.recipe.models.UnitOfMeasureModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-03-01
 * @project recipe
 */
class IngredientModelToIngredientTest {

    public static final long INGREDIENT_ID = 1L;
    public static final String DESCRIPTION = "Jakiś opis modelu składnika";
    public static final long UOM_ID = 2L;

    IngredientModelToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientModelToIngredient(new UnitOfMeasureModelToUnitOfMeasure());
    }

    @Test
    void checkIfModelIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        UnitOfMeasureModel measureModel = new UnitOfMeasureModel();
        IngredientModel model = new IngredientModel();
        model.setUnitOfMeasure(measureModel);
        assertNotNull(converter.convert(model));
    }

    @Test
    void convert() {
        //given
        IngredientModel model = new IngredientModel();
        model.setId(INGREDIENT_ID);
        model.setDescription(DESCRIPTION);
        UnitOfMeasureModel measureModel = new UnitOfMeasureModel();
        measureModel.setId(UOM_ID);
        model.setUnitOfMeasure(measureModel);
        //when
        Ingredient ingredient = converter.convert(model);
        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }
}