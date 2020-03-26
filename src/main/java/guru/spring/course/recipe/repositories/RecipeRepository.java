package guru.spring.course.recipe.repositories;

import guru.spring.course.recipe.models.RecipeModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

/**
 * @author Łukasz Staniszewski on 2020-02-13
 * @project recipe
 */
public interface RecipeRepository extends CrudRepository<RecipeModel,Long> {
}
