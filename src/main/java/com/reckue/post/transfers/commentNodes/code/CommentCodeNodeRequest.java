package com.reckue.post.transfers.commentNodes.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.reckue.post.models.types.CommentNodeType;
import com.reckue.post.models.types.LangType;
import com.reckue.post.transfers.commentNodes.CommentNodeParentRequest;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class CommentCodeNodeRequest represents an incoming DTO
 * for adding type of comment node such as a language of content.
 *
 * @author Artur Magomedov
 */
@Builder
@Data
public class CommentCodeNodeRequest implements CommentNodeParentRequest {

    @NotNull
    private LangType language;

    @NotNull
    private String content;

    @NotNull
    private CommentNodeType type;

    @JsonCreator
    public CommentCodeNodeRequest(@NotNull LangType language,
                                  @NotNull String content,
                                  @NotNull CommentNodeType type) {
        this.language = language;
        this.content = content;
        this.type = type;
    }
}
