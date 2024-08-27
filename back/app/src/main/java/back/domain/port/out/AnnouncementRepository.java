package back.domain.port.out;

import back.domain.model.announcement.Announcement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnnouncementRepository {
    void save(Announcement announcement);
    void delete(Announcement announcement);
    Optional<Announcement> findAnnouncementById(UUID id);
    Optional<List<Announcement>> findAnnouncementsByName(String name);
    Optional<List<Announcement>> findAnnouncementsByAnnouncerName(String name);
    Optional<List<Announcement>> findAnnouncementsByPrice(BigDecimal price);
    Optional<List<Announcement>> findAnnouncementNamePriceFilter(String name, Double lowPrice, Double highPrice);
    List<Announcement> findAll();
    List<Announcement> findAllWithFilter();
    Optional<List<Announcement>> findAnnouncewmentNameFilter(String name);
}
