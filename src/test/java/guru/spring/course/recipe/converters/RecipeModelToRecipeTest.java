package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.models.RecipeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Łukasz Staniszewski on 2020-03-02
 * @project recipe
 */
class RecipeModelToRecipeTest {

    RecipeModelToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeModelToRecipe();
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        RecipeModel model = new RecipeModel();
        assertNotNull(converter.convert(model));
    }

    @Test
    void convert() {
    }
}