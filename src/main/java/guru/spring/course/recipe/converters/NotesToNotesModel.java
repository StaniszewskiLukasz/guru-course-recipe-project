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
public class NotesToNotesModel implements Converter<Notes, NotesModel> {

    @Synchronized
    @Nullable
    @Override
    public NotesModel convert(Notes notes) {
        if(notes==null){
            throw new IllegalArgumentException("Notes can not be null");
        }
        NotesModel model = new NotesModel();
        model.setId(notes.getId());
        model.setRecipeNotes(notes.getRecipeNotes());
        return model;
    }
}
