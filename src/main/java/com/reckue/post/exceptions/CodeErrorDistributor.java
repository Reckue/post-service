package com.reckue.post.exceptions;

import com.reckue.post.exceptions.models.InvalidModelFieldSizeException;
import com.reckue.post.exceptions.models.comment.CommentAlreadyExistsException;
import com.reckue.post.exceptions.models.comment.CommentNotFoundException;
import com.reckue.post.exceptions.models.nodes.NodeAlreadyExistsException;
import com.reckue.post.exceptions.models.nodes.NodeNotFoundException;
import com.reckue.post.exceptions.models.nodes.pollnode.PollNodeAlreadyExistsException;
import com.reckue.post.exceptions.models.nodes.pollnode.PollNodeNotFoundException;
import com.reckue.post.exceptions.models.post.PostAlreadyExistsException;
import com.reckue.post.exceptions.models.post.PostNotFoundException;
import com.reckue.post.exceptions.models.rating.RatingAlreadyExistsException;
import com.reckue.post.exceptions.models.rating.RatingNotFoundException;
import com.reckue.post.exceptions.models.tag.TagAlreadyExistsException;
import com.reckue.post.exceptions.models.tag.TagNotFoundException;

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
        codeErrors.put(InvalidModelFieldSizeException.class, "RPE-1501");
    }
}
