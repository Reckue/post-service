package com.reckue.post.services;

import com.reckue.post.exceptions.ReckueUnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Класс SecurityServiceRealization
 *
 * @author Kamila Meshcheryakova
 */
@Service
@RequiredArgsConstructor
public class SecurityServiceRealization implements SecurityService {

    private final TokenStore tokenStore;

    /**
     * The method allows to get all additional information from a token.
     * Throws {@link ReckueUnauthorizedException} in case of invalid token.
     *
     * @param token user token
     * @return additional information from a token
     */
    @Override
    public Map<String, Object> getTokenInfo(String token) {
        try {
            return tokenStore.readAccessToken(token).getAdditionalInformation();
        } catch (Exception e) {
            throw new ReckueUnauthorizedException("Invalid token");
        }
    }

    /**
     * The method allows to check for token availability.
     * Throws {@link ReckueUnauthorizedException} in case if the token is missing or
     * is too short.
     *
     * @param request information for HTTP servlets
     * @return access token
     */
    @Override
    public String checkToken(HttpServletRequest request) {
        String token;
        try {
            token = request.getHeader(AUTHORIZATION).substring(7);
            return token;
        } catch (NullPointerException e) {
            throw new ReckueUnauthorizedException("Token missing");
        } catch (StringIndexOutOfBoundsException e) {
            throw new ReckueUnauthorizedException("Token is too short");
        }
    }
}
