package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.IngredientDtoToIngredientModel;
import guru.spring.course.recipe.converters.IngredientModelToIngredientDto;
import guru.spring.course.recipe.dto.IngredientDto;
import guru.spring.course.recipe.models.IngredientModel;
import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.repositories.RecipeRepository;
import guru.spring.course.recipe.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author Łukasz Staniszewski on 2020-03-13
 * @project recipe
 */
public class IngredientServiceImplTest {

    private final IngredientModelToIngredientDto ingredientModelToIngredientDto;
    private final IngredientDtoToIngredientModel ingredientDtoToIngredientModel;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    IngredientServiceImplTest(IngredientModelToIngredientDto ingredientModelToIngredientDto,
                              IngredientDtoToIngredientModel ingredientDtoToIngredientModel) {
        this.ingredientModelToIngredientDto = ingredientModelToIngredientDto;
        this.ingredientDtoToIngredientModel = ingredientDtoToIngredientModel;
    }


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository,
                ingredientModelToIngredientDto,ingredientDtoToIngredientModel, unitOfMeasureRepository);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setId(1L);

        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setId(1L);

        IngredientModel ingredientModel2 = new IngredientModel();
        ingredientModel2.setId(1L);

        IngredientModel ingredientModel3 = new IngredientModel();
        ingredientModel3.setId(3L);

        recipeModel.addIngredient(ingredientModel);
        recipeModel.addIngredient(ingredientModel2);
        recipeModel.addIngredient(ingredientModel3);

        Optional<RecipeModel> recipeOptional = Optional.of(recipeModel);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientDto ingredientDto = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        assertEquals(3L, ingredientDto.getId());
        assertEquals(1L, ingredientDto.getRecipeId());
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
        IngredientDto savedCommand = ingredientService.saveIngredient(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(RecipeModel.class));

    }
}