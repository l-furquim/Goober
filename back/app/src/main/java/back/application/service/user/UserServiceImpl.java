package back.application.service.user;

import back.adapter.in.web.controller.user.dto.LoginUserRequestDto;
import back.adapter.in.web.controller.user.dto.RegisterUserRequestDto;
import back.adapter.in.web.controller.user.dto.VerifierCodeRequestDto;
import back.application.service.auth.AuthServiceImpl;
import back.application.service.email.EmailServiceImpl;
import back.domain.enums.AccountStatus;
import back.domain.exception.AuthException;
import back.domain.exception.UserException;
import back.domain.model.user.User;
import back.domain.model.userVerifier.UserVerifier;
import back.domain.port.in.AuthService;
import back.domain.port.in.UserService;
import back.domain.port.in.UserVerifierService;
import back.domain.port.out.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;



    @Autowired
    private EmailServiceImpl emailService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(RegisterUserRequestDto registerUserRequestDto, UserVerifierService userVerifierService) {
        var aUser = userRepository.findUserByEmail(registerUserRequestDto.userEmail());

        if(aUser.isPresent()){
            throw new UserException("Ja existe um usuario com este email !");
        }
        if(registerUserRequestDto.userEmail().isEmpty() || registerUserRequestDto.userPassword().isEmpty()){
            throw new UserException("Voce nao pode criar um usuario com senha ou email vazios !");
        }
        if(
                userVerifierService.getUserVerifierByUserEmail(registerUserRequestDto.userEmail()).isPresent() &&
                        userVerifierService.getUserVerifierByUserEmail(registerUserRequestDto.userEmail()).get().getExpiresAt().compareTo(Instant.now()) >= 0 ){
            throw  new UserException("Seu codigo ainda nao expirou !");
        }


        UserVerifier userVerifierJpaEntity = new UserVerifier(
               UUID.randomUUID(), registerUserRequestDto.userName(),registerUserRequestDto.userEmail(), passwordEncoder.encode(
                registerUserRequestDto.userPassword())
                , Instant.now().plusMillis(6000000), AccountStatus.PENDING, UUID.randomUUID().toString());

        try{
            userVerifierService.save(userVerifierJpaEntity);
            //emailService.sendEmail(
                    //registerUserRequestDto.userEmail(),"Codigo para criação da conta!",
                    //"aqui esta seu codigo: " + userVerifierJpaEntity.getCode());
        }catch (IllegalArgumentException e){
            throw new UserException(e.getMessage());
        }catch(OptimisticLockingFailureException e) {
            throw new UserException((e.getMessage()));
        }


    }

    @Override
    public UserVerifier verifierCode(VerifierCodeRequestDto verifierCodeRequestDto, UserVerifierService userVerifierService) {
        var aUserVerifier = userVerifierService.getUserVerifierByCode(verifierCodeRequestDto.code());

        if (aUserVerifier.isEmpty()) {
            throw new UserException("Codigo invalido ou incorreto !");
        }
        var anUser = userRepository.findUserByEmail(aUserVerifier.get().getUserVerifierEmail());


        ZoneId zoneId = ZoneId.systemDefault();

        Instant instant = Instant.now();

        ZonedDateTime zonedDateTime = instant.atZone(zoneId);


        if (aUserVerifier.get().getExpiresAt().compareTo(Instant.now()) <= 0) {
            userVerifierService.delete(aUserVerifier.get());
            throw new UserException("Seu codigo de validação expirou!");
        }


        var User = new User(
                aUserVerifier.get().getUserVerifierId(), aUserVerifier.get().getUserVerifierName(),
                aUserVerifier.get().getUserVerifierEmail(), aUserVerifier.get().getUserVerifierPassword(),
                "woiew", AccountStatus.ACTIVE);




        try {
            userRepository.save(User);
            return aUserVerifier.get();
        } catch (IllegalArgumentException e) {
            throw new UserException(e.getMessage());
        } catch (OptimisticLockingFailureException e) {
            throw new UserException((e.getMessage()));
        }

    }

    @Override
    public boolean loginCredentialsValidade(User user, String password) {

        return passwordEncoder.matches(password, user.getUserPassword());

    }

    @Override
    public String loginUser(LoginUserRequestDto loginUserRequestDto, UserVerifierService userVerifierService) {

        var authService = new AuthServiceImpl(userRepository);

        if(userVerifierService.getUserVerifierByUserEmail(loginUserRequestDto.email()).isPresent()){
            throw new AuthException("Você ainda não validou sua conta, tente novamente após validar o codigo!");
        }


        var user = userRepository.findUserByEmail(loginUserRequestDto.email());

        if(user.isEmpty()){
            throw new AuthException("Não existe nenhuma conta com estas credenciais");
        }

        if(!loginCredentialsValidade(user.get(), loginUserRequestDto.password())){
            throw new AuthException("Senha incorreta");
        }


        return authService.createToken(user.get());
    }

    @Override
    public User loadUserByUserName(String userName) {
        var user = userRepository.findUserByEmail(userName);

        if(user.isEmpty()){
            throw new UserException("Não foi possivel carregar o usuario pelo seu email");
        }
        return user.get();
    }


}
