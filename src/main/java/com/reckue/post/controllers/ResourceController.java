package com.reckue.post.controllers;

import com.reckue.post.services.TokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Класс ResourceController
 *
 * @author Kamila Meshcheryakova
 * created 11.08.2020
 */

@RestController
@RequiredArgsConstructor
public class ResourceController {

    private final TokenService tokenService;

    @GetMapping("/user/id")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @ApiOperation(value = "User id",
            authorizations = {@Authorization(value = "Bearer token")})
    public String userId(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION).substring(7);
        return (String) tokenService.extractExtraInfo(token).get("userId");
    }

    @GetMapping("/user/info")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @ApiOperation(value = "Info about users",
            authorizations = {@Authorization(value = "Bearer token")})
    public Map<String, Object> userInfo(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION).substring(7);
        return tokenService.extractExtraInfo(token);
    }
}
