package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.RecipeDtoToRecipeModel;
import guru.spring.course.recipe.converters.RecipeModelToRecipeDto;
import guru.spring.course.recipe.dto.RecipeDto;
import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Łukasz Staniszewski on 2020-02-17
 * @project recipe
 */
public class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;
    RecipeDtoToRecipeModel recipeDtoToRecipeModel;
    RecipeModelToRecipeDto recipeModelToRecipeDto;
    @Mock
    RecipeServiceImpl recipeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // to nam da RecipeRepository
        recipeService = new RecipeServiceImpl(recipeRepository, recipeDtoToRecipeModel, recipeModelToRecipeDto);
    }


    @Test
    public void getRecipes() {
        RecipeModel recipe = new RecipeModel();
        Set<RecipeModel> receipesData = new HashSet();
        receipesData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(receipesData);

        Set<RecipeModel> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }


    @Test
    public void deleteRecipe() {
        Long id = 2L;
        recipeService.deleteById(id);
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}