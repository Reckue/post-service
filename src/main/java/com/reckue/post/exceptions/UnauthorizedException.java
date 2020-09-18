package com.reckue.post.exceptions;

import lombok.Getter;

/**
 * Class UnauthorizedException is responsible for throwing
 * exception when user is unauthorized.
 *
 * @author Daria Smirnova
 */
@Getter
public abstract class UnauthorizedException extends ReckueException {
}
