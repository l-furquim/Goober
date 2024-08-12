package back.adapter.out.persistence.repository.product;

import back.adapter.out.persistence.entity.product.ProductEntity;
import back.domain.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    Optional<List<ProductEntity>> findProductsByProductName(String name);

    @Query("SELECT u FROM ProductEntity u WHERE u.productName =:name AND u.productPrice = :price")
    Optional<List<ProductEntity>> findProductByNameAndPriceFilter(@Param("name") String name, @Param("price")BigDecimal price);
}
