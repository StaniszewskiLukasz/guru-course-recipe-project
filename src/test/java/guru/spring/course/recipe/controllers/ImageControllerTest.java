package guru.spring.course.recipe.controllers;

import guru.spring.course.recipe.dto.RecipeDto;
import guru.spring.course.recipe.service.RecipeService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ImageControllerTest {

    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;

    @Before
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void renderImageFromDB() throws Exception {
        RecipeDto recipe = new RecipeDto();
        recipe.setId(1L);
        String text = "fake image text";
        Byte[] bytes = new Byte[text.getBytes().length];
        int i =0;
        for (Byte b : text.getBytes()) {
            bytes[i++] = b;
        }
        recipe.setImage(bytes);

        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);

        MockHttpServletResponse response = mockMvc.perform(get("recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] contentAsByteArray = response.getContentAsByteArray();
        assertEquals(text.getBytes().length,contentAsByteArray.length);
    }
}