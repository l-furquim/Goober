package back.adapter.out.persistence.product;

import back.adapter.out.persistence.mapper.product.ProductMapper;
import back.adapter.out.persistence.repository.product.ProductJpaRepository;
import back.domain.model.product.Product;
import back.domain.port.out.ProductRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductPersistenceAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    public ProductPersistenceAdapter(ProductJpaRepository productJpaRepository, ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper = productMapper;
    }


    @Override
    public void save(Product product) {
        var productEntity = productMapper.toEntity(product);

        productJpaRepository.save(productEntity.get());
    }

    @Override
    public void delete(Product product) {
        var productEntity = productMapper.toEntity(product);

        productJpaRepository.delete(productEntity.get());
    }

    @Override
    public Optional<Product> findProductById(String id) {
        var prod = productJpaRepository.findById(UUID.fromString(id));

        if(prod.isEmpty()){
            return Optional.empty();
        }

        return productMapper.toDomain(prod.get());
    }

    @Override
    public Optional<List<Product>> findProductsByName(String name) {
        var prodl = productJpaRepository.findProductsByProductName(name).get();

        if(prodl.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(prodl.stream().map(prod -> productMapper.toDomain(prod).get()).toList());
    }

    @Override
    public Optional<List<Product>> findProductsByNameAndPriceFilter(String name, BigDecimal price) {
        var prod = productJpaRepository.findProductByNameAndPriceFilter(name,price);

       if(prod.isEmpty()){
           return Optional.empty();
       }
        return Optional.of(prod.get().stream().map(prods -> productMapper.toDomain(prods).get()).toList());
    }
}
