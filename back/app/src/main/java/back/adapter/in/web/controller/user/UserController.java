package back.adapter.in.web.controller.user;

import back.adapter.in.web.controller.user.dto.*;
import back.domain.port.in.UserService;
import back.domain.port.in.UserVerifierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserVerifierService userVerifierService;

    @Autowired
    public UserController(UserService userService, UserVerifierService userVerifierService) {
        this.userService = userService;
        this.userVerifierService = userVerifierService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> registerUser(@RequestBody RegisterUserRequestDto registerUserRequestDto){

        userService.registerUser(registerUserRequestDto, userVerifierService);

        return ResponseEntity.ok().body(new RegisterUserResponseDto("Registro realizado com sucesso"));
    }

    @PostMapping("/verifierCode")
    public ResponseEntity<VerifierCodeResponseDto> verifierCode(@RequestBody VerifierCodeRequestDto codeRequestDto){

        var aUserVerifier = userService.verifierCode(codeRequestDto, userVerifierService);

        log.info(aUserVerifier.getCode() + " " + aUserVerifier.getUserVerifierId());

        userVerifierService.delete(aUserVerifier);

        return ResponseEntity.ok().body(new VerifierCodeResponseDto("Verificação feita com sucesso!"));
    }




    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDto> loginUser(@RequestBody LoginUserRequestDto loginUserRequestDto){
        var token = userService.loginUser(loginUserRequestDto, userVerifierService);





        return ResponseEntity.ok().body(new LoginUserResponseDto(token));
    }


}
