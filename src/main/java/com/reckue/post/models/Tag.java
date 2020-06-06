package com.reckue.post.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class Tag is responsible for model that works with tags.
 *
 * @author Iveri Narozashvili
 */
@Data
@Document
@Builder
public class Tag {

    @Id
    private String id;

    private String name;
}
