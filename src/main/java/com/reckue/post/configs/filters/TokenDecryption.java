package com.reckue.post.configs.filters;

import com.reckue.post.exceptions.AuthenticationException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Class TokenDecryption decrypts user information from a token.
 *
 * @author Kamila Meshcheryakova
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenDecryption {

    @Value("${security.token.secret:secret-key}")
    private String secretKey;

    private final UserDetailsService userDetailsService;

    /**
     * This method is used to init a secretKey using the encoder.
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * This method is used to authenticate user by token.
     *
     * @param token the user token
     * @return a simple presentation of a username and password
     */
    public Authentication authenticateToken(String token) {
        UserDetails userDetails;
        String username = getUsernameByToken(token);
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } catch (UsernameNotFoundException e) {
            throw new AuthenticationException("The username " + username + " not found", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * This method is used to get user name by token.
     * Throws {@link AuthenticationException} in case:
     * if a token has expired;
     * if an invalid token is entered;
     * if a token is incorrect - a JWT was not correctly constructed and should be rejected;
     * if a signature or verifying an existing signature of a JWT failed;
     * if a method has been passed an illegal or inappropriate argument.
     *
     * @param token the user token
     * @return a user name
     */
    public String getUsernameByToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("Expired token", HttpStatus.UNAUTHORIZED);
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationException("Invalid token", HttpStatus.BAD_REQUEST);
        } catch (MalformedJwtException e) {
            throw new AuthenticationException("Wrong token", HttpStatus.BAD_REQUEST);
        } catch (SignatureException e) {
            throw new AuthenticationException("Unverified token", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("Illegal argument", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method is used to get token without token type.
     *
     * @param req request information for HTTP servlets
     * @return a token without token type
     */
    public String extractToken(HttpServletRequest req) {
        String token = req.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    /**
     * This method is used to validate token by secret key.
     * Throws {@link AuthenticationException} in case:
     * if a token has expired;
     * if an invalid token is entered;
     * if a token is incorrect - a JWT was not correctly constructed and should be rejected;
     * if a signature or verifying an existing signature of a JWT failed;
     * if a method has been passed an illegal or inappropriate argument.
     *
     * @param token the user token
     * @return true or false
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("Expired token", HttpStatus.UNAUTHORIZED);
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationException("Invalid token", HttpStatus.BAD_REQUEST);
        } catch (MalformedJwtException e) {
            throw new AuthenticationException("Wrong token", HttpStatus.BAD_REQUEST);
        } catch (SignatureException e) {
            throw new AuthenticationException("Unverified token", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("Illegal argument", HttpStatus.BAD_REQUEST);
        }
    }
}
