package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.Notes;
import guru.spring.course.recipe.models.NotesModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-03-02
 * @project recipe
 */
class NotesToNotesModelTest {

    public static final long ID = 1L;
    public static final String RECIPE_NOTES = "Jakaś notatka do przepisu";
    NotesToNotesModel converter;

    @BeforeEach
    void setUp() {
        converter= new NotesToNotesModel();
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        Notes notes = new Notes();
        assertNotNull(converter.convert(notes));
    }


    @Test
    void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(RECIPE_NOTES);
        //when
        NotesModel model = converter.convert(notes);
        //then
        assertNotNull(model);
        assertEquals(ID,model.getId());
        assertEquals(RECIPE_NOTES,model.getRecipeNotes());
    }
}