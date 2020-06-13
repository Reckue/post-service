package com.reckue.post.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Class Post is responsible for model that works with posts.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
@Document
public class Post {

    @Id
    private String id;

    private String title;
    private List<Node> nodes;
    private String source;
    private String username;
    private List<Tag> tags;
    private long published;
    private long changed;
    private StatusType status;
}
