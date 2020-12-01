package com.reckue.post.processor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ServiceChecker {

    protected static boolean isService(Class<?> beanClass) {
        if (beanClass.isAnnotationPresent(Service.class)) {
            return true;
        } else {
            return checkServiceAmongParents(beanClass.getInterfaces());
        }
    }

    protected static boolean checkServiceAmongParents(Class<?>[] parents) {
        boolean accumulator = false;
        for (Class<?> parent : parents) {
            accumulator = accumulator || isService(parent);
        }
        return accumulator;
    }
}
