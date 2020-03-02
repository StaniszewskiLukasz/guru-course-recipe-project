package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.Recipe;
import guru.spring.course.recipe.models.RecipeModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-02-29
 * @project recipe
 */
@Component
public class RecipeModelToRecipe implements Converter<RecipeModel, Recipe> {

    IngredientModelToIngredient ingredientConverter;
    NotesModelToNotes notesConverter;
    CategoryModelToCategory categoryConverter;

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeModel recipeModel) {
        if(recipeModel==null){
            throw new IllegalArgumentException("RecipeModel can not be null");
        }
        Recipe recipe = new Recipe();
        recipe.setId(recipeModel.getId());
        recipe.setCookTime(recipeModel.getCookTime());
        recipe.setDescription(recipeModel.getDescription());
        recipe.setDifficulty(recipeModel.getDifficulty());
        recipe.setDirections(recipeModel.getDirections());
        recipe.setPrepTime(recipeModel.getPrepTime());
        recipe.setServings(recipeModel.getServings());
        recipe.setSource(recipeModel.getSource());
        recipe.setUrl(recipeModel.getUrl());
        recipe.setNotes(notesConverter.convert(recipeModel.getNotes()));
        if(recipeModel.getIngredients()!=null&& recipeModel.getIngredients().size()>0) {
            recipeModel.getIngredients()
                    .forEach(ingredientModel -> recipe
                            .getIngredients().add(ingredientConverter.convert(ingredientModel)));
        }
       if(recipeModel.getCategories()!=null&&recipeModel.getCategories().size()>0) {
           recipeModel.getCategories()
                   .forEach(categoryModel -> recipe
                           .getCategories()
                           .add(categoryConverter.convert(categoryModel)));
       }
        return recipe;
    }
}
