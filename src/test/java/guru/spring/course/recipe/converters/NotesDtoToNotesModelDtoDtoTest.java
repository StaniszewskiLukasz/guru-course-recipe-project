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
class NotesDtoToNotesModelDtoDtoTest {

    public static final long ID = 1L;
    public static final String RECIPE_NOTES = "Jakaś notatka do przepisu";
    NotesModelToNotesDto converter;

    @BeforeEach
    void setUp() {
        converter= new NotesModelToNotesDto();
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        NotesModel notesModel = new NotesModel();
        assertNotNull(converter.convert(notesModel));
    }


    @Test
    void convert() {
        //given
        NotesModel notesModel = new NotesModel();
        notesModel.setId(ID);
        notesModel.setRecipeNotes(RECIPE_NOTES);
        //when
        NotesDto model = converter.convert(notesModel);
        //then
        assertNotNull(model);
        assertEquals(ID,model.getId());
        assertEquals(RECIPE_NOTES,model.getRecipeNotes());
    }
}