package com.reckue.post.component;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConstructorParamsLoader {

    private final ApplicationContext applicationContext;

    public Constructor<?> findAllArgsConstructor(Constructor<?>[] constructors) {
        int max = -1;
        Constructor<?> allArgsConstructor = null;
        for (Constructor<?> constructor: constructors) {
            if (max < constructor.getParameterCount()) {
                allArgsConstructor = constructor;
                max = constructor.getParameterCount();
            }
        }
        if (max == -1) {
            throw new RuntimeException("Cannot find declared constructor");
        }
        return allArgsConstructor;
    }

    public Class<?>[] getBeanClassTypes(Parameter[] parameters) {
        Class<?>[] classes = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            classes[i] = parameters[i].getType();
        }
        return classes;
    }

    public Object[] loadRequiredBeans(Class<?>[] classes) {
        List<Object> objectList = new ArrayList<>();
        for (Class<?> clazz: classes) {
            objectList.add(applicationContext.getBean(clazz));
        }
        return objectList.toArray();
    }

    public Parameter[] extractConstructorParameters(Class<?> beanClass) {
        Constructor<?> constructor = findAllArgsConstructor(beanClass.getDeclaredConstructors());
        return constructor.getParameters();
    }
}
