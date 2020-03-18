package guru.spring.course.recipe.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Łukasz Staniszewski on 2020-02-11
 * @project recipe
 */
@Entity
@EqualsAndHashCode(exclude = {"recipeModel"})
@Getter
@Setter
public class IngredientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @ManyToOne
    private RecipeModel recipeModel;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasureModel unitOfMeasureModel;

    public IngredientModel() {
    }

    public IngredientModel(String description, BigDecimal amount, UnitOfMeasureModel unitOfMeasureModel) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasureModel = unitOfMeasureModel;
    }

    public IngredientModel(String description, BigDecimal amount, UnitOfMeasureModel unitOfMeasureModel, RecipeModel recipeModel) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasureModel = unitOfMeasureModel;
        this.recipeModel = recipeModel;
    }
}
