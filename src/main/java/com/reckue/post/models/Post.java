package com.reckue.post.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document
public class Post {

    @Id
    private String id;

    private String title;
    private List<Node> nodes;
    private String source;
    private List<Tag> tags;
    private long published;
    private long changed;
    private StatusType status;
}
