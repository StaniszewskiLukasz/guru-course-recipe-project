package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.Notes;
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

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeModel recipeModel) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeModel.getId());
        recipe.setCookTime(recipeModel.getCookTime());
        recipe.setDescription(recipeModel.getDescription());
        recipe.setDifficulty(recipeModel.getDifficulty());
        recipe.setDirections(recipeModel.getDirections());
        Notes notes = new Notes();
        notes.setId(recipeModel.getNotes().getId());
        notes.setRecipeNotes(recipeModel.getNotes().getRecipeNotes());

        return null;
    }
}
