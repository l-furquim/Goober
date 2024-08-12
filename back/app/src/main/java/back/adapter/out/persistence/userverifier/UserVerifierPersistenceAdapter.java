package back.adapter.out.persistence.userverifier;

import back.adapter.out.persistence.mapper.userverifier.UserVerifierMapper;
import back.adapter.out.persistence.repository.userverifier.UserVerifierJpaRepository;
import back.domain.model.userVerifier.UserVerifier;
import back.domain.port.out.UserVerifierRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserVerifierPersistenceAdapter implements UserVerifierRepository {

   private final UserVerifierJpaRepository userVerifierJpaRepository;
   private final UserVerifierMapper userVerifierMapper;

    public UserVerifierPersistenceAdapter(UserVerifierJpaRepository userVerifierJpaRepository, UserVerifierMapper userVerifierMapper) {
        this.userVerifierJpaRepository = userVerifierJpaRepository;
        this.userVerifierMapper = userVerifierMapper;
    }

    @Override
    public Optional<UserVerifier> getUserVerifierByUserEmail(String email) {
        var uEntity = userVerifierJpaRepository.findUserVerifierByEmail(email);

        if(uEntity.isEmpty()){
            return Optional.empty();
        }
        return userVerifierMapper.toDomain(uEntity.get());
    }

    @Override
    public void save(UserVerifier userVerifier) {
        var entity = userVerifierMapper.toEntity(userVerifier);

        userVerifierJpaRepository.save(entity.get());
    }

    @Override
    public Optional<UserVerifier> getUserVerifierByCode(String code) {
        var uEntity = userVerifierJpaRepository.findUserVerifierByCode(code);

        if(uEntity.isEmpty()){
            return Optional.empty();
        }
        return userVerifierMapper.toDomain(uEntity.get());
    }

    @Override
    public void delete(UserVerifier userVerifier) {
        var entity = userVerifierMapper.toEntity(userVerifier);

        userVerifierJpaRepository.delete(entity.get());
    }
}
