package back.adapter.in.web.controller.cart;

import back.adapter.in.web.controller.cart.dto.*;
import back.domain.port.in.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {


    private final CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<CreateCartResponseDto> createCart(@PathVariable("userId")String userId, @RequestBody CreateCartRequestDto createCartRequestDto){

        cartService.createCart(createCartRequestDto, userId);

        return ResponseEntity.ok().body(new CreateCartResponseDto("carrinho criado com sucesso"));
    }

    @DeleteMapping("/finish/{cartId}")
    public ResponseEntity<FinishCartResponseDto> finishCart(@PathVariable("cartId")String cartId){

        cartService.finishCart(cartId);

        return ResponseEntity.ok().body(new FinishCartResponseDto("Carrinho pagado com sucesso"));
    }

    @PutMapping("/add/cart={cartId}")
    public ResponseEntity<AddProductToCartResponseDto> addProductToCart(@PathVariable("cartId")String cartId,
                                                                        @RequestBody AddProductToCartRequestDto product){

        cartService.addProductToCart(cartId, product.products());


        return ResponseEntity.ok().body(new AddProductToCartResponseDto("produto adicionado com sucesso"));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<FindCartByUserIdResponseDto>> findCartByUserId(@PathVariable("userId") String userId){

        final var cart = cartService.findCartByUserId(UUID.fromString(userId));

        if(cart.isEmpty()) {
            return ResponseEntity.ok().body(Collections.emptyList());
        }

        return ResponseEntity.ok().body(
                cart.get().stream().map(ct -> {
                    return new FindCartByUserIdResponseDto(ct.getCartId(), ct.getItemsQuantity(), ct.getTotalPrice());
                }).toList()
        );
    }








}
