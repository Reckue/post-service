package com.reckue.post.services;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Класс SecurityService
 *
 * @author Kamila Meshcheryakova
 */
public interface SecurityService {

    /**
     * The default realization of the algorithm:
     * check token and get additional information from a token.
     *
     * @param request information for HTTP servlets
     * @return additional information from token
     */
    default Map<String, Object> checkAndGetInfo(HttpServletRequest request) {
        return getTokenInfo(checkToken(request));
    }

    /**
     * The method allows to get all additional information from a token.
     *
     * @param token user token
     * @return additional information from a token
     */
    Map<String, Object> getTokenInfo(String token);

    /**
     * The method allows to check for token availability.
     *
     * @param request information for HTTP servlets
     * @return access token
     */
    String checkToken(HttpServletRequest request);
}
