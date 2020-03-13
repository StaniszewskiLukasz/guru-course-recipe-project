package guru.spring.course.recipe.controllers;

import guru.spring.course.recipe.service.IngredientService;
import guru.spring.course.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Łukasz Staniszewski on 2020-03-08
 * @project recipe
 */
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService=recipeService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String ingredientsList(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe",recipeService.getRecipeById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                 @PathVariable String ingredientId,
                                 Model model){
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId)
                        ,Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }
}
