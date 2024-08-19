package back.domain.port.in;

import back.adapter.in.web.controller.product.dto.*;
import back.domain.model.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product createProduct(CreateProductRequestDto product);
    void deleteProduct(DeleteProductRequestDto product);
    void changeProductPrice(ChangeProductPriceRequestDto product);
    void changeProductName(ChangeProductNameRequestDto product);
    Optional<List<Product>> findProductsByCategories(String categories);
    Optional<List<Product>> findProductsByNameAndPriceFilter(FindProductsByNameAndPriceFilterRequesDto findProductsByNameAndPriceFilterRequesDto);
}
