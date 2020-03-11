package guru.spring.course.recipe.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Łukasz Staniszewski on 2020-02-11
 * @project recipe
 */
@Data
@EqualsAndHashCode(exclude = {"recipeModel"})
@Entity
public class NotesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private RecipeModel recipeModel;

    //ta adnotacja pozwala zapisać więcej niż 256 znaków w tym polu
    @Lob
    private String recipeNotes;
}
