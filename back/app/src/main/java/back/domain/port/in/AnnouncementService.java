package back.domain.port.in;

import back.adapter.in.web.controller.announcement.dto.CreateAnnouncementRequestDto;
import back.adapter.in.web.controller.announcement.dto.DeleteAnnouncementRequestDto;
import back.adapter.in.web.controller.announcement.dto.UpdateAnnouncementNameRequestDto;
import back.adapter.in.web.controller.announcement.dto.UpdateAnnouncementPriceRequestDto;
import back.domain.model.announcement.Announcement;

import java.util.List;
import java.util.Optional;

public interface AnnouncementService {

    Announcement createAnnouncement(CreateAnnouncementRequestDto createAnnouncementRequestDto);
    void deleteAnnouncement(DeleteAnnouncementRequestDto deleteAnnouncementRequestDto);
    void updateAnnouncementPrice(UpdateAnnouncementPriceRequestDto updateAnnouncementPriceRequestDto);
    void updateAnnouncementName(UpdateAnnouncementNameRequestDto updateAnnouncementNameRequestDto);
    List<Announcement> findAnnouncementIfContains(String something);
    Optional<List<Announcement>> findAnnouncementByAnnouncerName(String name);



}
