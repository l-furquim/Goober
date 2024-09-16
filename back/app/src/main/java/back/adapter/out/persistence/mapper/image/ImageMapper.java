package back.adapter.out.persistence.mapper.image;

import back.adapter.out.persistence.entity.image.ImageEntity;
import back.domain.model.image.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public Image toDomain(ImageEntity entity){
        return new Image(entity.getId(),entity.getFileName(),entity.getContentType(),entity.getData());
    }

    public ImageEntity toEntity(Image image){
        return new ImageEntity(image.getId(),image.getFileName(),image.getContentType(),image.getData());
    }


}
