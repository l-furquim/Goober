package back.adapter.in.web.controller.cart.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record FindCartByUserIdResponseDto(
        UUID cartId, Integer itemsQuantity, BigDecimal totalPrice
) {
}
