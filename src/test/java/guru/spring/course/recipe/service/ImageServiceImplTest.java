package guru.spring.course.recipe.service;

import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    ImageService imageService;

    @Before
    void setUp() {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    void saveImageFile() throws IOException {

        Long id = 1L;
        MultipartFile multipartFile = new MockMultipartFile("imageFile",
                "testing.txt",
                "text/plain",
                "Jakieś bzdety".getBytes());

        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setId(1L);
        Optional<RecipeModel> recipeOptional = Optional.of(recipeModel);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        ArgumentCaptor<RecipeModel> argumentCaptor = ArgumentCaptor.forClass(RecipeModel.class);
        //when
        imageService.saveImageFile(id,multipartFile);

        //then
        verify(recipeRepository,times(1)).save(argumentCaptor.capture());
        RecipeModel savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length,savedRecipe.getImage().length);

    }
}