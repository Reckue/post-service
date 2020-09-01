package com.reckue.post.models.commentNodes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class CommentVideoNode is responsible for displaying video in comment.
 *
 * @author Artur Magomedov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CommentVideoNode implements CommentParent {

    private String videoUrl;
}
