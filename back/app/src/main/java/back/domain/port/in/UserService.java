package back.domain.port.in;

import back.adapter.in.web.controller.user.dto.LoginUserRequestDto;
import back.adapter.in.web.controller.user.dto.RegisterUserRequestDto;
import back.adapter.in.web.controller.user.dto.VerifierCodeRequestDto;
import back.domain.model.user.User;
import back.application.service.userVerifier.UserVerifierServiceImpl;
import back.domain.model.userVerifier.UserVerifier;


public interface UserService {
    void registerUser(RegisterUserRequestDto registerUserRequestDto, UserVerifierService userVerifierService);
    UserVerifier verifierCode(VerifierCodeRequestDto verifierCodeRequestDto, UserVerifierService userVerifierService);
    boolean loginCredentialsValidade(User user, String password);
    String loginUser(LoginUserRequestDto loginUserRequestDto, UserVerifierService userVerifierService);
    User loadUserByUserName(String userName);
}
