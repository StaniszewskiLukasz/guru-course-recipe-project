package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.RecipeModelToRecipe;
import guru.spring.course.recipe.converters.RecipeToRecipeModel;
import guru.spring.course.recipe.dto.Recipe;
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
public class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;
    RecipeModelToRecipe recipeModelToRecipe;
    RecipeToRecipeModel recipeToRecipeModel;
    RecipeServiceImpl recipeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // to nam da RecipeRepository
        recipeService = new RecipeServiceImpl(recipeRepository, recipeModelToRecipe, recipeToRecipeModel);
    }


    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);
        when(recipeService.getRecipes()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    public void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeRepository.findById(anyLong())).thenReturn(java.util.Optional.of(recipe));
        Recipe recipeById = recipeService.getRecipeById(1L);
        assertNotNull("Null recipe returned", recipeById);
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