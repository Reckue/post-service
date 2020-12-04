package com.reckue.post.model;

import com.reckue.post.model.node.Parent;
import com.reckue.post.model.type.NodeType;
import com.reckue.post.model.type.ParentType;
import com.reckue.post.model.type.StatusType;
import lombok.*;
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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document
public class Node extends BaseModel implements Serializable {

    @Id
    private String id;

    // required fields
    private NodeType type;
    private Parent node;
    private String parentId;
    private ParentType parentType;

    // standard types
    private String source;
    private StatusType status;

    @LastModifiedDate
    private LocalDateTime modificationDate;
    @CreatedDate
    private LocalDateTime createdDate;

    @Builder
    public Node(String userId, String id, NodeType type, Parent node, String parentId, ParentType parentType,
                String source, StatusType status, LocalDateTime modificationDate, LocalDateTime createdDate) {
        super(userId);
        this.id = id;
        this.type = type;
        this.node = node;
        this.parentId = parentId;
        this.parentType = parentType;
        this.source = source;
        this.status = status;
        this.modificationDate = modificationDate;
        this.createdDate = createdDate;
    }
}
