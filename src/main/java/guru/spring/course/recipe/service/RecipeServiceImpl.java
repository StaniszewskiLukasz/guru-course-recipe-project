package guru.spring.course.recipe.service;

import guru.spring.course.recipe.converters.RecipeDtoToRecipeModel;
import guru.spring.course.recipe.converters.RecipeModelToRecipeDto;
import guru.spring.course.recipe.dto.RecipeDto;
import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Łukasz Staniszewski on 2020-02-13
 * @project recipe
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeDtoToRecipeModel recipeDtoToRecipeModel;
    private final RecipeModelToRecipeDto recipeModelToRecipeDto;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeDtoToRecipeModel recipeDtoToRecipeModel, RecipeModelToRecipeDto recipeModelToRecipeDto) {
        this.recipeRepository = recipeRepository;
        this.recipeDtoToRecipeModel = recipeDtoToRecipeModel;
        this.recipeModelToRecipeDto = recipeModelToRecipeDto;
    }

    @Override
    @Transactional
    public List<RecipeDto> getRecipes() {
        List<RecipeModel> recipeModels = recipeRepository.findAll();
        return recipeModels.stream().map(recipeModelToRecipeDto::convert).collect(Collectors.toList());
//        Set<RecipeModel> recipes = new HashSet<>();
//        recipeRepository.findAll()
//                .iterator()
//                .forEachRemaining(recipes::add);
//        Set<RecipeDto> collect = recipes.stream()
//                .map(recipeModelToRecipeDto::convert)
//                .collect(Collectors.toSet());
//        return collect;
    }

    @Override
    public RecipeDto getRecipeById(Long id) {
        Optional<RecipeModel> recipeOptional = recipeRepository.findById(id);
        RecipeModel model = recipeOptional.orElseThrow(() -> new RuntimeException("Recipe object can not be null"));
        return recipeModelToRecipeDto.convert(model);
    }

    @Override
    @Transactional
    public RecipeDto saveRecipe(RecipeDto recipe) {
        RecipeModel model = recipeDtoToRecipeModel.convert(recipe);
        RecipeModel savedRecipe = recipeRepository.save(model);
        return recipeModelToRecipeDto.convert(savedRecipe);
    }

    @Override
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }


}
