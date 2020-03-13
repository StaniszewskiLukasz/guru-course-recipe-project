package guru.spring.course.recipe.controllers;

import guru.spring.course.recipe.dto.IngredientDto;
import guru.spring.course.recipe.dto.RecipeDto;
import guru.spring.course.recipe.service.IngredientService;
import guru.spring.course.recipe.service.RecipeService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Łukasz Staniszewski on 2020-03-08
 * @project recipe
 */
class IngredientControllerTest {

    @Mock
    IngredientService ingredientService;

    @Mock
    RecipeService recipeService;

    IngredientController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(recipeService, ingredientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        //given
        RecipeDto recipe = new RecipeDto();

        //when
        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);

        //then
        mockMvc.perform(get("recipe/1/ingredients"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(status().isOk());
        verify(recipeService, times(1)).getRecipeById(anyLong());
    }

    @Test
    public void testShowIngredient() throws Exception{
        //given
        IngredientDto ingredient = new IngredientDto();
        ingredient.setId(1L);

       //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(ingredient);

        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(view().name("recipe/ingredient/3/show"))
                .andExpect(status().isOk());

    }
}