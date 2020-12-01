package com.reckue.post.processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

@Component
public class NotNullableBeanPostProcessor implements BeanPostProcessor {

    private final AnnotatedMethodsMetaInfo annotatedMethodsMetaInfo = new AnnotatedMethodsMetaInfo();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (ServiceChecker.isService(beanClass)) {
            annotatedMethodsMetaInfo.supplement(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Map<Method, String[]> methodsMetaInfo = annotatedMethodsMetaInfo.get(beanName);
        if (methodsMetaInfo != null) {
            return NotNullableProxy.newProxyInstance(methodsMetaInfo, bean);
        }
        return bean;
    }
}
