package back.adapter.out.persistence.repository.user;

import back.adapter.out.persistence.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {


    @Query("SELECT u FROM UserEntity u WHERE u.userPassword = :password")
    Optional<UserEntity> findUserByPassword(@Param("password")String password);

    Optional<UserEntity> findUserByUserEmail(String email);
}
