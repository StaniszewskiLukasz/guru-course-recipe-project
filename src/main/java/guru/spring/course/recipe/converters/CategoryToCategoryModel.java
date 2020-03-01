package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.Category;
import guru.spring.course.recipe.models.CategoryModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-03-01
 * @project recipe
 */
@Component
public class CategoryToCategoryModel implements Converter<Category, CategoryModel> {

    @Synchronized
    @Nullable
    @Override
    public CategoryModel convert(Category category) {
        if(category==null){
         throw new IllegalArgumentException("Category can not be null");
        }
        CategoryModel model = new CategoryModel();
        model.setId(category.getId());
        model.setDescription(category.getDescription());
        return model;
    }
}
