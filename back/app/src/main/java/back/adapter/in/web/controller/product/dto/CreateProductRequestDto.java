package back.adapter.in.web.controller.product.dto;

import back.domain.enums.ProductCategories;

import java.math.BigDecimal;

public record CreateProductRequestDto(String name,
                                      BigDecimal price, ProductCategories productCategories, String description ,
                                      String productImagePath) {
}
