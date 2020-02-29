package guru.spring.course.recipe.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Łukasz Staniszewski on 2020-02-17
 * @project recipe
 */
public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() throws Exception {
        Long settingId = 4L;
        category.setId(settingId);
        assertEquals(settingId, category.getId());
    }

    @Test
    public void getDescription() throws Exception {
        String desc = "ten opis musi być dokładny";
        category.setDescription(desc);
        assertEquals(desc, category.getDescription());
    }
}