package com.reckue.post.services;

import java.util.Map;

/**
 * Interface TokenService represents the service for getting additional information from a token.
 *
 * @author Kamila Meshcheryakova
 * created 06.09.2020
 */
public interface TokenService {
    Map<String, Object> extractExtraInfo(String token);
}
