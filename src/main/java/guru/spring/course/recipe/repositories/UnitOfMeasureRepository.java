package guru.spring.course.recipe.repositories;

import guru.spring.course.recipe.models.UnitOfMeasureModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Łukasz Staniszewski on 2020-02-13
 * @project recipe
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasureModel,Long> {

    Optional<UnitOfMeasureModel> findByDescription(String description);

}
