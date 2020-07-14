package com.reckue.post.exceptions;

import com.reckue.post.exceptions.models.comment.CommentAlreadyExistException;
import com.reckue.post.exceptions.models.comment.CommentNotFoundException;
import com.reckue.post.exceptions.models.nodes.NodeAlreadyExistException;
import com.reckue.post.exceptions.models.nodes.NodeNotFoundException;
import com.reckue.post.exceptions.models.nodes.pollnode.PollNodeAlreadyExistException;
import com.reckue.post.exceptions.models.nodes.pollnode.PollNodeNotFoundException;
import com.reckue.post.exceptions.models.post.PostAlreadyExistException;
import com.reckue.post.exceptions.models.post.PostNotFoundException;
import com.reckue.post.exceptions.models.rating.RatingAlreadyExistException;
import com.reckue.post.exceptions.models.rating.RatingNotFoundException;
import com.reckue.post.exceptions.models.tag.TagAlreadyExistException;
import com.reckue.post.exceptions.models.tag.TagNotFoundException;
import com.reckue.post.models.Rating;

import java.util.HashMap;
import java.util.Map;

/**
 * Class CodeErrorDistributor represents is a map, where the key is the some Reckue subclass,
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

        codeErrors.put(PostAlreadyExistException.class, "RPE-1001");
        codeErrors.put(NodeAlreadyExistException.class, "RPE-1002");
        codeErrors.put(TagAlreadyExistException.class, "RPE-1003");
        codeErrors.put(CommentAlreadyExistException.class, "RPE-1004");
        codeErrors.put(RatingAlreadyExistException.class, "RPE-1005");
        codeErrors.put(PollNodeAlreadyExistException.class, "RPE-1006");

        codeErrors.put(ReckueIllegalArgumentException.class, "RPE-1500");
    }
}
