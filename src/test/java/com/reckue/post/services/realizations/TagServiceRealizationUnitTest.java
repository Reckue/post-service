package com.reckue.post.services.realizations;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.models.Tag;
import com.reckue.post.repositories.TagRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Class TagServiceRealizationTest represents test for TagService class.
 *
 * @author Artur Magomedov
 */
public class TagServiceRealizationUnitTest extends PostServiceApplicationTests {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagServiceRealization tagService;

    @Test
    public void create() {
        Tag tag = Tag.builder().name("core").build();
        when(tagRepository.save(tag)).thenReturn(tag);

        assertEquals(tag, tagService.create(tag));
    }

    @Test
    public void update() {
        Tag tagRequest = Tag.builder()
                .id("1")
                .name("newName")
                .build();
        Tag tag = Tag.builder()
                .id("1")
                .name("code")
                .build();

        when(tagRepository.findById(tagRequest.getId())).thenReturn(Optional.of(tag));
        when(tagRepository.save(tag)).thenReturn(tag);

        tagService.update(tagRequest);

        assertEquals(tagRequest.getName(), tag.getName());
    }

    @Test
    public void updateTagWithNullId() {
        Tag tag = Tag.builder().build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> tagService.update(tag));
        assertEquals("The parameter is null", exception.getMessage());
    }

    @Test
    public void updateTagIfNotExistId() {
        Tag tag = Tag.builder().id("1").name("tag").build();
        when(tagRepository.existsById(tag.getId())).thenReturn(false);

        Exception exception = assertThrows(ModelNotFoundException.class, () -> tagService.update(tag));
        assertEquals("Tag by id " + tag.getId() + " is not found", exception.getMessage());
    }

    @Test
    public void findById() {
        Tag tag = Tag.builder().id("wild").name("gerald").build();
        doReturn(Optional.of(tag)).when(tagRepository).findById(Mockito.anyString());

        assertEquals(tag, tagService.findById(tag.getId()));
    }

    @Test
    public void findByIdIfNotExist() {
        Tag tag = Tag.builder().id("saturn").name("tricia").build();

        Exception exception = assertThrows(ModelNotFoundException.class, () -> tagService.findById(tag.getId()));
        assertEquals("Tag by id " + tag.getId() + " is not found", exception.getMessage());
    }

    @Test
    public void findAll() {
        Tag tag1 = Tag.builder().id("1").name("jack").build();
        Tag tag2 = Tag.builder().id("2").name("maria").build();

        List<Tag> tags = Stream.of(tag1, tag2).collect(Collectors.toList());

        when(tagRepository.findAll()).thenReturn(tags);
        assertEquals(tags, tagService.findAll());
    }

    @Test
    public void findAllAndSortByName() {
        Tag tag1 = Tag.builder().id("b1").name("kingArthur").build();
        Tag tag2 = Tag.builder().id("10").name("zerro").build();
        Tag tag3 = Tag.builder().id("999").name("2pack").build();

        List<Tag> tags = Stream.of(tag1, tag2, tag3).collect(Collectors.toList());
        when(tagRepository.findAll()).thenReturn(tags);

        List<Tag> expected = tags.stream()
                .sorted(Comparator.comparing(Tag::getName))
                .collect(Collectors.toList());

        assertNotEquals(tags, tagService.findAllAndSortByName());
        assertEquals(expected, tagService.findAllAndSortByName());
    }

    @Test
    public void findAllAndSortById() {
        Tag tag1 = Tag.builder().id("b2").name("13district").build();
        Tag tag2 = Tag.builder().id("7up").name("batman").build();
        Tag tag3 = Tag.builder().id("777").name("sotas").build();

        List<Tag> tags = Stream.of(tag1, tag2, tag3).collect(Collectors.toList());
        when(tagRepository.findAll()).thenReturn(tags);

        List<Tag> expected = tags.stream()
                .sorted(Comparator.comparing(Tag::getId))
                .collect(Collectors.toList());

        assertNotEquals(tags, tagService.findAllAndSortById());
        assertEquals(expected, tagService.findAllAndSortById());
    }

    @Test
    public void findAllBySortType() {
        Tag tag1 = Tag.builder().id("xxx").name("dizel").build();
        Tag tag2 = Tag.builder().id("11").name("11friendsOushen").build();
        Tag tag3 = Tag.builder().id("woodoo").name("john").build();

        List<Tag> tags = Stream.of(tag1, tag2, tag3).collect(Collectors.toList());
        when(tagRepository.findAll()).thenReturn(tags);

        List<Tag> sortedByNameExpected = tags.stream()
                .sorted(Comparator.comparing(Tag::getName))
                .collect(Collectors.toList());

        List<Tag> sortedByIdExpected = tags.stream()
                .sorted(Comparator.comparing(Tag::getId))
                .collect(Collectors.toList());

        assertEquals(sortedByNameExpected, tagService.findAllBySortType("name"));
        assertEquals(sortedByIdExpected, tagService.findAllBySortType("id"));
    }

    @Test
    public void findAllByTypeAndDesc() {
        Tag tag1 = Tag.builder().id("eleven").name("911").build();
        Tag tag2 = Tag.builder().id("112358").name("fibonnacci").build();
        Tag tag3 = Tag.builder().id("b8").name("0stap").build();

        List<Tag> tags = Stream.of(tag1, tag2, tag3).collect(Collectors.toList());
        when(tagRepository.findAll()).thenReturn(tags);

        List<Tag> sortedByNameExpected = tags.stream()
                .sorted(Comparator.comparing(Tag::getName))
                .collect(Collectors.toList());

        List<Tag> sortedByIdExpected = tags.stream()
                .sorted(Comparator.comparing(Tag::getId))
                .collect(Collectors.toList());

        List<Tag> sortedByNameDescExpected = tags.stream()
                .sorted(Comparator.comparing(Tag::getName).reversed())
                .collect(Collectors.toList());

        List<Tag> sortedByIdDescExpected = tags.stream()
                .sorted(Comparator.comparing(Tag::getId).reversed())
                .collect(Collectors.toList());

        assertEquals(sortedByNameExpected, tagService.findAllByTypeAndDesc("name", false));
        assertEquals(sortedByIdExpected, tagService.findAllByTypeAndDesc("id", false));
        assertEquals(sortedByNameDescExpected, tagService.findAllByTypeAndDesc("name", true));
        assertEquals(sortedByIdDescExpected, tagService.findAllByTypeAndDesc("id", true));
    }

    @Test
    public void findAllWithLimitOffsetSortAndDesc() {
        Tag tag1 = Tag.builder().id("3").name("a").build();
        Tag tag2 = Tag.builder().id("1").name("c").build();
        Tag tag3 = Tag.builder().id("4").name("b").build();
        Tag tag4 = Tag.builder().id("2").name("d").build();

        List<Tag> tags = Stream.of(tag1, tag2, tag3, tag4).collect(Collectors.toList());
        when(tagRepository.findAll()).thenReturn(tags);

        List<Tag> test1 = tags.stream()
                .sorted(Comparator.comparing(Tag::getName))
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());

        List<Tag> test2 = tags.stream()
                .sorted(Comparator.comparing(Tag::getName).reversed())
                .limit(3)
                .collect(Collectors.toList());

        List<Tag> test3 = tags.stream()
                .sorted(Comparator.comparing(Tag::getId))
                .limit(1)
                .skip(2)
                .collect(Collectors.toList());

        List<Tag> test4 = tags.stream()
                .sorted(Comparator.comparing(Tag::getId))
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());

        List<Tag> test5 = tags.stream()
                .sorted(Comparator.comparing(Tag::getId).reversed())
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());

        assertEquals(test1, tagService.findAll(2, 1, "name", false));
        assertEquals(test2, tagService.findAll(3, 0, "name", true));
        assertEquals(test3, tagService.findAll(1, 2, "id", false));
        assertEquals(test4, tagService.findAll(2, 1, "id", false));
        assertEquals(test5, tagService.findAll(2, 1, "id", true));
    }

    @Test
    public void deleteById() {
        Tag tag = Tag.builder()
                .id("1")
                .name("tag")
                .build();
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);

        when(tagRepository.existsById(tag.getId())).thenReturn(true);
        doAnswer(invocation -> {
            tags.remove(tag);
            return null;
        }).when(tagRepository).deleteById(tag.getId());
        tagRepository.deleteById(tag.getId());

        assertEquals(0, tags.size());
    }

    @Test
    public void deleteByIdWithException() {
        Tag tag = Tag.builder().id("0").name("name").build();

        Exception exception = assertThrows(ModelNotFoundException.class, () -> tagService.deleteById(tag.getId()));
        assertEquals("Tag by id " + tag.getId() + " is not found", exception.getMessage());
    }
}
