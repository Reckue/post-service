package com.reckue.post.models;

import com.reckue.post.utils.NodeContent;

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
    private NodeContent content;
    private String source;
    private String username;
    private long published;
    private StatusType status;
}
