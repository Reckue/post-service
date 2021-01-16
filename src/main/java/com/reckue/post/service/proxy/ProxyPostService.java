package com.reckue.post.service.proxy;

import com.reckue.post.model.Post;
import com.reckue.post.service.BaseService;
import com.reckue.post.service.SecurityService;
import org.springframework.stereotype.Service;

/**
 * Класс ProxyPostService
 *
 * @author Kamila Meshcheryakova
 * created 03.12.2020
 */
@Service
public class ProxyPostService extends ProxyService<Post> {
    public ProxyPostService(BaseService<Post> baseService, SecurityService securityService) {
        super(baseService, securityService);
    }
}
