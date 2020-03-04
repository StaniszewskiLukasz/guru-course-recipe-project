package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.Recipe;
import guru.spring.course.recipe.models.RecipeModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-03-04
 * @project recipe
 */
@Component
public class RecipeToRecipeModel implements Converter<Recipe, RecipeModel> {

    IngredientToIngredientModel ingredientConverter;
    NotesToNotesModel notesConverter;
    CategoryToCategoryModel categoryConverter;

    public RecipeToRecipeModel(IngredientToIngredientModel ingredientConverter,
                               NotesToNotesModel notesConverter,
                               CategoryToCategoryModel categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Override
    public RecipeModel convert(Recipe recipe) {
        if(recipe==null){
            throw new IllegalArgumentException("RecipeModel can not be null");
        }
        RecipeModel model = new RecipeModel();
        model.setId(recipe.getId());
        model.setCookTime(recipe.getCookTime());
        model.setDescription(recipe.getDescription());
        model.setDifficulty(recipe.getDifficulty());
        model.setDirections(recipe.getDirections());
        model.setPrepTime(recipe.getPrepTime());
        model.setServings(recipe.getServings());
        model.setSource(recipe.getSource());
        model.setUrl(recipe.getUrl());
        model.setNotes(notesConverter.convert(recipe.getNotes()));
        if(recipe.getIngredients()!=null&& recipe.getIngredients().size()>0) {
            recipe.getIngredients()
                    .forEach(ingredientModel -> model
                            .getIngredients().add(ingredientConverter.convert(ingredientModel)));
        }
        if(recipe.getCategories()!=null&&recipe.getCategories().size()>0) {
            recipe.getCategories()
                    .forEach(categoryModel -> model
                            .getCategories()
                            .add(categoryConverter.convert(categoryModel)));
        }
        return model;
    }
}
