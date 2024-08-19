package back.adapter.in.web.controller.announcement.dto;

import back.domain.model.announcement.Announcement;

import java.util.List;

public record FindAllAnnouncementsResponseDto (List<Announcement> announces){
}
