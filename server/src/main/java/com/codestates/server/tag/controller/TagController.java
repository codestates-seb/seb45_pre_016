package com.codestates.server.tag.controller;

import com.codestates.server.dto.MultiResponseDto;
import com.codestates.server.tag.dto.TagResponseDto;
import com.codestates.server.tag.entity.Tag;
import com.codestates.server.tag.mapper.TagMapper;
import com.codestates.server.tag.service.TagService;
import com.codestates.server.user.dto.UserResponseDto;
import com.codestates.server.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

/*
 * 태그 기능 :
 * get tags -> 태그 전체 조회
 *
 */
@RestController
@RequestMapping("/tags")
@Validated
@AllArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper mapper;

    @GetMapping
    public ResponseEntity<TagResponseDto> getTags() {

        // ⏹️ Pagination 적용 전
        List<Tag> tags = tagService.getTags();
        List<TagResponseDto> response = mapper.tagsToTagResponseDto(tags);

        return new  ResponseEntity(response, HttpStatus.OK);
    }


    /*
     * Pagination 구현한 getTags
     */
//    @GetMapping
//    public ResponseEntity getTags(@Positive @RequestParam int page,
//                                   @Positive @RequestParam int size) {
//
//        Page<Tag> tagPages = tagService.getTags(page -1, size);
//        List<Tag> tags = tagPages.getContent();
//
//        return  new ResponseEntity(
//                new MultiResponseDto<>(mapper.tagsToTagResponseDto(tags), tagPages), HttpStatus.OK);
//                new MultiResponseDto<>(mapper.tagsToTagResponseDto(tags), userPages), HttpStatus.OK);
//    }


}
