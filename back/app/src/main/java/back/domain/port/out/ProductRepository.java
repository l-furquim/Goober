package back.domain.port.out;

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

}
