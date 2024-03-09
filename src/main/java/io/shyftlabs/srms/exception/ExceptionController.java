package io.shyftlabs.srms.exception;

import io.shyftlabs.srms.shared.Messages;
import io.shyftlabs.srms.shared.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * ExceptionController class to handle the exceptions
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * Handle the MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<?>> handle(MethodArgumentNotValidException exception) {
        HashMap<String, String> params = new HashMap<>();
        for (ObjectError error : exception.getAllErrors()) {
            if (error instanceof FieldError) {
                String field = ((FieldError) error).getField();
                String message = error.getDefaultMessage();
                params.put(field, message);
            }
        }
        ApiException exp = new ApiException(Messages.VALIDATION, params);
        return handle(exp);
    }

    /**
     * Handle the ApiException
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Response<?>> handle(ApiException exception) {
        Response.Message message = Response.Message.builder().key(exception.getKey()).params(exception.getParams()).build();
        Response<?> response = Response.builder().type(Response.Type.FAILURE).message(message).data(null).build();
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Handle the Exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<?>> handle(Exception exception) {
        HashMap<String, String> params = new HashMap<>();
        params.put("description", exception.getMessage());
        Response.Message message = Response.Message.builder().key(Messages.INTERNAL).params(params).build();
        Response<?> response = Response.builder().type(Response.Type.FAILURE).message(message).data(null).build();
        return ResponseEntity.internalServerError().body(response);
    }

}
