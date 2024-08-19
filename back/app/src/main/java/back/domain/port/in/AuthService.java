package back.domain.port.in;

import back.adapter.out.persistence.entity.user.UserEntity;
import back.domain.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    String validateToken(String token);
    String createToken(final User user);
}
