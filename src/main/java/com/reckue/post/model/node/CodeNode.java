package com.reckue.post.model.node;

import com.reckue.post.model.type.LangType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class CodeNode represents model of code fields.
 *
 * @author Iveri Narozashvili
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CodeNode implements Parent {

    private LangType language;
    private String content;
}
