package back.adapter.in.web.controller.product;

import back.adapter.in.web.controller.product.dto.*;
import back.domain.port.in.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateProductResponseDto> createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {

        log.info(createProductRequestDto.name());

        var product = productService.createProduct(createProductRequestDto);

        return ResponseEntity.ok().body(new CreateProductResponseDto(product));
    }

    @GetMapping("/find/categories/{categorie}")
    public ResponseEntity<FindProductByCategorieResponseDto> findProductsByCategorie(@PathVariable("categorie") String categories) {

        var products = productService.findProductsByCategories(categories.toUpperCase());

        return ResponseEntity.ok().body(new FindProductByCategorieResponseDto(products.get()));
    }


    @PostMapping("/change/price")
    public ResponseEntity<ChangeProductPriceResponseDto> changeProductPrice(@RequestBody ChangeProductPriceRequestDto
                                                                                    changeProductPriceRequestDto) {

        productService.changeProductPrice(changeProductPriceRequestDto);

        return ResponseEntity.ok().body(new ChangeProductPriceResponseDto("Preço alterado com sucesso"));
    }

    @PostMapping("/change/name")
    public ResponseEntity<ChangeProductNameResponseDto> changeProductName(@RequestBody ChangeProductNameRequestDto changeProductNameRequestDto) {

        productService.changeProductName(changeProductNameRequestDto);

        return ResponseEntity.ok().body(new ChangeProductNameResponseDto("Nome mudado com sucesso"));
    }

    @GetMapping("/find/name={name}/price={price}")
    public ResponseEntity<FindProductsByNameAndPriceFilterResponseDto> findProductsByNameAndPrice(@PathVariable("name") String name,
                                                                                                 @PathVariable("price") Double price ){

        var products = productService.findProductsByNameAndPriceFilter(new FindProductsByNameAndPriceFilterRequesDto(name,
                price));

        return ResponseEntity.ok().body(new FindProductsByNameAndPriceFilterResponseDto(products.get()));
    }




}
