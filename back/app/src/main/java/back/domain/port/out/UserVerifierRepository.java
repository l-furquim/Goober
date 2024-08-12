package back.domain.port.out;

import back.domain.model.userVerifier.UserVerifier;

import java.util.Optional;

public interface UserVerifierRepository {
    Optional<UserVerifier> getUserVerifierByUserEmail(String email);
    void save (UserVerifier userVerifier);
    Optional<UserVerifier> getUserVerifierByCode(String code);
    void delete(UserVerifier userVerifier);
}
