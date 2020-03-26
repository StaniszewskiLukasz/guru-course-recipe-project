package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.RecipeDtoToRecipeModel;
import guru.spring.course.recipe.converters.RecipeModelToRecipeDto;
import guru.spring.course.recipe.dto.RecipeDto;
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
    private final RecipeDtoToRecipeModel dtoConverter;
    private final RecipeModelToRecipeDto modelConverter;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeDtoToRecipeModel dtoConverter, RecipeModelToRecipeDto modelConverter) {
        this.recipeRepository = recipeRepository;
        this.dtoConverter = dtoConverter;
        this.modelConverter = modelConverter;
    }

    @Override
    public Set<RecipeModel> getRecipes() {
        log.debug("I'm in the service");

        Set<RecipeModel> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public RecipeModel findById(Long l) {

        Optional<RecipeModel> recipeOptional = recipeRepository.findById(l);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeDto findCommandById(Long l) {
        return modelConverter.convert(findById(l));
    }

    @Override
    @Transactional
    public RecipeDto saveRecipeCommand(RecipeDto command) {
        RecipeModel detachedRecipe = dtoConverter.convert(command);

        RecipeModel savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return modelConverter.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }
}
