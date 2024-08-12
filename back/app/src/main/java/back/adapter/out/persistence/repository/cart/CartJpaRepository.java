package back.adapter.out.persistence.repository.cart;

import back.adapter.out.persistence.entity.cart.CartEntity;
import back.domain.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, UUID> {

    @Query("SELECT u FROM CartEntity u WHERE u.userId = :id")
    Optional<List<CartEntity>> findCartsByUserId(@Param("id") UUID id);
}
