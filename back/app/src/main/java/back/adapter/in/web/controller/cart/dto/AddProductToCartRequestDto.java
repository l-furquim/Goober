package back.adapter.in.web.controller.cart.dto;

import back.domain.model.product.Product;

import java.util.List;

public record AddProductToCartRequestDto(List<Product> products) {
}
