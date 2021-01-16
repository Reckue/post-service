package com.reckue.post.service.proxy;

import com.reckue.post.model.Node;
import com.reckue.post.service.BaseService;
import com.reckue.post.service.SecurityService;
import org.springframework.stereotype.Service;

/**
 * Класс ProxyNodeService
 *
 * @author Kamila Meshcheryakova
 * created 03.12.2020
 */
@Service
public class ProxyNodeService extends ProxyService<Node> {
    public ProxyNodeService(BaseService<Node> baseService, SecurityService securityService) {
        super(baseService, securityService);
    }
}
