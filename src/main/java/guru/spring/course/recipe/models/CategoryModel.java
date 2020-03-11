package guru.spring.course.recipe.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Łukasz Staniszewski on 2020-02-12
 * @project recipe
 */
@Data
@EqualsAndHashCode(exclude = {"recipeModels"})
@Entity(name = "category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<RecipeModel> recipeModels;

    public CategoryModel() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CategoryModel;
    }

}
