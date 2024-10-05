package back.adapter.in.web.controller.user;

import back.adapter.in.web.controller.user.dto.*;
import back.domain.port.in.AuthService;
import back.domain.port.in.ImageService;
import back.domain.port.in.UserService;
import back.domain.port.in.UserVerifierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("user")
public class UserController {


    private final AuthService authService;
    private final UserService userService;
    private final UserVerifierService userVerifierService;
    private final ImageService imageService;

    @Autowired
    public UserController(UserService userService, UserVerifierService userVerifierService,
                          AuthService authService, ImageService imageService) {
        this.userService = userService;
        this.userVerifierService = userVerifierService;
        this.authService = authService;
        this.imageService = imageService;
    }

    @PostMapping(value="/register" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RegisterUserResponseDto> registerUser(@RequestPart("registerData") RegisterUserRequestDto registerData,
                                                                @RequestPart("userImage") MultipartFile userImage){
        
        var path = imageService.saveImage(userImage, "user/" + registerData.userName() + "/");

        userService.registerUser(registerData, userVerifierService, path);

        return ResponseEntity.ok().body(new RegisterUserResponseDto("Registro realizado com sucesso"));
    }

    @PostMapping("/verifierCode")
    public ResponseEntity<VerifierCodeResponseDto> verifierCode(@RequestBody VerifierCodeRequestDto codeRequestDto){

        var aUserVerifier = userService.verifierCode(codeRequestDto, userVerifierService);

        userVerifierService.delete(aUserVerifier);

        return ResponseEntity.ok().body(new VerifierCodeResponseDto("Verificação feita com sucesso!"));
    }




    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDto> loginUser(@RequestBody LoginUserRequestDto loginUserRequestDto){
        var token = userService.loginUser(loginUserRequestDto, userVerifierService);





        return ResponseEntity.ok().body(new LoginUserResponseDto(token));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<ValidateTokenResponseDto> validateToken(@RequestBody ValidateTokenRequestDto validateTokenRequestDto){

        var isValid = authService.validateTokenNoString(validateTokenRequestDto.token());


        return ResponseEntity.ok().body(new ValidateTokenResponseDto(isValid));
    }

    @GetMapping("/get")
    public ResponseEntity<ShowUserPropsResponseDto> showUserProps(@RequestHeader("Authorization") String token){

        var user = authService.getUserByToken(token.substring(7));

        return ResponseEntity.ok().body(new ShowUserPropsResponseDto(user.getUserEmail(), user.getUserName(), user.getUserImage()));
    }
    @GetMapping("/get/image/src/main/resources/static/images/user/{userName}")
    public ResponseEntity<byte[]> getUserImage(@PathVariable("userName") String userName){
        var images = imageService.findImageByDirName("user/" + userName);

        return ResponseEntity.ok().body(images);
    }


}
