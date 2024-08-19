package back.adapter.out.persistence.mapper.announcement;

import back.adapter.out.persistence.entity.announcement.AnnouncementEntity;
import back.adapter.out.persistence.mapper.product.ProductMapper;
import back.adapter.out.persistence.mapper.question.QuestionMapper;
import back.domain.model.announcement.Announcement;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AnnouncementMapper {
    public Optional<AnnouncementEntity> toEntity(Announcement announcement){
        return Optional.of(new AnnouncementEntity(
              announcement.getAnnouncementPrice(),
                announcement.getAnnouncementName(),
                announcement.getAnnouncementLikes(),
                announcement.getAnnouncementQuestions(),
                announcement.getAnnouncerId(),
                announcement.getProductImages(),
                ProductMapper.productToEntityList(announcement.getProducts())
        ));
    }




    public Optional<Announcement> toDomain(AnnouncementEntity announcement){
        return Optional.of(new Announcement(
                announcement.getAnnouncementEntityPrice(),
                announcement.getAnnouncementEntityName(),
                announcement.getAnnouncementEntityLikes(),
                announcement.getAnnouncementEntityQuestions(),
                announcement.getAnnouncerId(),
                announcement.getProductImages(),
                ProductMapper.productEntityToDomainList(announcement.getProducts())
        ));
    }



}
