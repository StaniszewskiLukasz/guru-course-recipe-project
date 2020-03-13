package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.IngredientModelToIngredientDto;
import guru.spring.course.recipe.converters.UnitOfMeasureModelToUnitOfMeasureDto;
import guru.spring.course.recipe.dto.IngredientDto;
import guru.spring.course.recipe.models.IngredientModel;
import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author Łukasz Staniszewski on 2020-03-13
 * @project recipe
 */
class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;
    IngredientServiceImpl ingredientService;
    IngredientModelToIngredientDto converter;

    public IngredientServiceImplTest() {
        this.converter = new IngredientModelToIngredientDto(new UnitOfMeasureModelToUnitOfMeasureDto());
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository,converter);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setId(1L);

        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setId(1L);

        IngredientModel ingredientModel2 = new IngredientModel();
        ingredientModel2.setId(2L);

        IngredientModel ingredientModel3 = new IngredientModel();
        ingredientModel3.setId(3L);

        recipeModel.addIngredient(ingredientModel);
        recipeModel.addIngredient(ingredientModel2);
        recipeModel.addIngredient(ingredientModel3);

        Optional<RecipeModel> recipeOptional = Optional.of(recipeModel);
        when(recipeRepository.findById(any())).thenReturn(recipeOptional);

        IngredientDto ingredientDto = ingredientService.findByRecipeIdAndIngredientId(3L, 1L);

        assertEquals(3L,ingredientDto.getId());
        assertEquals(1L,ingredientDto.getRecipeId());
        verify(recipeRepository,times(1)).findById(anyLong());
    }
}