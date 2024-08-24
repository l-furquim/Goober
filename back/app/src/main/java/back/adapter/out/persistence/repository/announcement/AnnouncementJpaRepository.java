package back.adapter.out.persistence.repository.announcement;

import back.adapter.out.persistence.entity.announcement.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnnouncementJpaRepository extends JpaRepository<AnnouncementEntity, UUID> {

    @Query("SELECT u from AnnouncementEntity u WHERE u.announcementName = :name")
    Optional<List<AnnouncementEntity>> findAnnouncementsByName(@Param("name")String name);


    @Query("SELECT a FROM AnnouncementEntity a WHERE UPPER(a.announcementName) LIKE UPPER(CONCAT('%', :name, '%')) AND a.announcementPrice BETWEEN :lowPrice AND :highPrice")
    Optional<List<AnnouncementEntity>> findAnnouncementsByUpperNameAndPrice(@Param("name") String name, @Param("lowPrice") BigDecimal lowPrice ,@Param("highPrice") BigDecimal highPrice);



    @Query("SELECT u from AnnouncementEntity u WHERE u.announcerName = :name")
    Optional<List<AnnouncementEntity>> findAnnouncementsByAnnouncerName(@Param("name")String name);

    @Query("SELECT u from AnnouncementEntity u WHERE u.announcementPrice = :price")
    Optional<List<AnnouncementEntity>> findAnnouncementByPrice(@Param("price") BigDecimal price);

    @Query("SELECT a.announcementName, a.announcementPrice, a.announcementId, a.announcementPrice, a.announcementLikes, a.announcementQuestions, a.announcerName, a.productImages FROM AnnouncementEntity a WHERE a.announcementName LIKE %:name%")
    List<AnnouncementEntity> findAnnouncementNoFrills();

}
