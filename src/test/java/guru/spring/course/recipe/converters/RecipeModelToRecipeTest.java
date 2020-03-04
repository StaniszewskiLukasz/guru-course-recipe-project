package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.Difficulty;
import guru.spring.course.recipe.dto.Recipe;
import guru.spring.course.recipe.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-03-02
 * @project recipe
 */
class RecipeModelToRecipeTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
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
    RecipeModelToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeModelToRecipe(
                new IngredientModelToIngredient(new UnitOfMeasureModelToUnitOfMeasure()),
                new NotesModelToNotes(),
                new CategoryModelToCategory());
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
        });
    }

    @Test
    void testIfObjectIsEmpty() {
        assertThrows(IllegalArgumentException.class,()->{
            converter.convert(new RecipeModel());
        });
    }

    @Test
    void convert() {
        //given
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setId(RECIPE_ID);
        recipeModel.setCookTime(COOK_TIME);
        recipeModel.setPrepTime(PREP_TIME);
        recipeModel.setDescription(DESCRIPTION);
        recipeModel.setDifficulty(DIFFICULTY);
        recipeModel.setDirections(DIRECTIONS);
        recipeModel.setServings(SERVINGS);
        recipeModel.setSource(SOURCE);
        recipeModel.setUrl(URL);

        NotesModel notes = new NotesModel();
        notes.setId(NOTES_ID);

        recipeModel.setNotes(notes);

        CategoryModel category = new CategoryModel();
        category.setId(CAT_ID_1);

        CategoryModel category2 = new CategoryModel();
        category2.setId(CAT_ID2);

        recipeModel.getCategories().add(category);
        recipeModel.getCategories().add(category2);

        IngredientModel ingredient = new IngredientModel();
        ingredient.setId(INGRED_ID_1);

        IngredientModel ingredient2 = new IngredientModel();
        ingredient2.setId(INGRED_ID_2);

        UnitOfMeasureModel measureModel = new UnitOfMeasureModel();
        measureModel.setId(ID);
        measureModel.setDescription("Opis ilości");
        ingredient.setUnitOfMeasure(measureModel);
        ingredient2.setUnitOfMeasure(measureModel);
        recipeModel.getIngredients().add(ingredient);
        recipeModel.getIngredients().add(ingredient2);

        //when
        Recipe recipe  = converter.convert(recipeModel);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}