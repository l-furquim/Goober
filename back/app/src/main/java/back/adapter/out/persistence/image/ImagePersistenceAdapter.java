package back.adapter.out.persistence.image;

import back.adapter.out.persistence.mapper.image.ImageMapper;
import back.adapter.out.persistence.repository.image.ImageJpaRepository;
import back.domain.exception.ImageException;
import back.domain.model.image.Image;
import back.domain.port.out.ImageRepository;
import org.springframework.stereotype.Component;

@Component
public class ImagePersistenceAdapter implements ImageRepository {

    private final ImageJpaRepository imageJpaRepository;
    private final ImageMapper imageMapper;




    public ImagePersistenceAdapter(ImageJpaRepository imageJpaRepository, ImageMapper imageMapper) {
        this.imageJpaRepository = imageJpaRepository;
        this.imageMapper = imageMapper;
    }

    @Override
    public void save(Image image) {
        var imageEntity = imageMapper.toEntity(image);

        imageJpaRepository.save(imageEntity);
    }

    @Override
    public void delete(String id) {
        var imageEntity = imageJpaRepository.findById(id);



        if(imageEntity.isPresent()){
            imageJpaRepository.delete(imageEntity.get());
        }

    }

    @Override
    public Image findById(String id) {
        var imageEntity = imageJpaRepository.findById(id);

        if(imageEntity.isEmpty()){
            throw new ImageException("Imagem não encontrada!");
        }
        return imageMapper.toDomain(imageEntity.get());
    }
}
