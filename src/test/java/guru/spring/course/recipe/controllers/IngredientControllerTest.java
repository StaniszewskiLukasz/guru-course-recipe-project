package guru.spring.course.recipe.controllers;

import guru.spring.course.recipe.converters.IngredientDtoToIngredientModel;
import guru.spring.course.recipe.converters.IngredientModelToIngredientDto;
import guru.spring.course.recipe.converters.UnitOfMeasureDtoToUnitOfMeasureModel;
import guru.spring.course.recipe.converters.UnitOfMeasureModelToUnitOfMeasureDto;
import guru.spring.course.recipe.dto.IngredientDto;
import guru.spring.course.recipe.models.IngredientModel;
import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.repositories.RecipeRepository;
import guru.spring.course.recipe.repositories.UnitOfMeasureRepository;
import guru.spring.course.recipe.service.IngredientService;
import guru.spring.course.recipe.service.IngredientServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author Łukasz Staniszewski on 2020-03-08
 * @project recipe
 */
public class IngredientControllerTest {

    private final IngredientModelToIngredientDto ingredientModelConverter;
    private final IngredientDtoToIngredientModel ingredientDtoConverter;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    public IngredientControllerTest() {
        this.ingredientModelConverter = new IngredientModelToIngredientDto(new UnitOfMeasureModelToUnitOfMeasureDto());
        this.ingredientDtoConverter = new IngredientDtoToIngredientModel(new UnitOfMeasureDtoToUnitOfMeasureModel());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientModelConverter,
                ingredientDtoConverter,
                recipeRepository,
                unitOfMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndId() throws Exception {
    }

    @Test
    public void findByRecipeIdAndReceipeIdHappyPath() throws Exception {
        //given
        RecipeModel recipe = new RecipeModel();
        recipe.setId(1L);

        IngredientModel ingredient1 = new IngredientModel();
        ingredient1.setId(1L);

        IngredientModel ingredient2 = new IngredientModel();
        ingredient2.setId(1L);

        IngredientModel ingredient3 = new IngredientModel();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<RecipeModel> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientDto ingredientDto = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientDto.getId());
        assertEquals(Long.valueOf(1L), ingredientDto.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }


    @Test
    public void testSaveRecipeCommand() throws Exception {
        //given
        IngredientDto command = new IngredientDto();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<RecipeModel> recipeOptional = Optional.of(new RecipeModel());

        RecipeModel savedRecipe = new RecipeModel();
        savedRecipe.addIngredient(new IngredientModel());
        savedRecipe.getIngredientModels().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientDto savedCommand = ingredientService.saveIngredientDto(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(RecipeModel.class));

    }

    @Test
    public void testDeleteById() throws Exception {
        //given
        RecipeModel recipe = new RecipeModel();
        IngredientModel ingredient = new IngredientModel();
        ingredient.setId(3L);
        recipe.addIngredient(ingredient);
        ingredient.setRecipeModel(recipe);
        Optional<RecipeModel> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        ingredientService.deleteIngredientByRecipeIdAndIngredientId(1L, 3L);

        //then
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(RecipeModel.class));
    }
}