package back.adapter.in.web.controller.announcement.dto;

import java.util.UUID;

public record UpdateAnnouncementNameRequestDto(UUID id, String newName) {
}
