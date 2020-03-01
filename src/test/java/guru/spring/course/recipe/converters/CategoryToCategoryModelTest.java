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
class CategoryToCategoryModelTest {

    public static final long ID = 1L;
    public static final String DESCRIPTION = "Jakiś opis kategorii";
    CategoryToCategoryModel converter;

    @BeforeEach
    void setUp() {
        converter= new CategoryToCategoryModel();
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        Category category = new Category();
        assertNotNull(converter.convert(category));
    }

    @Test
    void convert() {
        //given
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);
        //when
        CategoryModel model = converter.convert(category);
        //then
        assertEquals(ID,model.getId());
        assertEquals(DESCRIPTION,model.getDescription());
    }
}