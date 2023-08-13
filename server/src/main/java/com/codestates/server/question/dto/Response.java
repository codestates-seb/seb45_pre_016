package com.codestates.server.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * API 넘길때 어떻게 넘기면 좋을지 몰라서 일단 만들어놨습니다..!
 * ㄴ 솔 : 좋아요 얘도 global 로 사용해요
 */
@Getter
@Setter
@AllArgsConstructor
public class Response<T> {
    private Long userId;
    private T data;
}
