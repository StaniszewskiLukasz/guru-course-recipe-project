package guru.spring.course.recipe.repositories;

import guru.spring.course.recipe.models.IngredientModel;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Łukasz Staniszewski on 2020-03-13
 * @project recipe
 */
public interface IngredientRepository extends CrudRepository<IngredientModel,Long> {

}
