package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.NotesDto;
import guru.spring.course.recipe.models.NotesModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-03-02
 * @project recipe
 */
class NotesModelToNotesDtoTestModel {

    public static final long ID = 1L;
    public static final String RECIPE_NOTES = "Jakaś notatka do przepisu";
    NotesDtoToNotesModel converter;

    @BeforeEach
    void setUp() {
        converter= new NotesDtoToNotesModel();
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        NotesDto model = new NotesDto();
        assertNotNull(converter.convert(model));
    }


    @Test
    void convert() {
        //given
        NotesDto model = new NotesDto();
        model.setId(ID);
        model.setRecipeNotes(RECIPE_NOTES);
        //when
        NotesModel notesModel = converter.convert(model);
        //then
        assertNotNull(notesModel);
        assertEquals(ID, notesModel.getId());
        assertEquals(RECIPE_NOTES, notesModel.getRecipeNotes());
    }
}