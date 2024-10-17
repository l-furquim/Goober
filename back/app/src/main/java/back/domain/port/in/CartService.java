package back.domain.port.in;

import back.adapter.in.web.controller.cart.dto.CreateCartRequestDto;
import back.adapter.in.web.controller.cart.dto.DeleteCartRequestDto;
import back.domain.model.cart.Cart;
import back.domain.model.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartService {

    Cart createCart(CreateCartRequestDto createCartRequestDto, String id);
    void deleteCart(DeleteCartRequestDto deleteCartRequestDto);
    void addProductToCart(String cartId, List<Product> product);
    BigDecimal calculateTotalPrice(List<Product> productList);
    void finishCart(String cartId);
    Optional<List<Cart>> findCartByUserId(UUID userid);
}
