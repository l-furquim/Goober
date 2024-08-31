package back.adapter.in.web.controller.cart;

import back.adapter.in.web.controller.cart.dto.*;
import back.domain.port.in.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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








}
