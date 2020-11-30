package com.reckue.post.processors;

import com.reckue.post.processors.annotations.NotNullableParams;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NotNullableBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, List<Method>> annotatedMethodsMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (ServiceChecker.isService(beanClass)) {
            Method[] methods = beanClass.getDeclaredMethods();
            List<Method> annotatedMethodsList = extractAnnotatedMethods(methods);
            if (annotatedMethodsList.size() != 0) {
                annotatedMethodsMap.put(beanName, annotatedMethodsList);
            }
        }
        return bean;
    }

    private List<Method> extractAnnotatedMethods(Method[] methods) {
        List<Method> annotatedMethodsList = new ArrayList<>();
        for (Method method: methods) {
            if (method.isAnnotationPresent(NotNullableParams.class)) {
                annotatedMethodsList.add(method);
            }
        }
        return annotatedMethodsList;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        List<Method> annotatedMethodsList = annotatedMethodsMap.get(beanName);
        if (annotatedMethodsList != null) {
           return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
               annotatedMethodsList.forEach(annotatedMethod -> {
                   String annotatedMethodName = annotatedMethod.getName();
                   String currentMethodName = method.getName();
                   if (currentMethodName.equals(annotatedMethodName)) {
                       checkAnnotatedParam(annotatedMethod, args);
                   }
               });
               return method.invoke(bean, args);
            });
        }
        return bean;
    }

    private void checkAnnotatedParam(Method annotatedMethod, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) {
                throw new RuntimeException(extractArgName(annotatedMethod, i) + " is null");
            }
        }
    }

    private String extractArgName(Method annotatedMethod, int index) {
        String name = annotatedMethod.getParameters()[index].getName();
        String firstUpperCaseChar = String.valueOf(name.charAt(0)).toUpperCase();
        return firstUpperCaseChar + name.substring(1);
    }
}
