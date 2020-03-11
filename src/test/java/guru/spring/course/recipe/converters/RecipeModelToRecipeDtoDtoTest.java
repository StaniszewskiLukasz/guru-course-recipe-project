package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.RecipeDto;
import guru.spring.course.recipe.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Łukasz Staniszewski on 2020-03-04
 * @project recipe
 */
class RecipeModelToRecipeDtoDtoTest {

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
    RecipeModelToRecipeDto converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeModelToRecipeDto(
                new IngredientModelToIngredientDto(new UnitOfMeasureModelToUnitOfMeasureDto()),
                new NotesModelToNotesDto(),
                new CategoryModelToCategoryDto());
    }

    @Test
    void checkIfObjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(null);
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
        recipeModel.setDifficultyModel(DIFFICULTY_MODEL);
        recipeModel.setDirections(DIRECTIONS);
        recipeModel.setServings(SERVINGS);
        recipeModel.setSource(SOURCE);
        recipeModel.setUrl(URL);

        NotesModel notesModel = new NotesModel();
        notesModel.setId(NOTES_ID);

        recipeModel.setNotesModel(notesModel);

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(CAT_ID_1);

        CategoryModel categoryModel2 = new CategoryModel();
        categoryModel2.setId(CAT_ID2);

        recipeModel.getCategories().add(categoryModel);
        recipeModel.getCategories().add(categoryModel2);

        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setId(INGRED_ID_1);

        IngredientModel ingredientModel2 = new IngredientModel();
        ingredientModel2.setId(INGRED_ID_2);

        UnitOfMeasureModel unitOfMeasureModel = new UnitOfMeasureModel();
        unitOfMeasureModel.setId(ID);
        unitOfMeasureModel.setDescription("Opis ilości");
        ingredientModel.setUnitOfMeasureModel(unitOfMeasureModel);
        ingredientModel2.setUnitOfMeasureModel(unitOfMeasureModel);
        recipeModel.getIngredientModels().add(ingredientModel);
        recipeModel.getIngredientModels().add(ingredientModel2);

        //when
        RecipeDto model  = converter.convert(recipeModel);

        assertNotNull(model);
        assertEquals(RECIPE_ID, model.getId());
        assertEquals(COOK_TIME, model.getCookTime());
        assertEquals(PREP_TIME, model.getPrepTime());
        assertEquals(DESCRIPTION, model.getDescription());
        assertEquals(DIFFICULTY_MODEL, model.getDifficultyModel());
        assertEquals(DIRECTIONS, model.getDirections());
        assertEquals(SERVINGS, model.getServings());
        assertEquals(SOURCE, model.getSource());
        assertEquals(URL, model.getUrl());
        assertEquals(NOTES_ID, model.getNotes().getId());
        assertEquals(2, model.getCategories().size());
        assertEquals(2, model.getIngredients().size());
    }
}