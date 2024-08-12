package back.application.service.user;

import back.adapter.in.web.controller.user.dto.LoginUserRequestDto;
import back.adapter.in.web.controller.user.dto.RegisterUserRequestDto;
import back.adapter.in.web.controller.user.dto.VerifierCodeRequestDto;
import back.application.service.email.EmailServiceImpl;
import back.domain.enums.AccountStatus;
import back.domain.exception.UserException;
import back.domain.model.user.User;
import back.domain.model.userVerifier.UserVerifier;
import back.domain.port.in.UserService;
import back.domain.port.out.UserRepository;
import back.application.service.userVerifier.UserVerifierServiceImpl;
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

    private UserRepository userRepository;

    @Autowired
    private EmailServiceImpl emailService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(RegisterUserRequestDto registerUserRequestDto, UserVerifierServiceImpl userVerifierService) {
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
                 registerUserRequestDto.userEmail(), passwordEncoder.encode(registerUserRequestDto.userPassword()), Instant.now().plusMillis(6000000), AccountStatus.PENDING, UUID.randomUUID().toString());

        try{
            userVerifierService.save(userVerifierJpaEntity);
            emailService.sendEmail(
                    registerUserRequestDto.userEmail(),"Codigo para criação da conta!",
                    "aqui esta seu codigo: " + userVerifierJpaEntity.getCode());
        }catch (IllegalArgumentException e){
            throw new UserException(e.getMessage());
        }catch(OptimisticLockingFailureException e) {
            throw new UserException((e.getMessage()));
        }


    }

    @Override
    public User verifierCode(VerifierCodeRequestDto verifierCodeRequestDto, UserVerifierServiceImpl userVerifierService) {
        var aUserVerifier = userVerifierService.getUserVerifierByCode(verifierCodeRequestDto.code());

        var anUser = userRepository.findUserByEmail(aUserVerifier.get().getUserVerifierEmail());

        if(aUserVerifier.isEmpty()){
            throw new UserException("Codigo invalido ou incorreto !");
        }
        userVerifierService.delete(aUserVerifier.get());

        return anUser.get();

    }

    @Override
    public User validateRegister(String code,String userName, UserVerifierServiceImpl userVerifierService) {
        ZoneId zoneId = ZoneId.systemDefault();

        Instant instant = Instant.now();

        ZonedDateTime zonedDateTime = instant.atZone(zoneId);

        var userVerifier = userVerifierService.getUserVerifierByCode(code);

        if(userVerifier.isEmpty()){
            throw new UserException("Codigo invalido , nao existe nenhum login com este codigo!");
        }
        if(userVerifier.get().getExpiresAt().compareTo(Instant.now()) <=0){
            userVerifierService.delete(userVerifier.get());
            throw new UserException("Seu codigo de validação expirou!");
        }

        userVerifier.get().setStatus(AccountStatus.ACTIVE);

        var anUser = new User(
                UUID.randomUUID(),userName,userVerifier.get().getUserVerifierEmail(),userVerifier.get().getUserVerifierPassword(),
               "woiew",AccountStatus.ACTIVE);


        try{
            userRepository.save(anUser);
            userVerifierService.delete(userVerifier.get());
        }catch (IllegalArgumentException e){
            throw new UserException(e.getMessage());
        }catch(OptimisticLockingFailureException e) {
            throw new UserException((e.getMessage()));
        }

        return anUser;
    }

    @Override
    public boolean loginCredentialsValidade(LoginUserRequestDto loginUserRequestDto) {
        var aUser = this.userRepository.findUserByEmail(loginUserRequestDto.email());

        if(aUser.isPresent()) {

            return passwordEncoder.matches(loginUserRequestDto.password(), aUser.get().getUserPassword());
        }

        return false;
    }
}
