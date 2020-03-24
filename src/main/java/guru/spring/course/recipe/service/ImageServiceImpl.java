package guru.spring.course.recipe.service;

import guru.spring.course.recipe.models.RecipeModel;
import guru.spring.course.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Łukasz Staniszewski on 2020-03-23
 * @project recipe
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {

        try {
            RecipeModel recipeModel = recipeRepository.findById(recipeId).get();
            Byte[] byteObject = new Byte[file.getBytes().length];
            int i = 0;
            for (byte fileByte : file.getBytes()) {
               byteObject[i++] =  fileByte;
            }
            recipeModel.setImage(byteObject);
            recipeRepository.save(recipeModel);
        } catch (IOException e) {
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}
