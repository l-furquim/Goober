package back.application.service.userVerifier;

import back.domain.model.userVerifier.UserVerifier;
import back.domain.port.in.UserVerifierService;
import back.domain.port.out.UserVerifierRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserVerifierServiceImpl implements UserVerifierService {

    private final UserVerifierRepository userVerifierRepository;

    public UserVerifierServiceImpl(UserVerifierRepository userVerifierRepository) {
        this.userVerifierRepository = userVerifierRepository;
    }

    @Override
    public Optional<UserVerifier> getUserVerifierByUserEmail(String email) {
        return userVerifierRepository.getUserVerifierByUserEmail(email);
    }

    @Override
    public void save(UserVerifier userVerifier) {
        userVerifierRepository.save(userVerifier);
    }

    @Override
    public Optional<UserVerifier> getUserVerifierByCode(String code) {
        return userVerifierRepository.getUserVerifierByCode(code);
    }

    @Override
    public void delete(UserVerifier userVerifier) {
        userVerifierRepository.delete(userVerifier);
    }
}
