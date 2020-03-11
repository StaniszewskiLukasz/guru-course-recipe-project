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
class CategoryDtoToCategoryModelDtoDtoTest {

    public static final long ID = 1L;
    public static final String DESCRIPTION = "Jakiś opis kategorii";
    CategoryModelToCategoryDto converter;

    @BeforeEach
    void setUp() {
        converter= new CategoryModelToCategoryDto();
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        CategoryModel categoryModel = new CategoryModel();
        assertNotNull(converter.convert(categoryModel));
    }

    @Test
    void convert() {
        //given
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(ID);
        categoryModel.setDescription(DESCRIPTION);
        //when
        CategoryDto model = converter.convert(categoryModel);
        //then
        assertEquals(ID,model.getId());
        assertEquals(DESCRIPTION,model.getDescription());
    }
}