package back.adapter.in.web.controller.product.dto;

import back.domain.model.product.Product;

import java.util.List;

public record FindProductsByNameAndPriceFilterResponseDto(List<Product> products) {
}
