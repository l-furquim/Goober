package back.application.service.auth;


import back.adapter.out.persistence.entity.user.UserEntity;
import back.adapter.out.persistence.mapper.user.UserMapper;
import back.domain.exception.AuthException;
import back.domain.model.user.User;
import back.domain.port.in.AuthService;
import back.domain.port.in.UserService;
import back.domain.port.out.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthServiceImpl implements UserDetailsService , AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private String TOKEN_SECRET = "alfabeto";

    private String TOKEN_ISSUER = "goober";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserMapper.toEntity(userRepository.findUserByEmail(username).get()).get();
    }

    @Override
    public Boolean validateTokenNoString(String token) {
        var isValid = false;

        try{
            final var anAlgorithm = Algorithm.HMAC256("alfabeto");

            final var aVerifier = JWT.require(anAlgorithm)
                    .withIssuer(TOKEN_ISSUER).build();

            final var decodedToken = aVerifier.verify(token);

           isValid = true;

            return isValid;
        }catch (Exception e){
            throw new AuthException("Token invalido "+ token + e.getMessage());
        }
    }

    @Override
    public User getUserByToken(String token) {
        var subject = this.validateToken(token);

        return userRepository.findUserByEmail(subject).get();
    }


    @Override
    public String createToken(final User userDomain) {
        try {
            var user = UserMapper.toEntity(userDomain).get();

            final var anAlgorithm = Algorithm.HMAC256("alfabeto");

            final var aToken = JWT.create().withIssuer(TOKEN_ISSUER)
                    .withSubject(user.getUserEmail())
                    .withExpiresAt(Instant.now().plusSeconds(60 * 60 * 4))
                    .sign(anAlgorithm);

            return aToken;
        } catch (IllegalArgumentException e) {
            throw new AuthException(e.getMessage());
        } catch (JWTCreationException e) {
            throw new AuthException(e.getMessage());
        }
    }

    @Override
    public String validateToken(String token){
        try{
            final var anAlgorithm = Algorithm.HMAC256("alfabeto");

            final var aVerifier = JWT.require(anAlgorithm)
                    .withIssuer(TOKEN_ISSUER).build();

            final var decodedToken = aVerifier.verify(token);

            final var subject = decodedToken.getSubject();

            return subject;
        }catch (Exception e){
            throw new AuthException("Token invalido "+ token + e.getMessage());
        }
    }




}
