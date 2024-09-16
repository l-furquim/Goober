package back.domain.port.out;

import back.domain.model.image.Image;

public interface ImageRepository {
    void save(Image image);
    void delete(String id);
    Image findById(String id);
}
