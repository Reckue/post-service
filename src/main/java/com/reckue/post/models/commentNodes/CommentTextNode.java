package com.reckue.post.models.commentNodes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class CommentTextNode represents model of text fields in comment.
 *
 * @author Artur Magomedov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CommentTextNode implements CommentParent {

    private String content;
}
