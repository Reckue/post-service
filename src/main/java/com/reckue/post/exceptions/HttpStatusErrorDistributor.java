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
        httpStatuses.put(InvalidModelFieldSizeException.class, HttpStatus.BAD_REQUEST);
    }
}
