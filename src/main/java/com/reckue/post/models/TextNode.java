package com.reckue.post.models;

import com.reckue.post.utils.NodeContent;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class TextNode represents model of text fields.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
@Document
public class TextNode implements NodeContent {

    private String content;
}
