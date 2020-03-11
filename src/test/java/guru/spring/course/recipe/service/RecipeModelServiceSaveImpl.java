package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.RecipeDtoToRecipeModel;
import guru.spring.course.recipe.converters.RecipeModelToRecipeDto;
import guru.spring.course.recipe.dto.RecipeDto;
import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Łukasz Staniszewski on 2020-03-05
 * @project recipe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeModelServiceSaveImpl {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeDtoToRecipeModel recipeDtoToRecipeModel;

    @Autowired
    RecipeModelToRecipeDto recipeModelToRecipeDto;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        //given
        Iterable<RecipeModel> recipes = recipeRepository.findAll();
        RecipeModel testRecipeModel = recipes.iterator().next();
        RecipeDto testRecipeDto = recipeModelToRecipeDto.convert(testRecipeModel);

        //when
        testRecipeDto.setDescription(NEW_DESCRIPTION);
        RecipeDto recipe = recipeService.saveRecipe(testRecipeDto);

        //then
        assertEquals(NEW_DESCRIPTION, recipe.getDescription());
        assertEquals(testRecipeModel.getId(), recipe.getId());
        assertEquals(testRecipeModel.getCategories().size(), recipe.getCategories().size());
        assertEquals(testRecipeModel.getIngredientModels().size(), recipe.getIngredients().size());
    }
}
