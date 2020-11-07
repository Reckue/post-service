package com.reckue.post.exception;

import lombok.Getter;

/**
 * Class ModelNotFoundException is responsible for throwing
 * exception when the received model is not found in the database.
 *
 * @author Daria Smirnova
 */
@Getter
public abstract class ModelNotFoundException extends ReckueException {
}
