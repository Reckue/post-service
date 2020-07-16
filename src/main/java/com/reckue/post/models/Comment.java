package com.reckue.post.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class Comment is responsible for model that works with comments.
 *
 * @author Artur Magomedov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Comment {

    @Id
    private String id;

    private String text;
    private String userId;
    private String postId;
    private List<Comment> comments;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modificationDate;
}
