package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.RecipeModelToRecipe;
import guru.spring.course.recipe.converters.RecipeToRecipeModel;
import guru.spring.course.recipe.dto.Recipe;
import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Łukasz Staniszewski on 2020-02-13
 * @project recipe
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeModelToRecipe recipeModelToRecipe;
    private final RecipeToRecipeModel recipeToRecipeModel;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeModelToRecipe recipeModelToRecipe, RecipeToRecipeModel recipeToRecipeModel) {
        this.recipeRepository = recipeRepository;
        this.recipeModelToRecipe = recipeModelToRecipe;
        this.recipeToRecipeModel = recipeToRecipeModel;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        return optionalRecipe.orElseThrow(() -> new RuntimeException("Recipe object can not be null"));
    }

    @Override
    @Transactional
    public RecipeModel saveRecipeModel(RecipeModel model) {
        Recipe convert = recipeModelToRecipe.convert(model);
        Recipe save = recipeRepository.save(convert);
        return recipeToRecipeModel.convert(save);
    }

    @Override
    @Transactional
    public RecipeModel findRecipeModelById(Long id) {
        return recipeToRecipeModel.convert(getRecipeById(id));
    }

    @Override
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }


}
