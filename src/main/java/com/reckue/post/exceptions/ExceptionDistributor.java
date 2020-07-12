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

public class ExceptionDistributor {

    public static Map<Class<? extends ReckueException>, String> codeErrors = new HashMap<>();

    static {
        codeErrors.put(PostNotFoundException.class, "RE-001");
        codeErrors.put(NodeNotFoundException.class, "RE-002");
        codeErrors.put(TagNotFoundException.class, "RE-003");
        codeErrors.put(CommentNotFoundException.class, "RE-004");
        codeErrors.put(RatingNotFoundException.class, "RE-005");
        codeErrors.put(PollNodeNotFoundException.class, "RE-006");
        codeErrors.put(PostAlreadyExistException.class, "RE-051");
        codeErrors.put(NodeAlreadyExistException.class, "RE-052");
        codeErrors.put(TagAlreadyExistException.class, "RE-053");
        codeErrors.put(CommentAlreadyExistException.class, "RE-054");
        codeErrors.put(RatingAlreadyExistException.class, "RE-055");
        codeErrors.put(PollNodeAlreadyExistException.class, "RE-056");
    }
}
