package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.*;
import guru.spring.course.recipe.models.DifficultyModel;
import guru.spring.course.recipe.models.RecipeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-03-02
 * @project recipe
 */
class RecipeModelToRecipeDtoTestModel {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final DifficultyModel DIFFICULTY_MODEL = DifficultyModel.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    public static final long ID = 1L;
    public static final long ID_CAT = 2L;
    public static final long ID_NOT = 3L;
    RecipeDtoToRecipeModel converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeDtoToRecipeModel(
                new IngredientDtoToIngredientModel(new UnitOfMeasureDtoToUnitOfMeasureModel()),
                new NotesDtoToNotesModel(),
                new CategoryDtoToCategoryModel());
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        assertNotNull(converter.convert(new RecipeDto()));
    }

    @Test
    void convert() {
        //given
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(RECIPE_ID);
        recipeDto.setCookTime(COOK_TIME);
        recipeDto.setPrepTime(PREP_TIME);
        recipeDto.setDescription(DESCRIPTION);
        recipeDto.setDifficultyModel(DIFFICULTY_MODEL);
        recipeDto.setDirections(DIRECTIONS);
        recipeDto.setServings(SERVINGS);
        recipeDto.setSource(SOURCE);
        recipeDto.setUrl(URL);

        NotesDto notes = new NotesDto();
        notes.setId(NOTES_ID);

        recipeDto.setNotes(notes);

        CategoryDto category = new CategoryDto();
        category.setId(CAT_ID_1);

        CategoryDto category2 = new CategoryDto();
        category2.setId(CAT_ID2);

        recipeDto.getCategories().add(category);
        recipeDto.getCategories().add(category2);

        IngredientDto ingredient = new IngredientDto();
        ingredient.setId(INGRED_ID_1);

        IngredientDto ingredient2 = new IngredientDto();
        ingredient2.setId(INGRED_ID_2);

        UnitOfMeasureDto measureModel = new UnitOfMeasureDto();
        measureModel.setId(ID);
        measureModel.setDescription("Opis ilości");
        ingredient.setUnitOfMeasure(measureModel);
        ingredient2.setUnitOfMeasure(measureModel);
        recipeDto.getIngredients().add(ingredient);
        recipeDto.getIngredients().add(ingredient2);

        //when
        RecipeModel recipeModel = converter.convert(recipeDto);

        assertNotNull(recipeModel);
        assertEquals(RECIPE_ID, recipeModel.getId());
        assertEquals(COOK_TIME, recipeModel.getCookTime());
        assertEquals(PREP_TIME, recipeModel.getPrepTime());
        assertEquals(DESCRIPTION, recipeModel.getDescription());
        assertEquals(DIFFICULTY_MODEL, recipeModel.getDifficultyModel());
        assertEquals(DIRECTIONS, recipeModel.getDirections());
        assertEquals(SERVINGS, recipeModel.getServings());
        assertEquals(SOURCE, recipeModel.getSource());
        assertEquals(URL, recipeModel.getUrl());
        assertEquals(NOTES_ID, recipeModel.getNotesModel().getId());
        assertEquals(2, recipeModel.getCategories().size());
        assertEquals(2, recipeModel.getIngredientModels().size());
    }
}