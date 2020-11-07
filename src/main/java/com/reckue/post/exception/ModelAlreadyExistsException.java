package com.reckue.post.exception;

import lombok.Getter;

/**
 * Class ModelAlreadyExistsException is responsible for throwing
 * exception when the created model already exists in database.
 *
 * @author Daria Smirnova
 */
@Getter
public abstract class ModelAlreadyExistsException extends ReckueException {
}
