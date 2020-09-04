package com.reckue.post.models.commentNodes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Class CommentListNode is responsible for displaying list of content in comment.
 *
 * @author Artur Magomedov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CommentListNode implements CommentParent {

    private List<String> content;
}
