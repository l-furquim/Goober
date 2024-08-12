package back.domain.port.out;

import back.domain.model.user.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    void delete(User user);
    Optional<User> findById(String userId);
    Optional<User> findUserByPassword(String password);
    Optional<User> findUserByEmail(String email);
    void updateUserEmailById(String id, String email);
}
