package it.sbcourse.sbpostit;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleToDoNotFoundException(ResourceNotFoundException ex, WebRequest req) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("caused-by", ex.getMessage());
        return super.handleExceptionInternal(ex, null, httpHeaders, HttpStatus.NOT_FOUND, req);
    }
}
