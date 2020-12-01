package com.reckue.post.processors;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NotNullableProxy {

    protected static Object newProxyInstance(Map<Method, String[]> methodsMetaInfo, Object bean) {
        Class<?> beanClass = bean.getClass();
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
}
