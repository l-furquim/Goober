package back.domain.port.out;

import back.adapter.in.web.controller.product.dto.ChangeProductNameRequestDto;
import back.adapter.in.web.controller.product.dto.ChangeProductPriceRequestDto;
import back.domain.model.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(Product product);
    void delete(Product product);
    Optional<Product> findProductById(String id);
    Optional<List<Product>> findProductsByName(String name);
    Optional<List<Product>> findProductsByNameAndPriceFilter(String name, BigDecimal price);
    Optional<List<Product>> findProductsByCategories(String categories);
    void changeProductPrice(ChangeProductPriceRequestDto productPriceRequestDto);
    void changeProductName(ChangeProductNameRequestDto productNameRequestDto);

}
