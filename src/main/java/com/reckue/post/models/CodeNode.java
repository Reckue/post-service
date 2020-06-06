package com.reckue.post.models;

import com.reckue.post.utils.NodeContent;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class CodeNode represents model of code fields.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
@Document
public class CodeNode implements NodeContent {

    @Id
    private String id;

    private LangType language;
    private String content;
}
