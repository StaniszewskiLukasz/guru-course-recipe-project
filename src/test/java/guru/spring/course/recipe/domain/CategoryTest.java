package guru.spring.course.recipe.domain;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Łukasz Staniszewski on 2020-02-17
 * @project recipe
 */
class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        Long settingId = 4L;
        category.setId(settingId);
        assertEquals(settingId, (long) category.getId());
    }

    @Test
    void getDescription() {
        String desc = "ten opis musi być dokładny";
        category.setDescription(desc);
        assertEquals(desc,category.getDescription());
    }
}