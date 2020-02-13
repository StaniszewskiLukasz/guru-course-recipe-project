package guru.spring.course.recipe.repositories;

import guru.spring.course.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Łukasz Staniszewski on 2020-02-13
 * @project recipe
 */
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
