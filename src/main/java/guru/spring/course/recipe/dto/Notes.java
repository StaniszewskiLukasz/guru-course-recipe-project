package guru.spring.course.recipe.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Łukasz Staniszewski on 2020-02-11
 * @project recipe
 */
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    //ta adnotacja pozwala zapisać więcej niż 256 znaków w tym polu
    @Lob
    private String recipeNotes;
}
