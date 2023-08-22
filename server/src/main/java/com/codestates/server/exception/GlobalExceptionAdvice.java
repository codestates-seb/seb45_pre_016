package com.codestates.server.exception;

import com.codestates.server.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionAdvice {
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public List<ErrorResponse.FieldError> handleMethodArgumentNotValidException(
//            MethodArgumentNotValidException e) {
//        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());
//        return response.getFieldErrors();
//    }

//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
//            MethodArgumentNotValidException e) {
//        ErrorResponse response = ErrorResponse.of(e.getBindingResult());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());
        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse.ConstraintViolationError> handleConstraintViolationException(
            ConstraintViolationException e) {
        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());
        return response.getViolationErrors();
    }
//    @ExceptionHandler
//    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
//        final ErrorResponse.ExceptionStatus response = ErrorResponse.exceptionStatus(e.getExceptionCode());
//        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getExceptionCode().getStatus()));
//    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse exceptionExample(BusinessLogicException e){
        final ErrorResponse response = ErrorResponse.of(HttpStatus.NOT_FOUND, e.getMessage());

        return response;
    }
}
