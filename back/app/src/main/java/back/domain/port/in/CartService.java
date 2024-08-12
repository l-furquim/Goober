package back.domain.port.in;

import back.adapter.in.web.controller.cart.dto.CreateCartRequestDto;
import back.adapter.in.web.controller.cart.dto.DeleteCartRequestDto;
import back.domain.model.cart.Cart;
import back.domain.model.product.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    Cart createCart(CreateCartRequestDto createCartRequestDto);
    void deleteCart(DeleteCartRequestDto deleteCartRequestDto);
    void addProductToCart(Cart cart,List<Product> product);
    BigDecimal calculateTotalPrice(List<Product> productList);

}
