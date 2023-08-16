package com.codestates.server.tag.service;

import com.codestates.server.tag.entity.Tag;
import com.codestates.server.tag.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    /*
     * Pagination 구현한 getTags()
     */
//    public Page<Tag> getTags(int page, int size) {
//    // ⏹️ pagination 변경 예정
//    return tagRepository.findAll(PageRequest.of(page, size,
//            Sort.by("tagId").descending()));
//
//    }

    public List<Tag> getTags() {
        // ⏹️ pagination 변경 예정

        List<Tag> tags = tagRepository.findAll();

        return tags;
    }
}
