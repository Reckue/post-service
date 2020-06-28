package com.reckue.post.models.nodes;

import com.reckue.post.utils.NodeWrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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
public class ImageNode {

    @Id
    private String id;

    private String imageUrl;
}
