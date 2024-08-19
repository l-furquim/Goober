package back.adapter.in.web.controller.exception.body;

public record GlobalExceptionHandler (
        String errorMessage,
        Integer status,
        String url
){
}
