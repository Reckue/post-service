package com.reckue.post.exception;

import com.reckue.libs.exception.ReckueAccessDeniedException;
import com.reckue.libs.exception.ReckueException;
import com.reckue.libs.exception.ReckueIllegalArgumentException;
import com.reckue.libs.exception.ReckueUnauthorizedException;
import com.reckue.post.exception.model.comment.CommentAlreadyExistsException;
import com.reckue.post.exception.model.comment.CommentNotFoundException;
import com.reckue.post.exception.model.node.NodeAlreadyExistsException;
import com.reckue.post.exception.model.node.NodeNotFoundException;
import com.reckue.post.exception.model.node.pollnode.PollNodeAlreadyExistsException;
import com.reckue.post.exception.model.node.pollnode.PollNodeNotFoundException;
import com.reckue.post.exception.model.post.PostAlreadyExistsException;
import com.reckue.post.exception.model.post.PostNotFoundException;
import com.reckue.post.exception.model.rating.RatingAlreadyExistsException;
import com.reckue.post.exception.model.rating.RatingNotFoundException;
import com.reckue.post.exception.model.tag.TagAlreadyExistsException;
import com.reckue.post.exception.model.tag.TagNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Class CodeErrorDistributor represents a map, where the key is the some Reckue subclass,
 * and the value is code error.
 *
 * @author Artur Magomedov
 */
public class CodeErrorDistributor {

    public static Map<Class<? extends ReckueException>, String> codeErrors = new HashMap<>();

    static {
        codeErrors.put(PostNotFoundException.class, "RPE-001");
        codeErrors.put(NodeNotFoundException.class, "RPE-002");
        codeErrors.put(TagNotFoundException.class, "RPE-003");
        codeErrors.put(CommentNotFoundException.class, "RPE-004");
        codeErrors.put(RatingNotFoundException.class, "RPE-005");
        codeErrors.put(PollNodeNotFoundException.class, "RPE-006");

        codeErrors.put(PostAlreadyExistsException.class, "RPE-1001");
        codeErrors.put(NodeAlreadyExistsException.class, "RPE-1002");
        codeErrors.put(TagAlreadyExistsException.class, "RPE-1003");
        codeErrors.put(CommentAlreadyExistsException.class, "RPE-1004");
        codeErrors.put(RatingAlreadyExistsException.class, "RPE-1005");
        codeErrors.put(PollNodeAlreadyExistsException.class, "RPE-1006");

        codeErrors.put(ReckueIllegalArgumentException.class, "RPE-1500");

        codeErrors.put(ReckueUnauthorizedException.class, "RPE-1600");

        codeErrors.put(ReckueAccessDeniedException.class, "RPE-1700");
    }
}
