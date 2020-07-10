package com.reckue.post.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

/**
 * Class Rating is responsible for model that works with rating.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Rating {

   @Id
    private String id;

    private String userId;

    private String postId;

    private long published;
}