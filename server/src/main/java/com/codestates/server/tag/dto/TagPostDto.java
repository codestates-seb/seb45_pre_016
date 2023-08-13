package com.codestates.server.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TagPostDto {

    // ⏹️ notnull 이나 notblank가 필요할까요?
    private String tagName;

}
