package back.adapter.in.web.controller.announcement.dto;

import back.domain.model.announcement.Announcement;
import back.domain.model.product.Product;

import java.util.List;

public record FindAnnouncementIfContainsResponseDto(List<Announcement> announces) {
}
