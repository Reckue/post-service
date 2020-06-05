package com.reckue.post.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class Node is responsible for displaying all types of nodes.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
@Document
public class Node {

    @Id
    private String id;

    private NodeType type;
    private String contentId;
    private String source;
    private String username;
    private StatusType status;
    private long published;
}
