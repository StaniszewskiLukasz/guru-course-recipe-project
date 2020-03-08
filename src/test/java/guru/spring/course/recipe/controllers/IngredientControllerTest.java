package guru.spring.course.recipe.controllers;

import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
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
    RecipeService recipeService;

    IngredientController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        //given
        RecipeModel model = new RecipeModel();
//        model.setId(1L);
        when(recipeService.findRecipeModelById(anyLong())).thenReturn(model);

        //when
        mockMvc.perform(get("recipe/1/ingredients"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(status().isOk());
        //then
        verify(recipeService, times(1)).findRecipeModelById(anyLong());
    }
}