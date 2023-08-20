package com.codestates.server.tag.mapper;

import com.codestates.server.tag.dto.TagPostDto;
import com.codestates.server.tag.dto.TagResponseDto;
import com.codestates.server.tag.entity.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-18T22:07:51+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public Tag tagPostDtoToTag(TagPostDto tagPostDto) {
        if ( tagPostDto == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setTagName( tagPostDto.getTagName() );

        return tag;
    }

    @Override
    public List<TagResponseDto> tagsToTagResponseDto(List<Tag> tags) {
        if ( tags == null ) {
            return null;
        }

        List<TagResponseDto> list = new ArrayList<TagResponseDto>( tags.size() );
        for ( Tag tag : tags ) {
            list.add( tagToTagResponseDto( tag ) );
        }

        return list;
    }

    protected TagResponseDto tagToTagResponseDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagResponseDto tagResponseDto = new TagResponseDto();

        tagResponseDto.setTagId( tag.getTagId() );
        tagResponseDto.setTagName( tag.getTagName() );

        return tagResponseDto;
    }
}
