package guru.spring.course.recipe.controllers;

import guru.spring.course.recipe.dto.Recipe;
import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Łukasz Staniszewski on 2020-02-26
 * @project recipe
 */
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        Recipe recipeById = recipeService.getRecipeById(new Long(id));
        model.addAttribute("recipe", recipeById);
        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeModel());
        return "recipe/recipeForm";
    }

    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findRecipeModelById(new Long(id)));
        return "recipe/recipeForm";
    }

    @PostMapping
    @RequestMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeModel recipeModel) {
        RecipeModel savedModel = recipeService.saveRecipeModel(recipeModel);
        return "redirect:/recipe/" + savedModel.getId() + "/show";
    }

    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        recipeService.deleteRecipeById(Long.valueOf(id));
        return "redirect:/";
    }
}
