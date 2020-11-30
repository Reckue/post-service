package com.reckue.post.processors;

import com.reckue.post.processors.annotations.NotNullableParams;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

@Component
public class NotNullableBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Map<Method, String[]>> annotatedMethodsMetaInfoMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (ServiceChecker.isService(beanClass)) {
            Method[] methods = beanClass.getDeclaredMethods();
            Map<Method, String[]> methodsMetaInfo = extractAnnotatedMethods(methods);
            if (methodsMetaInfo.size() != 0) {
                annotatedMethodsMetaInfoMap.put(beanName, methodsMetaInfo);
            }
        }
        return bean;
    }

    private Map<Method, String[]> extractAnnotatedMethods(Method[] methods) {
        Map<Method, String[]> methodsMetaInfo = new HashMap<>();
        for (Method method: methods) {
            if (method.isAnnotationPresent(NotNullableParams.class)) {
                String[] params = method.getAnnotation(NotNullableParams.class).value();
                methodsMetaInfo.put(method, params);
            }
        }
        return methodsMetaInfo;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Map<Method, String[]> methodsMetaInfo = annotatedMethodsMetaInfoMap.get(beanName);
        if (methodsMetaInfo != null) {
            return Proxy.newProxyInstance(
                    beanClass.getClassLoader(),
                    beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        methodsMetaInfo.forEach((annotatedMethod, paramNames)-> {
                            String annotatedMethodName = annotatedMethod.getName();
                            String currentMethodName = method.getName();
                            if (currentMethodName.equals(annotatedMethodName)) {
                                checkAnnotatedParam(annotatedMethod, args, paramNames);
                            }
                        });
                return method.invoke(bean, args);
            });
        }
        return bean;
    }

    private void checkAnnotatedParam(Method annotatedMethod, Object[] args, String[] paramNames) {
        List<String> paramNamesList = Arrays.asList(paramNames);
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) {
                String name = annotatedMethod.getParameters()[i].getName();
                if (paramNamesList.contains("") || paramNamesList.contains(name)) {
                    throw new RuntimeException(format(name) + " is null");
                }
            }
        }
    }

    private String format(String name) {
        String firstUpperCaseChar = String.valueOf(name.charAt(0)).toUpperCase();
        return firstUpperCaseChar + name.substring(1);
    }
}
