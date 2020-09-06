package com.reckue.post.services.realizations;

import com.reckue.post.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Class TokenServiceRealization represents realization of TokenService.
 *
 * @author Kamila Meshcheryakova
 * created 06.09.2020
 */
@Service
@RequiredArgsConstructor
public class TokenServiceRealization implements TokenService {

    private final DefaultTokenServices tokenServices;

    @Override
    public Map<String, Object> extractExtraInfo(String token) {
        OAuth2AccessToken oAuth2AccessToken = tokenServices.readAccessToken(token);
        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(oAuth2AccessToken);
        return customAccessToken.getAdditionalInformation();
    }
}
