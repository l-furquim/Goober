package back.adapter.out.persistence.mapper.product;

import back.adapter.out.persistence.entity.product.ProductEntity;
import back.domain.model.product.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductMapper {

    public static Optional<ProductEntity> toEntity(Product product){
        return Optional.of(new ProductEntity(
                product.getProductId(),
                product.getProductName(),
                product.getProductPrice(),
                product.getProductCategorie(),
                product.getProductDescription(),
                product.getProductImages()
        ));
    }
    public static  Optional<Product> toDomain(ProductEntity product){
        return Optional.of(new Product(
                product.getProductEntityId(),
                product.getProductEntityName(),
                product.getProductEntityPrice(),
                product.getProductCategories(),
                product.getProductEntityDescription(),
                product.getProductEntityImages()
        ));
    }
    public static List<ProductEntity> productToEntityList(List<Product> list){
        return list.stream().map(prod -> toEntity(prod).get()).toList();
    }

    public static List<Product> productEntityToDomainList(List<ProductEntity> list){
        return list.stream().map(prod -> toDomain(prod).get()).toList();
    }
}
