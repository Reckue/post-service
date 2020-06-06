package com.reckue.post.models;

import com.reckue.post.utils.NodeContent;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Class ImageNode is responsible for displaying images.
 *
 * @author Iveri Narozashvili
 */
@Data
@Document
public class ImageNode implements NodeContent {

    @Id
    private String id;

    private String imageUrl;
}
