package guru.spring.course.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Łukasz Staniszewski on 2020-02-07
 * @project recipe
 */
@Controller
public class IndexController {

    @RequestMapping({"/index","","/"})
    public String getIndexPage() {
        System.out.println("Coś się zmienia11111222");
        return "index";
    }

}
