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
public class CategoryModelToCategory implements Converter<CategoryModel, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryModel categoryModel) {
        if(categoryModel==null){
            throw new IllegalArgumentException("CategoryModel can not be null");
        }
        Category category = new Category();
        category.setId(categoryModel.getId());
        category.setDescription(categoryModel.getDescription());
        return category;
    }
}
