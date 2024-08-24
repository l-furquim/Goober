package back.adapter.out.persistence.mapper.cart;

import back.adapter.out.persistence.entity.cart.CartEntity;
import back.adapter.out.persistence.entity.product.ProductEntity;
import back.adapter.out.persistence.entity.user.UserEntity;
import back.adapter.out.persistence.mapper.product.ProductMapper;
import back.domain.model.cart.Cart;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CartMapper {

    public Optional<CartEntity> toEntity(Cart cart, List<ProductEntity> prod){
        return Optional.of(new CartEntity(
                cart.getCartId(),
                cart.getItemsQuantity(),
                cart.getTotalPrice(),
                cart.getUserId(),
                prod
        ));
    }

    public Optional<Cart> toDomain(CartEntity cart){
        return Optional.of(new Cart(
                cart.getCartId(),
                cart.getItemsQuantity(),
                cart.getTotalPrice(),
                cart.getUserId(),
                ProductMapper.productEntityToDomainList(cart.getCartEntityProducts())
        ));
    }

}
