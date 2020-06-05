package com.reckue.post.services;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.models.Tag;
import com.reckue.post.repositories.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;

@AutoConfigureMockMvc
@ActiveProfiles("develop")
public class TagServiceTest extends PostServiceApplicationTests {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void createTagTest() {
        Tag expected = Tag.builder()
                .id("1")
                .name("name")
                .build();

        Tag actual = tagService.create(expected);
        Assertions.assertEquals(expected, actual);
    }
}
