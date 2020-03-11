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
public class CategoryDtoToCategoryModel implements Converter<CategoryDto, CategoryModel> {

    @Synchronized
    @Override
    public CategoryModel convert(CategoryDto categoryDto) {
        if(categoryDto ==null){
            throw new IllegalArgumentException("CategoryModel can not be null");
        }
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(categoryDto.getId());
        categoryModel.setDescription(categoryDto.getDescription());
        return categoryModel;
    }
}
