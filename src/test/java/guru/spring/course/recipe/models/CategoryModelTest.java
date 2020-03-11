package guru.spring.course.recipe.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Łukasz Staniszewski on 2020-02-17
 * @project recipe
 */
public class CategoryModelTest {

    CategoryModel categoryModel;

    @Before
    public void setUp() {
        categoryModel = new CategoryModel();
    }

    @Test
    public void getId() throws Exception {
        Long settingId = 4L;
        categoryModel.setId(settingId);
        assertEquals(settingId, categoryModel.getId());
    }

    @Test
    public void getDescription() throws Exception {
        String desc = "ten opis musi być dokładny";
        categoryModel.setDescription(desc);
        assertEquals(desc, categoryModel.getDescription());
    }
}