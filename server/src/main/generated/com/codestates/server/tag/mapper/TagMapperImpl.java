package com.codestates.server.tag.mapper;

import com.codestates.server.tag.entity.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-16T13:16:53+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public List<Tag> tagsToTagResponseDto(List<Tag> tags) {
        if ( tags == null ) {
            return null;
        }

        List<Tag> list = new ArrayList<Tag>( tags.size() );
        for ( Tag tag : tags ) {
            list.add( tag );
        }

        return list;
    }
}
