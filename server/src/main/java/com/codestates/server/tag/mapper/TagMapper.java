package com.codestates.server.tag.mapper;

import com.codestates.server.tag.dto.TagPostDto;
import com.codestates.server.tag.dto.TagResponseDto;
import com.codestates.server.tag.entity.Tag;
import com.codestates.server.user.dto.UserResponseDto;
import com.codestates.server.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag tagPostDtoToTag(TagPostDto tagPostDto);

    List<TagResponseDto> tagsToTagResponseDto(List<Tag> tags);
}
