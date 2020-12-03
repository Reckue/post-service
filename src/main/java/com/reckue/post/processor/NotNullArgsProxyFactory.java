package com.reckue.post.processor;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class NotNullArgsProxyFactory {

    private final ApplicationContext applicationContext;

    public NotNullArgsProxyFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected Object newProxyInstance(Map<Method, String[]> methodsMetaInfo, Object bean) {
        Class<?> beanClass = bean.getClass();
        Parameter[] parameters = extractConstructorParameters(beanClass);
        Class<?>[] requiredClasses = getBeanClassTypes(parameters);
        Object[] requiredBeans = loadRequiredBeans(requiredClasses);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            methodsMetaInfo.forEach((annotatedMethod, paramNames)-> {
                String annotatedMethodName = annotatedMethod.getName();
                String currentMethodName = method.getName();
                if (currentMethodName.equals(annotatedMethodName)) {
                    checkAnnotatedParam(annotatedMethod, args, paramNames);
                }
            });
            return proxy.invokeSuper(obj, args);
        });
        return enhancer.create(requiredClasses, requiredBeans);
    }

    private static void checkAnnotatedParam(Method annotatedMethod, Object[] args, String[] paramNames) {
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

    private static String format(String name) {
        String firstUpperCaseChar = String.valueOf(name.charAt(0)).toUpperCase();
        return firstUpperCaseChar + name.substring(1);
    }

    private Class<?>[] getBeanClassTypes(Parameter[] parameters) {
        Class<?>[] classes = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            classes[i] = parameters[i].getType();
        }
        return classes;
    }

    private Object[] loadRequiredBeans(Class<?>[] classes) {
        List<Object> objectList = new ArrayList<>();
        for (Class<?> clazz: classes) {
            objectList.add(applicationContext.getBean(clazz));
        }
        return objectList.toArray();
    }

    private Parameter[] extractConstructorParameters(Class<?> beanClass) {
        Constructor<?> constructor = findAllArgsConstructor(beanClass.getDeclaredConstructors());
        return constructor.getParameters();
    }

    private Constructor<?> findAllArgsConstructor(Constructor<?>[] constructors) {
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
}
