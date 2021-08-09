package vn.amela.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<?> getResponseEntity(Object data, Status status) {
        return new ResponseEntity<>(new ResponseObject(data, status), HttpStatus.OK);
    }

    public static ResponseEntity<?> getResponseEntity(ResponseObject responseObject) {
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
