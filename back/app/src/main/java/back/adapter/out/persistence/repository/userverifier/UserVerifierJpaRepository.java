package back.adapter.out.persistence.repository.userverifier;

import back.adapter.out.persistence.entity.userverifier.UserVerifierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserVerifierJpaRepository extends JpaRepository<UserVerifierEntity, Long> {

    @Query("SELECT u FROM UserVerifierEntity u WHERE u.userVerifierEmail = :email")
    Optional<UserVerifierEntity> findUserVerifierByEmail(@Param("email") String email);

    @Query("SELECT u FROM UserVerifierEntity u WHERE u.code = :code")
    Optional<UserVerifierEntity> findUserVerifierByCode(@Param("code") String code);
}
