package com.reckue.post.component;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ServiceChecker {

    public boolean isService(Class<?> beanClass) {
        if (beanClass.isAnnotationPresent(Service.class)) {
            return true;
        } else {
            return checkServiceAmongParents(beanClass.getInterfaces());
        }
    }

    public boolean checkServiceAmongParents(Class<?>[] parents) {
        boolean accumulator = false;
        for (Class<?> parent : parents) {
            accumulator = accumulator || isService(parent);
        }
        return accumulator;
    }
}
