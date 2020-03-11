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
public class NotesDtoToNotesModel implements Converter<NotesDto, NotesModel> {

    @Synchronized
    @Override
    public NotesModel convert(NotesDto notesDto) {
        if(notesDto == null){
            throw  new IllegalArgumentException("NotesModel can not be null");
        }
        NotesModel notesModel = new NotesModel();
        notesModel.setId(notesDto.getId());
        notesModel.setRecipeNotes(notesDto.getRecipeNotes());
        return notesModel;
    }
}
