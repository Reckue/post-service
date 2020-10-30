package com.reckue.post.services.realizations;

import com.reckue.post.PostServiceApplicationTests;
import com.reckue.post.exceptions.ReckueIllegalArgumentException;
import com.reckue.post.exceptions.models.post.PostNotFoundException;
import com.reckue.post.models.Comment;
import com.reckue.post.models.Node;
import com.reckue.post.models.Post;
import com.reckue.post.models.Tag;
import com.reckue.post.models.types.NodeType;
import com.reckue.post.models.types.ParentType;
import com.reckue.post.models.types.PostStatusType;
import com.reckue.post.repositories.NodeRepository;
import com.reckue.post.repositories.PostRepository;
import com.reckue.post.services.NodeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.xmlunit.util.Nodes;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Class PostServiceRealizationTest represents test for PostService class.
 *
 * @author Viktor Grigoriev
 */
@SuppressWarnings("unused")
class PostServiceRealizationTest extends PostServiceApplicationTests {

    private Post newPost;
    private Post postWithId;
    private Post savedPost;
    private Post updatedPost;

    private Node newNode;
    private Node savedNode;

    @Mock
    private PostRepository postRepository;

    @Mock
    private NodeService nodeService;

    @Mock
    private NodeRepository nodeRepository;

    @Mock
    private TokenStore tokenStore;

    @Mock
    private OAuth2AccessToken accessToken;

    @InjectMocks
    private PostServiceRealization postService;

    @BeforeEach
    void initTest() {
        when(tokenStore.readAccessToken("token"))
                .thenReturn(accessToken);
        when(accessToken.getAdditionalInformation())
                .thenReturn(new HashMap<>() {{ put("userId", "1"); }});

        newNode = Node.builder()
                .type(NodeType.TEXT)
                .build();
        List<Node> nodes = new ArrayList<>();
        nodes.add(newNode);

        savedNode = Node.builder()
                .id("nodeId")
                .parentId("postId")
                .parentType(ParentType.POST)
                .type(NodeType.TEXT)
                .build();
        List<Node> savedNodes = new ArrayList<>();
        savedNodes.add(savedNode);

        List<Node> updatedNodes = new ArrayList<>();
        updatedNodes.add(Node.builder()
                .id("updatedId")
                .parentId("postId")
                .parentType(ParentType.POST)
                .type(NodeType.CODE)
                .build());

        when(nodeService.create(newNode, "token"))
                .thenReturn(savedNode);

        newPost = Post.builder()
                .title("Title")
                .nodes(nodes)
                .build();

        postWithId = Post.builder()
                .id("postId")
                .userId("1")
                .title("Title")
                .nodes(nodes)
                .build();

        savedPost = Post.builder()
                .id("postId")
                .userId("1")
                .title("Title")
                .nodes(savedNodes)
                .build();
    }

    @Test
    public void create() {
        when(postRepository.save(newPost)).thenReturn(postWithId);
        assertEquals(savedPost, postService.create(newPost, "token"));
    }

    @Disabled
    public void update() {
        Node node = mock(Node.class);
        Tag tag = mock(Tag.class);
        Post postRequest = Post.builder()
                .id("1")
                .title("newTitle")
                .nodes(Collections.singletonList(node))
                .source("newSource")
                .userId("1")
                .status(PostStatusType.DRAFT)
                .tags(Collections.singletonList(tag))
                .build();
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.findById(postRequest.getId())).thenReturn(Optional.of(postOne));
        when(postRepository.save(postOne)).thenReturn(postOne);

        postService.update(postRequest, "token");

        Assertions.assertAll(
                () -> assertEquals(postRequest.getTitle(), postOne.getTitle()),
                () -> assertEquals(postRequest.getNodes(), postOne.getNodes()),
                () -> assertEquals(postRequest.getSource(), postOne.getSource()),
                () -> assertEquals(postRequest.getUserId(), postOne.getUserId()),
                () -> assertEquals(postRequest.getStatus(), postOne.getStatus()),
                () -> assertEquals(postRequest.getTags(), postOne.getTags())
        );
    }

    @Test
    public void updateWithNullId() {
        Post postOne = Post.builder()
                .title("postOne")
                .build();

        assertThrows(ReckueIllegalArgumentException.class, () -> postService.update(postOne, "token"));
    }

    @Test
    public void updateWithExistId() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .nodes(new ArrayList<>())
                .build();
        when(postRepository.existsById(postOne.getId())).thenReturn(false);
        when(postRepository.save(postOne)).thenReturn(postOne);

        assertThrows(PostNotFoundException.class, () -> postService.update(postOne, "token"));
    }

    @Test
    public void findById() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.findById(postOne.getId())).thenReturn(Optional.of(postOne));

        assertEquals(postOne, postService.findById(postOne.getId()));
    }

    @Test
    public void findByIdWithException() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.findById(postOne.getId())).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> postService.findById(postOne.getId()));
    }

    @Test
    public void findAll() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        Post postTwo = Post.builder()
                .id("2")
                .title("postTwo")
                .build();
        List<Post> posts = List.of(postOne, postTwo);
        when(postRepository.findAll()).thenReturn(posts);

        for (Post post : posts) {
            doReturn(Optional.of(post)).when(postRepository).findById(post.getId());
        }

        assertEquals(posts, postService.findAll());
    }

    @Test
    public void findAllSortById() {
        Post postOne = Post.builder().id("1").build();
        Post postTwo = Post.builder().id("2").build();
        Post postThree = Post.builder().id("3").build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        for (Post post : posts) {
            doReturn(Optional.of(post)).when(postRepository).findById(post.getId());
        }

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getId))
                .collect(Collectors.toList());

        assertEquals(expected, postService.findAllAndSortById());
    }

    @Test
    public void findAllSortByTitle() {
        Post postOne = Post.builder().title("postOne").nodes(Collections.emptyList()).build();
        Post postTwo = Post.builder().title("postTwo").nodes(Collections.emptyList()).build();
        Post postThree = Post.builder().title("postThree").nodes(Collections.emptyList()).build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

//        for (Post post : posts) {
//            doReturn(Optional.of(post)).when(postRepository).findById(post.getId());
//        }

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getTitle))
                .collect(Collectors.toList());

        assertEquals(expected, postService.findAllAndSortByTitle());
    }

    @Test
    public void findAllSortBySource() {
        Post postOne = Post.builder().source("sourceOne").build();
        Post postTwo = Post.builder().source("sourceTwo").build();
        Post postThree = Post.builder().source("sourceThree").build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getSource))
                .collect(Collectors.toList());

        for (Post post : posts) {
            doReturn(Optional.of(post)).when(postRepository).findById(post.getId());
        }

        assertEquals(expected, postService.findAllAndSortBySource());
    }

    @Test
    public void findAllSortByUserId() {
        Post postOne = Post.builder().userId("Max").build();
        Post postTwo = Post.builder().userId("Will").build();
        Post postThree = Post.builder().userId("Arny").build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getUserId))
                .collect(Collectors.toList());

        for (Post post : posts) {
            doReturn(Optional.of(post)).when(postRepository).findById(post.getId());
        }

        assertEquals(expected, postService.findAllAndSortByUserId());
    }

    @Test
    public void findAllSortByPublished() {
        Post postOne = Post.builder()
                .createdDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(1), TimeZone.getDefault().toZoneId()))
                .build();
        Post postTwo = Post.builder()
                .createdDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(2), TimeZone.getDefault().toZoneId()))
                .build();
        Post postThree = Post.builder()
                .createdDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(3), TimeZone.getDefault().toZoneId()))
                .build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedDate))
                .collect(Collectors.toList());

        for (Post post : posts) {
            doReturn(Optional.of(post)).when(postRepository).findById(post.getId());
        }

        assertEquals(expected, postService.findAllAndSortByCreatedDate());
    }

    @Test
    public void findAllSortByChanged() {
        Post postOne = Post.builder()
                .modificationDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(1), TimeZone.getDefault().toZoneId()))
                .build();
        Post postTwo = Post.builder()
                .modificationDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(2), TimeZone.getDefault().toZoneId()))
                .build();
        Post postThree = Post.builder()
                .modificationDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(3), TimeZone.getDefault().toZoneId()))
                .build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        for (Post post : posts) {
            doReturn(Optional.of(post)).when(postRepository).findById(post.getId());
        }

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getModificationDate))
                .collect(Collectors.toList());

        assertEquals(expected, postService.findAllAndSortByModificationDate());
    }

    @Test
    public void findAllSortByStatus() {
        Post postOne = Post.builder().status(PostStatusType.DRAFT).build();
        Post postTwo = Post.builder().status(PostStatusType.BANNED).build();
        Post postThree = Post.builder().status(PostStatusType.DELETED).build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getStatus))
                .collect(Collectors.toList());

        for (Post post : posts) {
            doReturn(Optional.of(post)).when(postRepository).findById(post.getId());
        }

        assertEquals(expected, postService.findAllAndSortByStatus());
    }

    @Test
    public void findAllSortByTypeAndDesc() {
        Post postOne = Post.builder().userId("Max").build();
        Post postTwo = Post.builder().userId("Will").build();
        Post postThree = Post.builder().userId("Arny").build();
        List<Post> posts = List.of(postOne, postTwo, postThree);
        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getUserId).reversed())
                .collect(Collectors.toList());

        for (Post post : posts) {
            doReturn(Optional.of(post)).when(postRepository).findById(post.getId());
        }

        assertEquals(expected, postService.findAllByTypeAndDesc("userId", true));
    }

    @Test
    public void findAllWithArgs() {
        Post postOne = Post.builder().userId("Max").build();
        Post postTwo = Post.builder().userId("Will").build();
        Post postThree = Post.builder().userId("Arny").build();
        List<Post> posts = List.of(postOne, postTwo, postThree);

        when(postRepository.findAll()).thenReturn(posts);

        List<Post> expected = posts.stream()
                .sorted(Comparator.comparing(Post::getUserId).reversed())
                .limit(3)
                .skip(1)
                .collect(Collectors.toList());

        for (Post post : posts) {
            doReturn(Optional.of(post)).when(postRepository).findById(post.getId());
        }

        assertEquals(expected, postService.findAll(3, 1, "userId", true));
    }

    @Test
    public void findAllWithIllegalArgLimit() {
        assertThrows(ReckueIllegalArgumentException.class,
                () -> postService.findAll(-1, 1, "name", true));
    }

    @Test
    public void findAllWithIllegalArgOffset() {
        assertThrows(ReckueIllegalArgumentException.class,
                () -> postService.findAll(1, -1, "name", true));
    }

    @Disabled
    public void deleteById() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        List<Post> posts = new ArrayList<>();
        posts.add(postOne);
        when(postRepository.existsById(postOne.getId())).thenReturn(true);
        doAnswer(invocation -> {
            posts.remove(postOne);
            return null;
        }).when(postRepository).deleteById(postOne.getId());
        postService.deleteById(postOne.getId(), "token");

        assertEquals(0, posts.size());
    }

    @Test
    public void deleteByIdWithNotFoundException() {
        Post postOne = Post.builder()
                .id("1")
                .title("postOne")
                .build();
        when(postRepository.existsById(postOne.getId())).thenReturn(false);

        assertThrows(PostNotFoundException.class, () -> postService.deleteById(postOne.getId(), "token"));
    }
}
