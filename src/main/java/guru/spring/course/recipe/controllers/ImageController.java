package guru.spring.course.recipe.controllers;

import guru.spring.course.recipe.service.ImageService;
import guru.spring.course.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Łukasz Staniszewski on 2020-03-23
 * @project recipe
 */
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String showUploadForm(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(recipeId)));
        return "recipe/imageuploadForm";
    }

    @PostMapping("recipe/{recipeId}/image")
    public String handleImagePost(@PathVariable String recipeId, @RequestParam("imageFile") MultipartFile image) {
        imageService.saveImageFile(Long.valueOf(recipeId), image);
        return "redirect:/recipe" + recipeId + "/show";
    }
}
