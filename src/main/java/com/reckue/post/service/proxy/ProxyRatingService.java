package com.reckue.post.service.proxy;

import com.reckue.post.model.Rating;
import com.reckue.post.service.BaseService;
import com.reckue.post.service.SecurityService;
import org.springframework.stereotype.Service;

/**
 * Класс ProxyRatingService
 *
 * @author Kamila Meshcheryakova
 * created 03.12.2020
 */
@Service
public class ProxyRatingService extends ProxyService<Rating> {
    public ProxyRatingService(BaseService<Rating> baseService, SecurityService securityService) {
        super(baseService, securityService);
    }
}
