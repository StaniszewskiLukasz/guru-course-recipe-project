package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.IngredientDto;
import guru.spring.course.recipe.dto.UnitOfMeasureDto;
import guru.spring.course.recipe.models.IngredientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-03-01
 * @project recipe
 */
class IngredientModelToIngredientDtoTestModel {

    public static final long INGREDIENT_ID = 1L;
    public static final String DESCRIPTION = "Jakiś opis modelu składnika";
    public static final long UOM_ID = 2L;

    IngredientDtoToIngredientModel converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientDtoToIngredientModel(new UnitOfMeasureDtoToUnitOfMeasureModel());
    }

    @Test
    void checkIfModelIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        UnitOfMeasureDto measureModel = new UnitOfMeasureDto();
        IngredientDto model = new IngredientDto();
        model.setUnitOfMeasure(measureModel);
        assertNotNull(converter.convert(model));
    }

    @Test
    void convert() {
        //given
        IngredientDto model = new IngredientDto();
        model.setId(INGREDIENT_ID);
        model.setDescription(DESCRIPTION);
        UnitOfMeasureDto measureModel = new UnitOfMeasureDto();
        measureModel.setId(UOM_ID);
        model.setUnitOfMeasure(measureModel);
        //when
        IngredientModel ingredientModel = converter.convert(model);
        //then
        assertNotNull(ingredientModel);
        assertNotNull(ingredientModel.getUnitOfMeasureModel());
        assertEquals(INGREDIENT_ID, ingredientModel.getId());
        assertEquals(DESCRIPTION, ingredientModel.getDescription());
        assertEquals(UOM_ID, ingredientModel.getUnitOfMeasureModel().getId());
    }
}