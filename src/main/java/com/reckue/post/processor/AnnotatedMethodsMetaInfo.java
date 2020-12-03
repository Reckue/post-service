package com.reckue.post.processor;

import com.reckue.post.processor.annotation.NotNullArgs;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AnnotatedMethodsMetaInfo {

    private final Map<String, Map<Method, String[]>> annotatedMethodsMetaInfoMap = new HashMap<>();

    protected void supplement(String beanName, Class<?> beanClass) {
        Method[] methods = beanClass.getDeclaredMethods();
        Map<Method, String[]> methodsMetaInfo = extractAnnotatedMethods(methods);
        if (methodsMetaInfo.size() != 0) {
            annotatedMethodsMetaInfoMap.put(beanName, methodsMetaInfo);
        }
    }

    private Map<Method, String[]> extractAnnotatedMethods(Method[] methods) {
        Map<Method, String[]> methodsMetaInfo = new HashMap<>();
        for (Method method: methods) {
            if (method.isAnnotationPresent(NotNullArgs.class)) {
                String[] params = method.getAnnotation(NotNullArgs.class).value();
                methodsMetaInfo.put(method, params);
            }
        }
        return methodsMetaInfo;
    }

    protected Map<Method, String[]> get(String beanName) {
        return annotatedMethodsMetaInfoMap.get(beanName);
    }
}
