package back.application.service.image;

import back.domain.exception.ImageException;
import back.domain.model.image.Image;
import back.domain.port.in.ImageService;
import back.domain.port.out.ImageRepository;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final GridFsTemplate template;

    private static String UPLOAD_DIR = "src/main/resources/static/images/";


    public ImageServiceImpl(ImageRepository imageRepository, GridFsTemplate template) {
        this.imageRepository = imageRepository;
        this.template = template;
    }

    @Override
    public String saveImage(MultipartFile file, String rootPath) {
        try {
            Image image = new Image(
                    UUID.randomUUID().toString(),
                    file.getOriginalFilename(),
                    file.getOriginalFilename(),
                    file.getBytes()
            );

            imageRepository.save(image);

            Path path = Paths.get(UPLOAD_DIR + rootPath + file.getOriginalFilename());

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

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

    @Override
    public String saveMultipleImages(List<MultipartFile> images, String rootPath) {
            images.forEach(image-> {
                try {
                    var aImage = new Image(
                            UUID.randomUUID().toString(),
                            image.getOriginalFilename(),
                            image.getOriginalFilename(),
                            image.getBytes()
                    );
                    imageRepository.save(aImage);

                    Path pathTo = Paths.get(UPLOAD_DIR + rootPath + image.getOriginalFilename());

                    Path path = Paths.get(UPLOAD_DIR + rootPath);

                    if (!Files.exists(path)) {
                        Files.createDirectories(path);
                    }
                    Files.write(pathTo , image.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        return UPLOAD_DIR + rootPath;
    }
}
