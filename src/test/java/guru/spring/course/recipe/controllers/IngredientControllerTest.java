package guru.spring.course.recipe.controllers;

import guru.spring.course.recipe.dto.IngredientDto;
import guru.spring.course.recipe.dto.RecipeDto;
import guru.spring.course.recipe.dto.UnitOfMeasureDto;
import guru.spring.course.recipe.service.IngredientService;
import guru.spring.course.recipe.service.RecipeService;
import guru.spring.course.recipe.service.UnitOfMeasureService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Łukasz Staniszewski on 2020-03-08
 * @project recipe
 */
public class IngredientControllerTest {

    @Mock
    IngredientService ingredientService;

    @Mock
    RecipeService recipeService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
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
    public void showIngredientTest() throws Exception {
        //given
        IngredientDto ingredient = new IngredientDto();
        ingredient.setId(1L);

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredient);

        //then
        mockMvc.perform(get("/recipe/1/ingredient/1/show"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(view().name("recipe/ingredient/1/show"))
                .andExpect(status().isOk());
    }

    @Test
    void newIngredientFormTest() throws Exception {
        //given
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(1L);
        //when
        when(recipeService.getRecipeById(anyLong())).thenReturn(recipeDto);
        //then
        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("unitList"))
                .andExpect(view().name("recipe/ingredient/ingredientForm"));

        verify(recipeService, times(1)).getRecipeById(anyLong());
    }

    @Test
    void updateRecipeIngredientTest() throws Exception {
        //given
        IngredientDto ingredient = new IngredientDto();
        ingredient.setId(1L);
        UnitOfMeasureDto unit1 = new UnitOfMeasureDto();
        unit1.setId(1L);
        UnitOfMeasureDto unit2 = new UnitOfMeasureDto();
        unit2.setId(2L);
        Set<UnitOfMeasureDto> unitSet = new HashSet<>();
        unitSet.add(unit1);
        unitSet.add(unit2);
        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredient);
        when(unitOfMeasureService.listAllUnitsOfMeasure()).thenReturn(unitSet);
        //then
        mockMvc.perform(get("/recipe/1/ingredient/1/update"))
                .andExpect(view().name("recipe/ingredient/ingredientForm"))
                .andExpect(model().attributeExists("ingredient", "unitOfMeasureList"))
                .andExpect(status().isOk());

    }

    @Test
    void saveOrUpdate() throws Exception {
        //given
        IngredientDto ingredient = new IngredientDto();
        ingredient.setId(1L);
        ingredient.setRecipeId(1L);
        //when
        when(ingredientService.saveIngredient(any())).thenReturn(ingredient);
        //then
        mockMvc.perform(post("/recipe/1/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredient/1/show"));
    }

    @Test
    void deleteRecipeIngredient() throws Exception {
        //when
        mockMvc.perform(get("/recipe/1/ingredient/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredients"));
    }
}