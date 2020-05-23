package com.reckue.post.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Node {

    @Id
    String id;

    NodeType type;
    String contentId;
    String source;
    StatusType status;
}
