package back.application.service.image;

import back.domain.exception.ImageException;
import back.domain.model.image.Image;
import back.domain.port.in.ImageService;
import back.domain.port.out.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);
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
    @CacheEvict(value = "announcementImages", allEntries = true)
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

    @Override
    @Cacheable("announcementImages")
    public byte[] findImageByDirName(String dirName) {
        Path dirPath = Paths.get(UPLOAD_DIR, dirName);

        if (Files.exists(dirPath) && Files.isDirectory(dirPath)) {
            try {

                List<Path> imagePaths = Files.list(dirPath)
                        .filter(Files::isRegularFile)
                        .filter(path -> {

                            String fileName = path.getFileName().toString().toLowerCase();
                            return fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg") || fileName.endsWith(".webp");
                        })
                        .collect(Collectors.toList());

                if (imagePaths.isEmpty()) {
                    throw new ImageException("Nenhuma imagem encontrada no diretório: " + dirPath.toString());
                }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                for (Path imagePath : imagePaths) {
                    byte[] imageBytes = Files.readAllBytes(imagePath);
                    outputStream.write(imageBytes);
                }

                return outputStream.toByteArray();

            } catch (IOException e) {
                throw new ImageException("Erro ao processar as imagens no diretório: " + dirName + " - " + e.getMessage());
            }

        } else {
            throw new ImageException("Diretório não encontrado: " + dirPath.toString());
        }
    }



}
