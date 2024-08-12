package back.adapter.out.persistence.repository.announcement;

import back.adapter.out.persistence.entity.announcement.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnnouncementJpaRepository extends JpaRepository<AnnouncementEntity, Integer> {

    @Query("SELECT u from AnnouncementEntity u WHERE u.announcementName = :name")
    Optional<List<AnnouncementEntity>> findAnnouncementsByName(@Param("name")String name);

    @Query("SELECT u from AnnouncementEntity u WHERE u.announcerName = :name")
    Optional<List<AnnouncementEntity>> findAnnouncementsByAnnouncerName(@Param("name")String name);

    @Query("SELECT u from AnnouncementEntity u WHERE u.announcementPrice = :price")
    Optional<List<AnnouncementEntity>> findAnnouncementByPrice(@Param("price") BigDecimal price);
}
