package guru.spring.course.recipe.controllers;

import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Łukasz Staniszewski on 2020-02-17
 * @project recipe
 */

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {
        //poniżej standaloneSteup nadaje się na test jednostkowy
        //jest jeszcze do wyboru WebContextSetup ale ona sie nadaję na test całościowe
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build(); // tu błąd

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        //given
        Set<RecipeModel> recipeModels = new HashSet<>();
        recipeModels.add(new RecipeModel());
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setId(1L);
        recipeModels.add(recipeModel);

        when(recipeService.getRecipes()).thenReturn(recipeModels);
        ArgumentCaptor<Set<RecipeModel>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = indexController.getIndexPage(model);

        //then
        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<RecipeModel> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}