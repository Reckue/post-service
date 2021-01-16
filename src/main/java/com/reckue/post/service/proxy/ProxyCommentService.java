package com.reckue.post.service.proxy;

import com.reckue.post.model.Comment;
import com.reckue.post.service.BaseService;
import com.reckue.post.service.SecurityService;
import org.springframework.stereotype.Service;

/**
 * Класс ProxyCommentService
 *
 * @author Kamila Meshcheryakova
 * created 03.12.2020
 */
@Service
public class ProxyCommentService extends ProxyService<Comment> {
    public ProxyCommentService(BaseService<Comment> baseService, SecurityService securityService) {
        super(baseService, securityService);
    }
}
