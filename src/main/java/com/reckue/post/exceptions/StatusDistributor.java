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
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class StatusDistributor {
    public static Map<Class<? extends ReckueException>, HttpStatus> httpStatuses = new HashMap<>();

    static {
        httpStatuses.put(PostNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(NodeNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(TagNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(CommentNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(RatingNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(PollNodeNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(PostAlreadyExistException.class, HttpStatus.CONFLICT);
        httpStatuses.put(NodeAlreadyExistException.class, HttpStatus.CONFLICT);
        httpStatuses.put(TagAlreadyExistException.class, HttpStatus.CONFLICT);
        httpStatuses.put(CommentAlreadyExistException.class, HttpStatus.CONFLICT);
        httpStatuses.put(RatingAlreadyExistException.class, HttpStatus.CONFLICT);
        httpStatuses.put(PollNodeAlreadyExistException.class, HttpStatus.CONFLICT);
    }
}
