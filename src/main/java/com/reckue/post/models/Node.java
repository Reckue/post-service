package com.reckue.post.models;

import com.reckue.post.models.nodes.Parent;
import com.reckue.post.models.types.NodeType;
import com.reckue.post.models.types.ParentType;
import com.reckue.post.models.types.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Node implements Serializable {

    @Id
    private String id;

    // required fields
    private NodeType type;
    private Parent node;
    private String parentId;
    private ParentType parentType;

    // standard types
    private String source;
    private String userId;
    private StatusType status;

    @LastModifiedDate
    private LocalDateTime modificationDate;
    @CreatedDate
    private LocalDateTime createdDate;
}
