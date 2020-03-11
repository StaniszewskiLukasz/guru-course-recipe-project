package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.RecipeDtoToRecipeModel;
import guru.spring.course.recipe.converters.RecipeModelToRecipeDto;
import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author Łukasz Staniszewski on 2020-02-17
 * @project recipe
 */
public class RecipeModelServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;
    RecipeDtoToRecipeModel recipeDtoToRecipeModel;
    RecipeModelToRecipeDto recipeModelToRecipeDto;
    RecipeServiceImpl recipeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // to nam da RecipeRepository
        recipeService = new RecipeServiceImpl(recipeRepository, recipeDtoToRecipeModel, recipeModelToRecipeDto);
    }


    @Test
    public void getRecipes() {
        RecipeModel recipeModel = new RecipeModel();
        HashSet recipesData = new HashSet();
        recipesData.add(recipeModel);
        when(recipeService.getRecipes()).thenReturn(recipesData);
        Set<RecipeModel> recipeModels = recipeService.getRecipes();
        assertEquals(recipeModels.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    public void findRecipeModelById() {
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setId(1L);
        when(recipeRepository.findById(anyLong())).thenReturn(java.util.Optional.of(recipeModel));
        RecipeModel model = recipeService.findRecipeModelById(1L);
        assertNotNull("Null recipe returned", model);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void deleteRecipe() {
        Long id = 2L;
        recipeService.deleteRecipeById(id);
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}