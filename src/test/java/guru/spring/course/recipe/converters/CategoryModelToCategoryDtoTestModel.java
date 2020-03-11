package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.CategoryDto;
import guru.spring.course.recipe.models.CategoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-03-01
 * @project recipe
 */
class CategoryModelToCategoryDtoTestModel {

    public static final long ID = 1L;
    public static final String DESCRIPTION = "Jakiś opis modelu kategorii";
    CategoryDtoToCategoryModel converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryDtoToCategoryModel();
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        CategoryDto model = new CategoryDto();
        assertNotNull(converter.convert(model));
    }

    @Test
    void convert() {
        //given
        CategoryDto model = new CategoryDto();
        model.setId(ID);
        model.setDescription(DESCRIPTION);
        //when
        CategoryModel categoryModel = converter.convert(model);
        //then
        assert categoryModel != null;
        assertNotNull(categoryModel.getId());
        assertEquals(ID, categoryModel.getId());
        assertEquals(DESCRIPTION, categoryModel.getDescription());
    }
}