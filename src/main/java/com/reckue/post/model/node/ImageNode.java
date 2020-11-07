package com.reckue.post.model.node;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class ImageNode is responsible for displaying images.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ImageNode implements Parent {

    private String imageUrl;
}
