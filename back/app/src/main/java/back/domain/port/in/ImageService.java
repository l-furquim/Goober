package back.domain.port.in;

import back.domain.model.image.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    String saveImage(MultipartFile file, String rootPath);
    Image findImageById(String id);
    void deleteImage(String id);
    String saveMultipleImages(List<MultipartFile> images, String rootPath);
}
