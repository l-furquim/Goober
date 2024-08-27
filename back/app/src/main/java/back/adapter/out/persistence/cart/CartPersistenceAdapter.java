package back.adapter.out.persistence.cart;

import back.adapter.out.persistence.mapper.cart.CartMapper;
import back.adapter.out.persistence.repository.cart.CartJpaRepository;
import back.adapter.out.persistence.repository.product.ProductJpaRepository;
import back.domain.model.cart.Cart;
import back.domain.model.product.Product;
import back.domain.port.out.CartRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CartPersistenceAdapter implements CartRepository {

    private final CartMapper cartMapper;
    private final CartJpaRepository cartJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public CartPersistenceAdapter(CartMapper cartMapper, CartJpaRepository cartJpaRepository, ProductJpaRepository productJpaRepository) {
        this.cartMapper = cartMapper;
        this.cartJpaRepository = cartJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public void save(Cart cart) {
        var prodListEntity = cart.getCartProducts().stream().map(
                prod -> productJpaRepository.findById(prod.getProductId()).get()).toList();

        var cartEntity = cartMapper.toEntity(cart, prodListEntity);

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

    @Transactional
    @Override
    public void updateCartTotalPrice(BigDecimal totalPrice, String cartId, List<Product> product) {

        var cartEntity = cartJpaRepository.findById(UUID.fromString(cartId));
        var productsEntity = product.stream().map(prod -> productJpaRepository
                .findById(prod.getProductId()).get()).toList();


        if(cartEntity.isPresent()){

            for(int i=0; i< product.size(); i++){
                cartEntity.get().addProductToCart(productsEntity.get(i));

                cartEntity.get().setTotalPrice(cartEntity.get().getTotalPrice().add(totalPrice));
            }

        }

    }
}
