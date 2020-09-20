package com.reckue.post.models;

import com.reckue.post.models.types.PostStatusType;
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
 * Class Post is responsible for model that works with posts.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Post implements Cloneable {

    @Id
    private String id;

    private String title;
    private List<Node> nodes;
    private String source;
    private String userId;
    private List<Tag> tags;
    private PostStatusType status;

    @LastModifiedDate
    private LocalDateTime modificationDate;
    @CreatedDate
    private LocalDateTime createdDate;

    @Override
    public Post clone() {
        try {
            return (Post) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
