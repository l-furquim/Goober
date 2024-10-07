package back.adapter.in.web.controller.announcement.dto;

import back.domain.model.product.Product;

import java.util.List;

public record   CreateAnnouncementRequestDto(
        String announcementName, String announcementDescription,Double announcementPrice,
        String announcementCategorie ,String announcerToken, String announcementStreet,
        Integer announcementNumber, String announcementState, String announcementDistrict) {
}

