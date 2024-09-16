package back.application.service.image;

import back.domain.exception.ImageException;
import back.domain.model.image.Image;
import back.domain.port.in.ImageService;
import back.domain.port.out.ImageRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final GridFsTemplate template;

    private static String UPLOAD_DIR = "src/main/resources/static/images/user/";


    public ImageServiceImpl(ImageRepository imageRepository, GridFsTemplate template) {
        this.imageRepository = imageRepository;
        this.template = template;
    }

    @Override
    public String saveImage(MultipartFile file) {
        try {
            Image image = new Image(
                    UUID.randomUUID().toString(),
                    file.getOriginalFilename(),
                    file.getOriginalFilename(),
                    file.getBytes()
            );

            imageRepository.save(image);

            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());

            Files.write(path , file.getBytes());
            return UPLOAD_DIR + file.getOriginalFilename();

        }catch (IOException e){
            throw new ImageException("erro ao salvar a imagem: " + e.getMessage() + e.getCause());
        }

        }

    @Override
    public Image findImageById(String id) {
        var image =  imageRepository.findById(id);

        return image;
    }

    @Override
    public void deleteImage(String imageId) {
        imageRepository.delete(imageId);
    }
}
