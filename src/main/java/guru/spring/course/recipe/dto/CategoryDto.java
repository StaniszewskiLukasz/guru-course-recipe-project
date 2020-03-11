package guru.spring.course.recipe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Łukasz Staniszewski on 2020-02-28
 * @project recipe
 */
@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String description;

}
