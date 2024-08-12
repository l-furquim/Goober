package back.domain.port.in;

import back.adapter.in.web.controller.user.dto.LoginUserRequestDto;
import back.adapter.in.web.controller.user.dto.RegisterUserRequestDto;
import back.adapter.in.web.controller.user.dto.VerifierCodeRequestDto;
import back.domain.model.user.User;
import back.application.service.userVerifier.UserVerifierServiceImpl;


public interface UserService {
    void registerUser(RegisterUserRequestDto registerUserRequestDto, UserVerifierServiceImpl userVerifierService);
    User verifierCode(VerifierCodeRequestDto verifierCodeRequestDto, UserVerifierServiceImpl userVerifierService);
    User validateRegister(String code,String userName, UserVerifierServiceImpl userVerifierService);
    boolean loginCredentialsValidade(LoginUserRequestDto loginUserRequestDto);

}
