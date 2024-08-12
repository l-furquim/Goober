package back.adapter.out.persistence.mapper.userverifier;

import back.adapter.out.persistence.entity.userverifier.UserVerifierEntity;
import back.domain.model.userVerifier.UserVerifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserVerifierMapper {

    public Optional<UserVerifierEntity> toEntity(UserVerifier userVerifier){
        return Optional.of(new UserVerifierEntity(
            userVerifier.getUserVerifierEmail(),
                userVerifier.getUserVerifierPassword(),
                userVerifier.getExpiresAt(),
                userVerifier.getStatus(),
                userVerifier.getCode()
        ));
    }

    public Optional<UserVerifier> toDomain(UserVerifierEntity userVerifier){
        return Optional.of(new UserVerifier(
                userVerifier.getUserVerifierEntityEmail(),
                userVerifier.getUserVerifierEntityPassword(),
                userVerifier.getExpiresAt(),
                userVerifier.getStatus(),
                userVerifier.getCode()
        ));
    }

}
