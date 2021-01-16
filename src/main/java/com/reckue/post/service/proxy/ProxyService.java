package com.reckue.post.service.proxy;

import com.reckue.post.exception.ReckueAccessDeniedException;
import com.reckue.post.model.BaseModel;
import com.reckue.post.service.BaseService;
import com.reckue.post.service.SecurityService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Класс ProxyService
 *
 * @author Kamila Meshcheryakova
 * created 01.12.2020
 */
@RequiredArgsConstructor
public abstract class ProxyService<T extends BaseModel> {

    private final BaseService<T> baseService;
    private final SecurityService securityService;

    /**
     * This method is used to get the userId from a token, add it to a given model
     * and invoke the method of proxied class to create an object.
     *
     * @param object  the model to update
     * @param request http header
     */
    public T create(T object, HttpServletRequest request) {
        String userId = (String) securityService.checkAndGetInfo(request).get("userId");
        object.setUserId(userId);
        return baseService.create(object);
    }

    /**
     * This method is used to get the userId from a token, add it to a given model
     * and invoke the method of proxied class to update an object.
     *
     * @param object  the model to update
     * @param request http header
     */
    public T update(T object, HttpServletRequest request) {
        String userId = (String) securityService.checkAndGetInfo(request).get("userId");
        object.setUserId(userId);
        return baseService.update(object);
    }

    /**
     * This method is used to check user authorities and invoke the method of proxied class to delete an object by id.
     * Throws {@link ReckueAccessDeniedException} in case if the user isn't an post owner or
     * hasn't admin authorities.
     *
     * @param id      object
     * @param request http header
     */
    public void deleteById(String id, HttpServletRequest request) {
        Map<String, Object> tokenInfo = securityService.checkAndGetInfo(request);
        T object = baseService.findById(id);
        if (tokenInfo.get("userId").equals(object.getUserId()) || tokenInfo.get("authorities").equals("ROLE_ADMIN")) {
            baseService.deleteById(id);
        } else {
            throw new ReckueAccessDeniedException("The operation is forbidden");
        }
    }
}
