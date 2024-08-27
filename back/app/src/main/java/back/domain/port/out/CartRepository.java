package back.domain.port.out;

import back.domain.model.cart.Cart;
import back.domain.model.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository {
    void save(Cart cart);
    void delete(Cart cart);
    Optional<Cart> findCartById(UUID id);
    Optional<List<Cart>> findCartByUserId(UUID id);
    void updateCartTotalPrice(BigDecimal totalPrice, String cartId, List<Product> productList);

}
