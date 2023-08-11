package com.codestates.server.question.uri;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * URI 만들어줘야할것같아서 추가했습니다. 확인해보고 빼야하면 뺄게요
 */

public class UriCreator {
    public static URI createUri(String defaultUri, long resourceId){
        return UriComponentsBuilder
                .newInstance()
                .path(defaultUri + "/{resourceId}")
                .buildAndExpand(resourceId)
                .toUri();
    }
}
