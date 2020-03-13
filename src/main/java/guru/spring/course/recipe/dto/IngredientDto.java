package guru.spring.course.recipe.dto;

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
public class IngredientDto {

    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureDto unitOfMeasure;
}
