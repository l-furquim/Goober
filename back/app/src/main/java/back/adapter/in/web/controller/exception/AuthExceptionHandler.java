package back.adapter.in.web.controller.exception;

import back.adapter.in.web.controller.exception.body.GlobalExceptionHandler;
import back.domain.exception.AuthException;
import back.domain.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice("back.adapter.in.web.controller.user")
public class AuthExceptionHandler {


    @ExceptionHandler(value = {AuthException.class})
    protected ResponseEntity<GlobalExceptionHandler> handleAuthException(
            final AuthException authException,
            final HttpServletRequest aRequest
    ){
        final var aBody = new GlobalExceptionHandler(
                authException.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                aRequest.getRequestURI()
        );
        return ResponseEntity.badRequest().body(aBody);
    }
}
