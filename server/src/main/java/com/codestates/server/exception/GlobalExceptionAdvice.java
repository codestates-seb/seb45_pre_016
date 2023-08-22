package com.codestates.server.exception;

import com.codestates.server.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    // 유효성검사 에러 발생 처리
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());
        return response;
    }

    // 제약 조건 위반으로 발생한 예외 처리
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse.ConstraintViolationError> handleConstraintViolationException(
            ConstraintViolationException e) {
        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());
        return response.getViolationErrors();
    }

    // 비즈니스 로직 예외 처리
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse exceptionExample(BusinessLogicException e){
        final ErrorResponse response = ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
        return response;
    }

    // 맞지않은 HTTP 요청메서드 예외 처리
    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e){
        final ErrorResponse response = ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse hadleHttpMessageNotReadableException (
            HttpMessageNotReadableException e){
        final ErrorResponse response = ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());

        return response;
    }

    // 일반 예외 처리 메서드
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        log.error("⭐⭐⭐ 서버 에러 : ⭐⭐⭐", e);
        final ErrorResponse response = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

}
