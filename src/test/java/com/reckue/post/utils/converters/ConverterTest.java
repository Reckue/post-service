package com.reckue.post.utils.converters;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.models.Tag;
import com.reckue.post.transfers.TagResponse;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class ConverterTest allows to test all methods of class Converter.
 *
 * @author Kamila Meshcheryakova
 */
class ConverterTest extends PostServiceApplicationTests {

    @Test
    void convertTagToTagResponse() {
        Tag tag = Tag.builder()
                .id("1")
                .name("sherzod")
                .build();

        TagResponse expected = TagResponse.builder().
                id(tag.getId())
                .name(tag.getName())
                .build();

        TagResponse actual = Converter.convert(tag, TagResponse.class);
        assertEquals(expected, actual);
    }

    @Test
    void checkTagIsNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Converter.convert((Object) null, TagResponse.class));
        assertEquals("Null parameters are not allowed", exception.getMessage());
    }

    @Test
    void convertListToAnotherList() {
        Tag tag1 = Tag.builder()
                .id("1")
                .name("daria")
                .build();
        Tag tag2 = Tag.builder()
                .id("2")
                .name("camelya")
                .build();
        List<Tag> tags = Stream.of(tag1,tag2).collect(Collectors.toList());

        TagResponse tagResponse1 = TagResponse.builder()
                .id(tag1.getId())
                .name(tag1.getName())
                .build();
        TagResponse tagResponse2 = TagResponse.builder()
                .id(tag2.getId())
                .name(tag2.getName())
                .build();

        List<TagResponse> expected = Stream.of(tagResponse1, tagResponse2).collect(Collectors.toList());

        List<TagResponse> actual = Converter.convert(tags, TagResponse.class);
        assertEquals(expected, actual);
    }

    @Test
    void checkListIsNull() {
        List<Tag> tags = null;
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Converter.convert(tags, TagResponse.class));
        assertEquals("Null parameters are not allowed", exception.getMessage());
    }
}
