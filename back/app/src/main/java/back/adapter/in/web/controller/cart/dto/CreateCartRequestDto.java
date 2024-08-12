package back.adapter.in.web.controller.cart.dto;

import back.domain.model.product.Product;

import java.math.BigDecimal;
import java.util.List;

public record CreateCartRequestDto(String id, List<Product> productList) {
}
