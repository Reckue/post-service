package com.reckue.post.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class Comment is responsible for model that works with comments.
 *
 * @author Artur Magomedov
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document
public class Comment extends BaseModel implements Serializable {

    @Id
    private String id;

    private String postId;
    private String commentId;
    private List<Node> nodes;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modificationDate;

    @Builder
    public Comment(String userId, String id, String postId, String commentId, List<Node> nodes,
                   LocalDateTime createdDate, LocalDateTime modificationDate) {
        super(userId);
        this.id = id;
        this.postId = postId;
        this.commentId = commentId;
        this.nodes = nodes;
        this.createdDate = createdDate;
        this.modificationDate = modificationDate;
    }
}
