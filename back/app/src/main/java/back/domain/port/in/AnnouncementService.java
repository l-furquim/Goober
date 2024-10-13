package back.domain.port.in;

import back.adapter.in.web.controller.announcement.dto.CreateAnnouncementRequestDto;
import back.adapter.in.web.controller.announcement.dto.DeleteAnnouncementRequestDto;
import back.adapter.in.web.controller.announcement.dto.UpdateAnnouncementNameRequestDto;
import back.adapter.in.web.controller.announcement.dto.UpdateAnnouncementPriceRequestDto;
import back.domain.model.announcement.Announcement;
import back.domain.model.product.Product;
import back.domain.model.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface AnnouncementService {

    Announcement createAnnouncement(
            CreateAnnouncementRequestDto createAnnouncementRequestDto, User announcer, String imagesPath, Product product);

    void deleteAnnouncement(DeleteAnnouncementRequestDto deleteAnnouncementRequestDto);
    void updateAnnouncementPrice(UpdateAnnouncementPriceRequestDto updateAnnouncementPriceRequestDto);
    void updateAnnouncementName(UpdateAnnouncementNameRequestDto updateAnnouncementNameRequestDto);
    List<Announcement> findAnnouncementNamePriceFilter(String something, Double lowPrice, Double highPrice);
    Optional<List<Announcement>> findAnnouncementByAnnouncerName(String name);
    List<Announcement> findAll();
    List<Announcement> findAnnouncementNameFilter(String word);
    Announcement findAnnouncementById(String id);

}
