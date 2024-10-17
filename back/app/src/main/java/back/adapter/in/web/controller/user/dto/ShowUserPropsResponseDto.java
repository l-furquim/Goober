package back.adapter.in.web.controller.user.dto;

import back.domain.model.cart.Cart;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.UUID;

public record ShowUserPropsResponseDto(
        String userId, String userEmail, String userName, String userImagePath
       ) {
}
