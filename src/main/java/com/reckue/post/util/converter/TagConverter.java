package com.reckue.post.util.converter;

import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.generated.controller.dto.TagRequestDto;
import com.reckue.post.generated.controller.dto.TagResponseDto;
import com.reckue.post.model.Tag;

/**
 * Class for converting TagRequest object to Tag and Tag object to TagResponse.
 *
 * @author Daria Smirnova
 */
public class TagConverter {

    /**
     * Converts from TagRequest to Tag.
     *
     * @param tagRequest the object of class TagRequest
     * @return the object of class Tag
     */
    public static Tag convert(TagRequestDto tagRequest) {
        if (tagRequest == null) {
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }
        return Tag.builder()
                .name(tagRequest.getName())
                .build();
    }

    /**
     * Converts from Tag to TagResponse.
     *
     * @param tag the object of class Tag
     * @return the object of class TagResponse
     */
    public static TagResponseDto convert(Tag tag) {
        if (tag == null) {
            throw new ReckueIllegalArgumentException("Null parameters are not allowed");
        }
        return TagResponseDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}
