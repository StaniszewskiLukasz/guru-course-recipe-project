package guru.spring.course.recipe.controllers;

import guru.spring.course.recipe.domain.Recipe;
import guru.spring.course.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Łukasz Staniszewski on 2020-02-26
 * @project recipe
 */
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService=recipeService;
    }


    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){
        Recipe recipeById = recipeService.getRecipeById(new Long(id));
        model.addAttribute("recipe", recipeById);
        return "recipe/show";
    }
}
