package back.adapter.out.persistence.mapper.userverifier;

import back.adapter.out.persistence.entity.userverifier.UserVerifierEntity;
import back.domain.model.userVerifier.UserVerifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserVerifierMapper {

    public Optional<UserVerifierEntity> toEntity(UserVerifier userVerifier){
        return Optional.of(new UserVerifierEntity(
                userVerifier.getUserVerifierId(),
                userVerifier.getUserVerifierName(),
                userVerifier.getUserVerifierEmail(),
                userVerifier.getUserVerifierPassword(),
                userVerifier.getExpiresAt(),
                userVerifier.getStatus(),
                userVerifier.getCode(),
                userVerifier.getUserImagePath()
        ));
    }

    public Optional<UserVerifier> toDomain(UserVerifierEntity userVerifier){
        return Optional.of(new UserVerifier(
                userVerifier.getUserVerifierId(),
                userVerifier.getUserVerifierName(),
                userVerifier.getUserVerifierEntityEmail(),
                userVerifier.getUserVerifierEntityPassword(),
                userVerifier.getExpiresAt(),
                userVerifier.getStatus(),
                userVerifier.getCode(),
                userVerifier.getUserImagePath()
        ));
    }

}
