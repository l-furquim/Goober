package back.domain.port.in;

import back.domain.model.image.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveImage(MultipartFile file);
    Image findImageById(String id);
    void deleteImage(String id);
}
