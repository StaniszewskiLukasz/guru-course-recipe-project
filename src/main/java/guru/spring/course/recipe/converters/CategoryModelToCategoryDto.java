package guru.spring.course.recipe.converters;

import guru.spring.course.recipe.dto.CategoryDto;
import guru.spring.course.recipe.models.CategoryModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Łukasz Staniszewski on 2020-03-01
 * @project recipe
 */
@Component
public class CategoryModelToCategoryDto implements Converter<CategoryModel, CategoryDto> {

    @Synchronized
    @Override
    public CategoryDto convert(CategoryModel categoryModel) {
        if(categoryModel ==null){
         throw new IllegalArgumentException("Category can not be null");
        }
        CategoryDto model = new CategoryDto();
        model.setId(categoryModel.getId());
        model.setDescription(categoryModel.getDescription());
        return model;
    }
}
