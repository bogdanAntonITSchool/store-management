package ro.itschool.store_management.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.itschool.store_management.exception.InvalidPayloadException;


@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(InvalidPayloadException.class)
    public ResponseEntity<ProblemDto> handleInvalidPayloadException(InvalidPayloadException ex) {
        return ResponseEntity.badRequest().body(
                new ProblemDto(ex.getMessage(), HttpStatus.BAD_REQUEST.toString())
        );
    }

}
