package back.adapter.out.persistence.repository.image;

import back.adapter.out.persistence.entity.image.ImageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageJpaRepository extends MongoRepository<ImageEntity, String> {


}
