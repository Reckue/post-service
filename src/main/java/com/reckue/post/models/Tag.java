package com.reckue.post.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class Tag is responsible for model that works with tags.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    private String id;

    private String name;
}
