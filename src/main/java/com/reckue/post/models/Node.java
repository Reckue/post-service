package com.reckue.post.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Node {

    @Id
    private String id;

    private NodeType type;
    private String contentId;
    private String source;
    private StatusType status;
}
