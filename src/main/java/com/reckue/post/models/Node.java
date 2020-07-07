package com.reckue.post.models;

import com.reckue.post.models.nodes.Parent;
import com.reckue.post.models.types.NodeType;
import com.reckue.post.models.types.StatusType;
import com.reckue.post.utils.NodeContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class Node is responsible for displaying all types of nodes.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Node {

    @Id
    private String id;

    // required fields
    private NodeType type;
    private Parent node;
    private String postId;

    // standard types
    private String source;
    private String userId;
    private long published;
    private StatusType status;
}
