package com.reckue.post.processor.notnull;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

@Component
@RequiredArgsConstructor
public class NotNullArgsBeanPostProcessor implements BeanPostProcessor {

    private final NotNullArgsProxyFactory notNullArgsProxyFactory;
    private final AnnotatedMethodsMetaInfo annotatedMethodsMetaInfo;

    @Override
    public Object postProcessBeforeInitialization(Object bean, @NotNull String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        annotatedMethodsMetaInfo.supplement(beanName, beanClass);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
        Map<Method, String[]> methodsMetaInfo = annotatedMethodsMetaInfo.get(beanName);
        if (methodsMetaInfo != null) {
            return notNullArgsProxyFactory.newProxyInstance(methodsMetaInfo, bean);
        }
        return bean;
    }
}
