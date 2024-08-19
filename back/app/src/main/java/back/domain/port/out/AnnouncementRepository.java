package back.domain.port.out;

import back.domain.model.announcement.Announcement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository {
    void save(Announcement announcement);
    void delete(Announcement announcement);
    Optional<Announcement> findAnnouncementById(Integer id);
    Optional<List<Announcement>> findAnnouncementsByName(String name);
    Optional<List<Announcement>> findAnnouncementsByAnnouncerName(String name);
    Optional<List<Announcement>> findAnnouncementsByPrice(BigDecimal price);
    Optional<List<Announcement>> findAnnouncementByUpperName(String name);
    List<Announcement> findAll();
}
