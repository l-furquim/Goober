package back.adapter.out.persistence.mapper.product;

import back.adapter.out.persistence.entity.product.ProductEntity;
import back.domain.model.product.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductMapper {

    public Optional<ProductEntity> toEntity(Product product){
        return Optional.of(new ProductEntity(
                product.getProductId(),
                product.getProductName(),
                product.getProductPrice(),
                product.getProductDescription(),
                product.getProductImages()
        ));
    }
    public Optional<Product> toDomain(ProductEntity product){
        return Optional.of(new Product(
                product.getProductEntityId(),
                product.getProductEntityName(),
                product.getProductEntityPrice(),
                product.getProductEntityDescription(),
                product.getProductEntityImages()
        ));
    }
}
