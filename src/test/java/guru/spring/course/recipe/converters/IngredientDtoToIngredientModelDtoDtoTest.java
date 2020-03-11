package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.IngredientDto;
import guru.spring.course.recipe.models.IngredientModel;
import guru.spring.course.recipe.models.UnitOfMeasureModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-03-01
 * @project recipe
 */
class IngredientDtoToIngredientModelDtoDtoTest {

    public static final long INGREDIENT_ID = 1L;
    public static final String DESCRIPTION = "Jakiś opis składnika";
    public static final long UOM_ID = 2L;

    IngredientModelToIngredientDto converter;

    @BeforeEach
    void setUp() {
        converter=new IngredientModelToIngredientDto(new UnitOfMeasureModelToUnitOfMeasureDto());
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        UnitOfMeasureModel measure = new UnitOfMeasureModel();
        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setUnitOfMeasureModel(measure);
        assertNotNull(converter.convert(ingredientModel));
    }

    @Test
    void convert() {
        //given
        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setId(INGREDIENT_ID);
        ingredientModel.setDescription(DESCRIPTION);
        UnitOfMeasureModel measure = new UnitOfMeasureModel();
        measure.setId(UOM_ID);
        ingredientModel.setUnitOfMeasureModel(measure);
        //when
        IngredientDto model = converter.convert(ingredientModel);
        //then
        assertNotNull(model);
        assertNotNull(model.getUnitOfMeasure());
        assertEquals(INGREDIENT_ID, model.getId());
        assertEquals(DESCRIPTION, model.getDescription());
        assertEquals(UOM_ID, model.getUnitOfMeasure().getId());
    }
}