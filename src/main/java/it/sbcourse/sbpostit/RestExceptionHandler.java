package it.sbcourse.sbpostit;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.sbcourse.sbpostit.postit.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest req) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("caused-by", ex.getMessage());
        return super.handleExceptionInternal(ex, null, headers, HttpStatus.NOT_FOUND, req);
    }
}
