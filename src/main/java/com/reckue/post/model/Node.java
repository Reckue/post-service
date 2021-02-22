package com.reckue.post.model;

import com.reckue.post.model.type.NodeType;
import com.reckue.post.model.type.StatusType;
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

    private NodeType type;

    private Object content;

    private String source;

    private String userId;

    private StatusType status;

    @LastModifiedDate
    private LocalDateTime modificationDate;

    @CreatedDate
    private LocalDateTime createdDate;
}
