package back.adapter.in.web.controller.announcement.dto;

import java.util.UUID;

public record UpdateAnnouncementPriceRequestDto(UUID id, Float newPrice) {
}
