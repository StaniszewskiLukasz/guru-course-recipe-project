package guru.spring.course.recipe.repositories;

import guru.spring.course.recipe.models.UnitOfMeasureModel;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Łukasz Staniszewski on 2020-02-18
 * @project recipe
 */
@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureModelRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    void setUp() throws Exception {

    }

    @Test
    void findByDescription() {
        Optional<UnitOfMeasureModel> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon",uomOptional.get().getDescription());

    }

    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasureModel> uomOptional = unitOfMeasureRepository.findByDescription("Cup");
        assertEquals("Cup",uomOptional.get().getDescription());

    }
}