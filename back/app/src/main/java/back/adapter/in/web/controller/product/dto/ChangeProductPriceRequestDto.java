package back.adapter.in.web.controller.product.dto;

import java.math.BigDecimal;

public record ChangeProductPriceRequestDto(String id, Float newPrice) {
}
