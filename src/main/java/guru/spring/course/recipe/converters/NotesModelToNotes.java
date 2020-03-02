package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.Notes;
import guru.spring.course.recipe.models.NotesModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-03-02
 * @project recipe
 */
@Component
public class NotesModelToNotes implements Converter<NotesModel, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesModel notesModel) {
        if(notesModel==null){
            throw  new IllegalArgumentException("NotesModel can not be null");
        }
        Notes notes = new Notes();
        notes.setId(notesModel.getId());
        notes.setRecipeNotes(notesModel.getRecipeNotes());
        return notes;
    }
}
