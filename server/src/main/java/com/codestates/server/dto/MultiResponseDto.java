package com.codestates.server.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

/*
 *    저도 일단 Pagination 구현한다고 MultiResponseDto를 만들었는데
 *    만약에 한다면 question에 Response 를
 *    package com.codestates.server.dto;
 *    에 옮겨 놓으면 될 듯 합니다.
 */
@Getter
public class MultiResponseDto<T> {

    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
