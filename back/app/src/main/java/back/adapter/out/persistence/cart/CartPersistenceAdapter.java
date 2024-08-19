package back.adapter.out.persistence.cart;

import back.adapter.out.persistence.mapper.cart.CartMapper;
import back.adapter.out.persistence.repository.cart.CartJpaRepository;
import back.domain.model.cart.Cart;
import back.domain.port.out.CartRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CartPersistenceAdapter implements CartRepository {

    private final CartMapper cartMapper;
    private final CartJpaRepository cartJpaRepository;

    public CartPersistenceAdapter(CartMapper cartMapper, CartJpaRepository cartJpaRepository) {
        this.cartMapper = cartMapper;
        this.cartJpaRepository = cartJpaRepository;
    }


    @Override
    public void save(Cart cart) {
        var cartEntity = cartMapper.toEntity(cart);

        cartJpaRepository.save(cartEntity.get());
    }

    @Override
    public void delete(Cart cart) {
        var cartEntity = cartJpaRepository.findById(cart.getCartId());

        cartJpaRepository.delete(cartEntity.get());
    }

    @Override
    public Optional<Cart> findCartById(UUID id) {
        var cart = cartJpaRepository.findById(id);

        if(cart.isEmpty()){
            return Optional.empty();
        }
       return cartMapper.toDomain(cart.get());
    }

    @Override
    public Optional<List<Cart>> findCartByUserId(UUID id) {
        var cartEntity = cartJpaRepository.findCartsByUserId(id);

        if(cartEntity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(cartEntity.get().stream().map(cart -> cartMapper.toDomain(cart).get()).toList());
    }
}
