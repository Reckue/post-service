package com.reckue.post.processor.notnull;

import com.reckue.post.component.ConstructorParamsLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NotNullArgsProxyFactory {

    private final ConstructorParamsLoader constructorParamsLoader;

    protected Object newProxyInstance(Map<Method, String[]> methodsMetaInfo, Object bean) {
        Class<?> beanClass = bean.getClass();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            methodsMetaInfo.forEach((annotatedMethod, paramNames) -> {
                String annotatedMethodName = annotatedMethod.getName();
                String currentMethodName = method.getName();
                if (currentMethodName.equals(annotatedMethodName)) {
                    checkAnnotatedParam(annotatedMethod, args, paramNames);
                }
            });
            return proxy.invokeSuper(obj, args);
        });

        Parameter[] parameters = constructorParamsLoader.extractConstructorParameters(beanClass);
        Class<?>[] requiredClasses = constructorParamsLoader.getBeanClassTypes(parameters);
        Object[] requiredBeans = constructorParamsLoader.loadRequiredBeans(requiredClasses);
        return enhancer.create(requiredClasses, requiredBeans);
    }

    private void checkAnnotatedParam(Method annotatedMethod, Object[] args, String[] annotationParamNames) {
        List<String> paramNamesList = Arrays.asList(annotationParamNames);
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
