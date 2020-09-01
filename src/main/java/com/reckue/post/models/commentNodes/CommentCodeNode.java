package com.reckue.post.models.commentNodes;

import com.reckue.post.models.types.LangType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class CommentCodeNode represents model of code fields in comment.
 *
 * @author Artur Magomedov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CommentCodeNode implements CommentParent {

    private LangType language;
    private String content;
}
