package com.reckue.post.utils.converters;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.models.Tag;
import com.reckue.post.transfers.TagResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConverterTest extends PostServiceApplicationTests {

    @Test
    void convert() {
        Tag expected = Tag.builder()
                .id("1")
                .name("sherzod")
                .build();

        TagResponse actual = Converter.convert(expected, TagResponse.class);
        assertTrue(actual instanceof TagResponse);
    }
}
