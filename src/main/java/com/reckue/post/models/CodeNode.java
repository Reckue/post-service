package com.reckue.post.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CodeNode {

    @Id
    private String id;

    private LangType language;
    private String content;
}
