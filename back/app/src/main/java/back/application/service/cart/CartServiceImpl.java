package back.application.service.cart;

import back.adapter.in.web.controller.cart.dto.CreateCartRequestDto;
import back.adapter.in.web.controller.cart.dto.DeleteCartRequestDto;
import back.domain.exception.AnnouncementException;
import back.domain.exception.CartException;
import back.domain.exception.UserException;
import back.domain.model.cart.Cart;
import back.domain.model.product.Product;
import back.domain.port.in.CartService;
import back.domain.port.out.CartRepository;
import back.domain.port.out.ProductRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;


    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart(CreateCartRequestDto createCartRequestDto, String id) {
        if(createCartRequestDto.productList().isEmpty()){
            throw new CartException("Voce nao pode criar um carrinho sem possuir produtos.");
        }

        var totalprice = calculateTotalPrice(createCartRequestDto.productList());

        var cart = new Cart(
                UUID.randomUUID(),
                createCartRequestDto.productList().size(),
                totalprice,
                UUID.fromString(id),
                createCartRequestDto.productList()
        );

        try{
            cartRepository.save(cart);
        }catch (IllegalArgumentException e){
            throw new CartException(e.getMessage());
        }catch(OptimisticLockingFailureException e) {
            throw new UserException((e.getMessage()));
        }
        return cart;
    }

    @Override
    public void deleteCart(DeleteCartRequestDto deleteCartRequestDto) {
        var cart = cartRepository.findCartById(UUID.fromString(deleteCartRequestDto.id()));

        if(cart.isEmpty()){
            throw new CartException("Nao foi possivel deletar o carrinho, ele nao existe.");
        }

        try{
            cartRepository.delete(cart.get());
        }catch (IllegalArgumentException e){
            throw new CartException(e.getMessage());
        }catch(OptimisticLockingFailureException e) {
            throw new UserException((e.getMessage()));
        }
    }

    @Override
    public void addProductToCart(String cartId, List<Product> product) {

            var newPrice = calculateTotalPrice(product);

            cartRepository.updateCartTotalPrice(newPrice, cartId,product);
    }

    @Override
    public BigDecimal calculateTotalPrice(List<Product> productList) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for(int i=0; i < productList.size(); i++){
            totalPrice= totalPrice.add(productList.get(i).getProductPrice());
        }

        return totalPrice;
    }

    @Override
    public void finishCart(String cartId) {

        var cart = cartRepository.findCartById(UUID.fromString(cartId));

        if(cart.isPresent()) {

            cartRepository.delete(cart.get());
        }
    }
}
