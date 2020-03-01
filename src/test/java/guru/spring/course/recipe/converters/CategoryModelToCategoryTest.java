package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.Category;
import guru.spring.course.recipe.models.CategoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-03-01
 * @project recipe
 */
class CategoryModelToCategoryTest {

    public static final long ID = 1L;
    public static final String DESCRIPTION = "Jakiś opis modelu kategorii";
    CategoryModelToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryModelToCategory();
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        CategoryModel model = new CategoryModel();
        assertNotNull(converter.convert(model));
    }

    @Test
    void convert() {
        //given
        CategoryModel model = new CategoryModel();
        model.setId(ID);
        model.setDescription(DESCRIPTION);
        //when
        Category category = converter.convert(model);
        //then
        assert category != null;
        assertNotNull(category.getId());
        assertEquals(ID,category.getId());
        assertEquals(DESCRIPTION,category.getDescription());
    }
}