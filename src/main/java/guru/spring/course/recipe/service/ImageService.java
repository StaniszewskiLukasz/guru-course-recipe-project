package guru.spring.course.recipe.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Łukasz Staniszewski on 2020-03-23
 * @project recipe
 */
public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile file);
}
