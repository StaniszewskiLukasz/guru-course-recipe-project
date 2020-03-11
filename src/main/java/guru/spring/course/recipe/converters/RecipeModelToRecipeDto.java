package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.RecipeDto;
import guru.spring.course.recipe.models.RecipeModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-03-04
 * @project recipe
 */
@Component
public class RecipeModelToRecipeDto implements Converter<RecipeModel, RecipeDto> {

    IngredientModelToIngredientDto ingredientConverter;
    NotesModelToNotesDto notesConverter;
    CategoryModelToCategoryDto categoryConverter;

    public RecipeModelToRecipeDto(IngredientModelToIngredientDto ingredientConverter,
                                  NotesModelToNotesDto notesConverter,
                                  CategoryModelToCategoryDto categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Override
    public RecipeDto convert(RecipeModel recipeModel) {
        if(recipeModel == null){
            throw new IllegalArgumentException("RecipeModel can not be null");
        }
        RecipeDto recipe = new RecipeDto();
        recipe.setId(recipeModel.getId());
        recipe.setCookTime(recipeModel.getCookTime());
        recipe.setDescription(recipeModel.getDescription());
        recipe.setDifficultyModel(recipeModel.getDifficultyModel());
        recipe.setDirections(recipeModel.getDirections());
        recipe.setPrepTime(recipeModel.getPrepTime());
        recipe.setServings(recipeModel.getServings());
        recipe.setSource(recipeModel.getSource());
        recipe.setUrl(recipeModel.getUrl());
        if(recipeModel.getNotesModel()!=null){
            recipe.setNotes(notesConverter.convert(recipeModel.getNotesModel()));
        }
        if(recipeModel.getIngredientModels()!=null&& recipeModel.getIngredientModels().size()>0) {
            recipeModel.getIngredientModels()
                    .forEach(ingredientModel -> recipe
                            .getIngredients().add(ingredientConverter.convert(ingredientModel)));
        }
        if(recipeModel.getCategories()!=null&& recipeModel.getCategories().size()>0) {
            recipeModel.getCategories()
                    .forEach(categoryModel -> recipe
                            .getCategories()
                            .add(categoryConverter.convert(categoryModel)));
        }
        return recipe;
    }
}
