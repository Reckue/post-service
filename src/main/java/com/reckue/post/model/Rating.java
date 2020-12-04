package com.reckue.post.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Class Rating is responsible for model that works with rating.
 *
 * @author Iveri Narozashvili
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document
@SuppressWarnings("unused")
public class Rating extends BaseModel implements Serializable {

    @Id
    private String id;

    private String postId;

    @LastModifiedDate
    private LocalDateTime modificationDate;
    @CreatedDate
    private LocalDateTime createdDate;

    @Builder
    public Rating(String userId, String id, String postId, LocalDateTime modificationDate, LocalDateTime createdDate) {
        super(userId);
        this.id = id;
        this.postId = postId;
        this.modificationDate = modificationDate;
        this.createdDate = createdDate;
    }
}
