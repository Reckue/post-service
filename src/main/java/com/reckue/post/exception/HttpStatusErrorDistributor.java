package com.reckue.post.exception;

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
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Class HttpStatusErrorDistributor represents a map, where the key is the Reckue subclass,
 * and the value is HttpStatus.
 *
 * @author Artur Magomedov
 */
public class HttpStatusErrorDistributor {
    public static Map<Class<? extends ReckueException>, HttpStatus> httpStatuses = new HashMap<>();

    static {
        httpStatuses.put(PostNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(NodeNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(TagNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(CommentNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(RatingNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(PollNodeNotFoundException.class, HttpStatus.NOT_FOUND);
        httpStatuses.put(PostAlreadyExistsException.class, HttpStatus.CONFLICT);
        httpStatuses.put(NodeAlreadyExistsException.class, HttpStatus.CONFLICT);
        httpStatuses.put(TagAlreadyExistsException.class, HttpStatus.CONFLICT);
        httpStatuses.put(CommentAlreadyExistsException.class, HttpStatus.CONFLICT);
        httpStatuses.put(RatingAlreadyExistsException.class, HttpStatus.CONFLICT);
        httpStatuses.put(PollNodeAlreadyExistsException.class, HttpStatus.CONFLICT);

        httpStatuses.put(ReckueIllegalArgumentException.class, HttpStatus.BAD_REQUEST);

        httpStatuses.put(ReckueUnauthorizedException.class, HttpStatus.UNAUTHORIZED);

        httpStatuses.put(ReckueAccessDeniedException.class, HttpStatus.FORBIDDEN);

    }
}
