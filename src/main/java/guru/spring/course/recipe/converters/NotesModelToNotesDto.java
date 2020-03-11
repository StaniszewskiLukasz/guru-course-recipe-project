package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.NotesDto;
import guru.spring.course.recipe.models.NotesModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-03-02
 * @project recipe
 */
@Component
public class NotesModelToNotesDto implements Converter<NotesModel, NotesDto> {

    @Synchronized
    @Override
    public NotesDto convert(NotesModel notesModel) {
        if(notesModel==null){
throw new IllegalArgumentException("NotesModel can not be null");
        }
        NotesDto note = new NotesDto();
        note.setId(notesModel.getId());
        note.setRecipeNotes(notesModel.getRecipeNotes());
        return note;
    }
}
