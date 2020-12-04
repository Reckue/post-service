package com.reckue.post.model;

import com.reckue.post.model.type.PostStatusType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class Post is responsible for model that works with posts.
 *
 * @author Iveri Narozashvili
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document
public class Post extends BaseModel implements Serializable {

    @Id
    private String id;

    private String title;
    private List<Node> nodes;
    private String source;
    private List<Tag> tags;
    private PostStatusType status;

    @LastModifiedDate
    private LocalDateTime modificationDate;
    @CreatedDate
    private LocalDateTime createdDate;

    @Builder
    public Post(String userId, String id, String title, List<Node> nodes, String source, List<Tag> tags,
                PostStatusType status, LocalDateTime modificationDate, LocalDateTime createdDate) {
        super(userId);
        this.id = id;
        this.title = title;
        this.nodes = nodes;
        this.source = source;
        this.tags = tags;
        this.status = status;
        this.modificationDate = modificationDate;
        this.createdDate = createdDate;
    }
}
