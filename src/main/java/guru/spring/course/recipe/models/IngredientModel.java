package guru.spring.course.recipe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Łukasz Staniszewski on 2020-02-28
 * @project recipe
 */
@Setter
@Getter
@NoArgsConstructor
public class IngredientModel {

    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureModel unitOfMeasure;
}
