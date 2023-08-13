package com.codestates.server.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * API 넘길때 어떻게 넘기면 좋을지 몰라서 일단 만들어놨습니다..!
 */
@Getter
@Setter
public class Response<T> extends ResponseEntity<T> {

    private Long userId;
    private T data;

    public Response(T body, HttpStatus status) {
        super(body, status);
    }
}
