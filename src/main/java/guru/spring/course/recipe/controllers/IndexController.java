package guru.spring.course.recipe.controllers;

import guru.spring.course.recipe.domain.Category;
import guru.spring.course.recipe.domain.UnitOfMeasure;
import guru.spring.course.recipe.repositories.CategoryRepository;
import guru.spring.course.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author Łukasz Staniszewski on 2020-02-07
 * @project recipe
 */
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"/index","","/"})
    public String getIndexPage() {
        Optional<Category> american = categoryRepository.findByDescription("Mexican");
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category id is: " + american.get().getId());
        System.out.println("unit id is: " + teaspoon.get().getId());
        return "index";
    }

}
