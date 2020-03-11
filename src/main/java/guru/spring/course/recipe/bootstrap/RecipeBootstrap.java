package guru.spring.course.recipe.bootstrap;

import guru.spring.course.recipe.models.*;
import guru.spring.course.recipe.repositories.CategoryRepository;
import guru.spring.course.recipe.repositories.RecipeRepository;
import guru.spring.course.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Łukasz Staniszewski on 2020-02-13
 * @project recipe
 */
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional // to sprawi że dana metoda będzie działać i wywoływać obikety w ramach jednej transakcji. I nie dojdzie do sytuacji że w jednej transakcji zapisujemy dane a w innej chemy je pobrać i tam ich nie ma
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
        log.info("Loading bootstrap data");
    }

    private List<RecipeModel> getRecipes() {

        List<RecipeModel> recipeModels = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasureModel> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasureModel> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasureModel> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasureModel> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasureModel> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasureModel> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasureModel eachUom = eachUomOptional.get();
        UnitOfMeasureModel tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasureModel teapoonUom = tableSpoonUomOptional.get();
        UnitOfMeasureModel dashUom = dashUomOptional.get();
        UnitOfMeasureModel pintUom = dashUomOptional.get();
        UnitOfMeasureModel cupsUom = cupsUomOptional.get();

        //get Categories
        Optional<CategoryModel> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<CategoryModel> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        CategoryModel americanCategoryModel = americanCategoryOptional.get();
        CategoryModel mexicanCategoryModel = mexicanCategoryOptional.get();

        //Yummy Guac
        RecipeModel guacRecipeModel = new RecipeModel();
        guacRecipeModel.setDescription("Perfect Guacamole");
        guacRecipeModel.setPrepTime(10);
        guacRecipeModel.setCookTime(0);
        guacRecipeModel.setDifficultyModel(DifficultyModel.EASY);
        guacRecipeModel.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        NotesModel guacNotesModel = new NotesModel();
        guacNotesModel.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

        guacRecipeModel.setNotesModel(guacNotesModel);

        //very redundent - could add helper method, and make this simpler
        guacRecipeModel.addIngredient(new IngredientModel("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipeModel.addIngredient(new IngredientModel("Kosher salt", new BigDecimal(".5"), teapoonUom));
        guacRecipeModel.addIngredient(new IngredientModel("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
        guacRecipeModel.addIngredient(new IngredientModel("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
        guacRecipeModel.addIngredient(new IngredientModel("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipeModel.addIngredient(new IngredientModel("Cilantro", new BigDecimal(2), tableSpoonUom));
        guacRecipeModel.addIngredient(new IngredientModel("freshly grated black pepper", new BigDecimal(2), dashUom));
        guacRecipeModel.addIngredient(new IngredientModel("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        guacRecipeModel.getCategories().add(americanCategoryModel);
        guacRecipeModel.getCategories().add(mexicanCategoryModel);

        guacRecipeModel.setUrl("www.przepis1.com");
        guacRecipeModel.setServings(4);
        guacRecipeModel.setSource("Simple recipes");

        //add to return list
        recipeModels.add(guacRecipeModel);

        //Yummy Tacos
        RecipeModel tacosRecipeModel = new RecipeModel();
        tacosRecipeModel.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipeModel.setCookTime(9);
        tacosRecipeModel.setPrepTime(20);
        tacosRecipeModel.setDifficultyModel(DifficultyModel.MODERATE);

        tacosRecipeModel.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        NotesModel tacoNotesModel = new NotesModel();
        tacoNotesModel.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

        tacosRecipeModel.setNotesModel(tacoNotesModel);

        tacosRecipeModel.addIngredient(new IngredientModel("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom));
        tacosRecipeModel.addIngredient(new IngredientModel("Dried Oregano", new BigDecimal(1), teapoonUom));
        tacosRecipeModel.addIngredient(new IngredientModel("Dried Cumin", new BigDecimal(1), teapoonUom));
        tacosRecipeModel.addIngredient(new IngredientModel("Sugar", new BigDecimal(1), teapoonUom));
        tacosRecipeModel.addIngredient(new IngredientModel("Salt", new BigDecimal(".5"), teapoonUom));
        tacosRecipeModel.addIngredient(new IngredientModel("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom));
        tacosRecipeModel.addIngredient(new IngredientModel("finely grated orange zestr", new BigDecimal(1), tableSpoonUom));
        tacosRecipeModel.addIngredient(new IngredientModel("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom));
        tacosRecipeModel.addIngredient(new IngredientModel("Olive Oil", new BigDecimal(2), tableSpoonUom));
        tacosRecipeModel.addIngredient(new IngredientModel("boneless chicken thighs", new BigDecimal(4), tableSpoonUom));
        tacosRecipeModel.addIngredient(new IngredientModel("small corn tortillasr", new BigDecimal(8), eachUom));
        tacosRecipeModel.addIngredient(new IngredientModel("packed baby arugula", new BigDecimal(3), cupsUom));
        tacosRecipeModel.addIngredient(new IngredientModel("medium ripe avocados, slic", new BigDecimal(2), eachUom));
        tacosRecipeModel.addIngredient(new IngredientModel("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacosRecipeModel.addIngredient(new IngredientModel("cherry tomatoes, halved", new BigDecimal(".5"), pintUom));
        tacosRecipeModel.addIngredient(new IngredientModel("red onion, thinly sliced", new BigDecimal(".25"), eachUom));
        tacosRecipeModel.addIngredient(new IngredientModel("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacosRecipeModel.addIngredient(new IngredientModel("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupsUom));
        tacosRecipeModel.addIngredient(new IngredientModel("lime, cut into wedges", new BigDecimal(4), eachUom));

        tacosRecipeModel.getCategories().add(americanCategoryModel);
        tacosRecipeModel.getCategories().add(mexicanCategoryModel);

        recipeModels.add(tacosRecipeModel);
        return recipeModels;
    }
}
