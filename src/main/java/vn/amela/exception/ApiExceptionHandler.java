package vn.amela.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import vn.amela.response.ResponseObject;
import vn.amela.response.Status;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllEdxception(Exception exception, WebRequest request) {
        Status status = new Status("100", exception.getLocalizedMessage());
        ResponseObject responseObject = new ResponseObject(null, status);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerEdxception(Exception exception, WebRequest request) {
        Status status = new Status("101", exception.getLocalizedMessage());
        ResponseObject responseObject = new ResponseObject(null, status);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
