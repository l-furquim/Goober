package back.adapter.out.persistence.mapper.user;

import back.adapter.out.persistence.entity.user.UserEntity;
import back.domain.model.user.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public Optional<UserEntity> toEntity(User user){
        return Optional.of(new UserEntity(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPassword(),
                user.getUserImage(),
                user.getStatus()
        ));
    }
    public Optional<User> toDomain(UserEntity user){
        return Optional.of(new User(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPassword(),
                user.getUserImage(),
                user.getStatus()
        ));
    }

}
