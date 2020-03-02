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
class NotesModelToNotesTest {

    public static final long ID = 1L;
    public static final String RECIPE_NOTES = "Jakaś notatka do przepisu";
    NotesModelToNotes converter;

    @BeforeEach
    void setUp() {
        converter= new NotesModelToNotes();
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        NotesModel model = new NotesModel();
        assertNotNull(converter.convert(model));
    }


    @Test
    void convert() {
        //given
        NotesModel model = new NotesModel();
        model.setId(ID);
        model.setRecipeNotes(RECIPE_NOTES);
        //when
        Notes notes = converter.convert(model);
        //then
        assertNotNull(notes);
        assertEquals(ID,notes.getId());
        assertEquals(RECIPE_NOTES,notes.getRecipeNotes());
    }
}