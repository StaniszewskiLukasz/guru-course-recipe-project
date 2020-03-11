package guru.spring.course.recipe.controllers;

import guru.spring.course.recipe.dto.RecipeDto;
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
class IngredientModelControllerTest {

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
        RecipeDto model = new RecipeDto();
//        model.setId(1L);
        when(recipeService.getRecipeById(anyLong())).thenReturn(model);

        //when
        mockMvc.perform(get("recipe/1/ingredients"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(status().isOk());
        //then
        verify(recipeService, times(1)).getRecipeById(anyLong());
    }

//    @Test
//    public void testShowIngredeint() throws Exception{
//        RecipeDto model = new RecipeDto();
//        when(recipeService.findRecipeById(anyLong())).thenReturn(model);
//        //when
//        mockMvc.perform(get("recipe/1/ingredient/3/show"))
//                .andExpect(model().attributeExists("recipe"))
//                .andExpect(view().name("recipe/ingredient/3/show"))
//                .andExpect(status().isOk());
//        //then
//        verify()
//    }
}