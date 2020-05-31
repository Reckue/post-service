package com.reckue.post.converters;

import com.reckue.post.models.Tag;
import com.reckue.post.transfers.TagRequest;
import com.reckue.post.transfers.TagResponse;

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
    public static Tag convert(TagRequest tagRequest) {
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
    public static TagResponse convert(Tag tag) {
        return TagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}
