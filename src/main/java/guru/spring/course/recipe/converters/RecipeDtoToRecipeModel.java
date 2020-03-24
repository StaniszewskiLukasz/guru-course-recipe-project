package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.RecipeDto;
import guru.spring.course.recipe.models.RecipeModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-02-29
 * @project recipe
 */
@Component
public class RecipeDtoToRecipeModel implements Converter<RecipeDto, RecipeModel> {

    IngredientDtoToIngredientModel ingredientConverter;
    NotesDtoToNotesModel notesConverter;
    CategoryDtoToCategoryModel categoryConverter;

    public RecipeDtoToRecipeModel(IngredientDtoToIngredientModel ingredientConverter,
                                  NotesDtoToNotesModel notesConverter,
                                  CategoryDtoToCategoryModel categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
//    @Nullable
    @Override
    public RecipeModel convert(RecipeDto recipeDto) {

        if (recipeDto == null) {
            throw new IllegalArgumentException("RecipeModel can not be null");
//            return null;
        }
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setId(recipeDto.getId());
        recipeModel.setCookTime(recipeDto.getCookTime());
        recipeModel.setDescription(recipeDto.getDescription());
        recipeModel.setDifficultyModel(recipeDto.getDifficultyModel());
        recipeModel.setDirections(recipeDto.getDirections());
        recipeModel.setPrepTime(recipeDto.getPrepTime());
        recipeModel.setServings(recipeDto.getServings());
        recipeModel.setSource(recipeDto.getSource());
        recipeModel.setUrl(recipeDto.getUrl());
        recipeModel.setImage(recipeDto.getImage());
        if (recipeDto.getNotes() != null) {
            recipeModel.setNotesModel(notesConverter.convert(recipeDto.getNotes()));
        }
        if (recipeDto.getIngredients() != null && recipeDto.getIngredients().size() > 0) {
            recipeDto.getIngredients()
                    .forEach(ingredientModel -> recipeModel
                            .getIngredientModels().add(ingredientConverter.convert(ingredientModel)));
        }
        if (recipeDto.getCategories() != null && recipeDto.getCategories().size() > 0) {
            recipeDto.getCategories()
                    .forEach(categoryModel -> recipeModel
                            .getCategories()
                            .add(categoryConverter.convert(categoryModel)));
        }
        return recipeModel;
    }
}
