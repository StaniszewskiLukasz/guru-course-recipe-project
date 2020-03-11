package guru.spring.course.recipe.repositories;

import guru.spring.course.recipe.models.CategoryModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Łukasz Staniszewski on 2020-02-13
 * @project recipe
 */
public interface CategoryRepository extends CrudRepository<CategoryModel,Long> {

    Optional<CategoryModel> findByDescription(String description);

}
