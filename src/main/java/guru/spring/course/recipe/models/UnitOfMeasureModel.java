package guru.spring.course.recipe.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Łukasz Staniszewski on 2020-02-11
 * @project recipe
 */
@Entity(name = "unit_of_measure")
@Data
public class UnitOfMeasureModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
}
