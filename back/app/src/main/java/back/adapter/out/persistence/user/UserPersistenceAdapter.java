package back.adapter.out.persistence.user;

import back.adapter.out.persistence.mapper.user.UserMapper;
import back.adapter.out.persistence.repository.user.UserJpaRepository;
import back.domain.exception.UserException;
import back.domain.model.user.User;
import back.domain.port.out.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserPersistenceAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    private final UserMapper userMapper;

    public UserPersistenceAdapter(UserJpaRepository userJpaRepository, UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        var auser = userMapper.toEntity(user);

        userJpaRepository.save(auser.get());
    }

    @Override
    public void delete(User user) {
        var auser = userJpaRepository.findById(user.getUserId());

        userJpaRepository.delete(auser.get());
    }

    @Override
    public Optional<User> findById(String userId) {
        var aUser = userJpaRepository.findById(UUID.fromString(userId));

        if(aUser.isEmpty()){
            return Optional.empty();
        }

        var userD = userMapper.toDomain(aUser.get());

        return userD;
    }

    @Override
    public Optional<User> findUserByPassword(String password) {
        var auser = userJpaRepository.findUserByPassword(password);

        if(auser.isEmpty()){
            return Optional.empty();
        }

        return userMapper.toDomain(auser.get());
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        var user = userJpaRepository.findUserByUserEmail(email);
        if(user.isEmpty()){
            return Optional.empty();
        }
        return userMapper.toDomain(user.get());
    }

    @Transactional
    @Override
    public void updateUserEmailById(String id, String email) {
        var user = userJpaRepository.findById(UUID.fromString(id));

        if(user.isPresent()){
            user.get().setUserEmail(email);
        }
    }
}
