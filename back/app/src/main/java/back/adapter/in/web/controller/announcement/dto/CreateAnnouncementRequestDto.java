package back.adapter.in.web.controller.announcement.dto;

import back.domain.model.product.Product;

import java.math.BigDecimal;
import java.util.List;

public record CreateAnnouncementRequestDto(
        String name, String imagesPath, Double price, String announcerId, List<Product> products) {
}
