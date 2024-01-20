package at.tugraz.oop2;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(CustomBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleBadRequest(CustomBadRequestException e) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "400 - " + e.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(CustomInternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleInternalServerError(CustomInternalServerErrorException e) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "500 - " + e.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleNotFound(CustomNotFoundException e) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "404 - " + e.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

}


