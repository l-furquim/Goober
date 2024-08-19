package back.adapter.in.web.controller.exception;

import back.adapter.in.web.controller.exception.body.GlobalExceptionHandler;
import back.domain.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("back.adapter.in.web.controller.user")
public class UserExceptionHandler {

    @ExceptionHandler(value = {UserException.class})
    protected ResponseEntity<GlobalExceptionHandler> handleUserException(
            final UserException userException,
            final HttpServletRequest aRequest
    ){
        final var aBody = new GlobalExceptionHandler(
                userException.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                aRequest.getRequestURI()
        );
        return ResponseEntity.badRequest().body(aBody);
    }
}
