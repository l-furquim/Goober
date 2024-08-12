package back.adapter.in.web.controller.product.dto;

import java.math.BigDecimal;

public record CreateProductRequestDto(String name, BigDecimal price,String description ,String productImagePath) {
}
