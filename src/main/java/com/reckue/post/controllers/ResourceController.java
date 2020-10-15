package com.reckue.post.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Class ResourceController represents REST-controller to get info about user by token.
 *
 * @author Kamila Meshcheryakova
 * created 11.09.2020
 */

@RestController
@RequiredArgsConstructor
public class ResourceController {

    private final TokenStore tokenStore;

    @GetMapping("/user/id")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @ApiOperation(value = "Get user id",
            authorizations = {@Authorization(value = "Bearer token")})
    public String userId(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION).substring(7);
        return (String) tokenStore.readAccessToken(token)
                .getAdditionalInformation().get("userId");
    }

    @GetMapping("/user/info")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @ApiOperation(value = "All user info",
            authorizations = {@Authorization(value = "Bearer token")})
    public Map<String, Object> userInfo(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION).substring(7);
        return tokenStore.readAccessToken(token).getAdditionalInformation();
    }
}
