package guru.spring.course.recipe.controllers;

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

    public IngredientController(RecipeService recipeService) {
        this.recipeService=recipeService;
    }

    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String ingredientsList(@PathVariable String recipeId, Model model) throws Exception{
        model.addAttribute("recipe",recipeService.findRecipeModelById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }
}
