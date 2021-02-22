//package com.reckue.post.service.prevent;
//
//import com.reckue.post.model.Post;
//import com.reckue.post.service.PostService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//public class PostServiceRuntimeExceptions {
//
//    @Autowired
//    private PostService postService;
//
//    @Test
//    public void createPostButRuntimeExceptionCauseNullPost() {
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            postService.create(null, null);
//        });
//        assertEquals( "Post is null", exception.getMessage());
//    }
//
//    @Test
//    public void createPostButRuntimeExceptionCauseNullTokenInfo() {
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            postService.create(new Post(), null);
//        });
//        assertEquals( "TokenInfo is null", exception.getMessage());
//    }
//
//    @Test
//    public void findAllPostByTitleButRuntimeExceptionCauseNullTitleString() {
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            postService.findAllByTitle(null);
//        });
//        assertEquals( "Title is null", exception.getMessage());
//    }
//
//    @Test
//    public void deletePostByIdButRuntimeExceptionCauseNullIdString() {
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            postService.deleteById(null, null);
//        });
//        assertEquals( "Id is null", exception.getMessage());
//    }
//
//    @Test
//    public void findPostByIdButRuntimeExceptionCauseNullIdString() {
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            postService.findById(null);
//        });
//        assertEquals( "Id is null", exception.getMessage());
//    }
//}
