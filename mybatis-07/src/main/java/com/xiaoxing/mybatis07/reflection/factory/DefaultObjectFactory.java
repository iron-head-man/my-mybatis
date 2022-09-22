package com.xiaoxing.mybatis07.reflection.factory;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * <p>
 * 默认对象工厂
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/22 22:21
 */
public class DefaultObjectFactory implements ObjectFactory {
    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public <T> T create(Class<T> type) {
        return create(type, null, null);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {

        // 1. 解析接口
        Class<?> classToCreate = resolveInterface(type);
        // 2. 类实例化
        return (T) instantiateClass(classToCreate, constructorArgTypes, constructorArgs);
    }

    protected Object instantiateClass(Class<?> classToCreate, List<Class<?>> constructorArgTypes,
                    List<Object> constructorArgs) {
        try {
            Constructor<?> constructor;
            // 无参数，直接构造器New一个
            if (constructorArgTypes == null || constructorArgs == null) {
                constructor = classToCreate.getDeclaredConstructor();
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                return constructor.newInstance();
            }
            constructor = classToCreate
                            .getDeclaredConstructor(constructorArgTypes.toArray(new Class[constructorArgTypes.size()]));
            constructor.newInstance(constructorArgs);
        } catch (Exception e) {
            // 如果出错，包装一下，重新抛出自己的异常
            StringBuilder argTypes = new StringBuilder();
            if (constructorArgTypes != null) {
                for (Class<?> argType : constructorArgTypes) {
                    argTypes.append(argType.getSimpleName());
                    argTypes.append(",");
                }
            }
            StringBuilder argValues = new StringBuilder();
            if (constructorArgs != null) {
                for (Object argValue : constructorArgs) {
                    argValues.append(argValue);
                    argValues.append(",");
                }
            }
            throw new RuntimeException("Error instantiating " + classToCreate + " with invalid types (" + argTypes
                            + ") or values (" + argValues + "). Cause: " + e, e);

        }
        return null;
    }

    /**
     * 解析接口，将 interface 转换为实际的 class 类
     * 
     */
    protected <T> Class<?> resolveInterface(Class<T> type) {
        Class<?> classToCreate;

        if (type == List.class || type == Collection.class || type == Iterable.class) {
            // List|Collection|Iterable-->ArrayList
            classToCreate = ArrayList.class;
        } else if (type == Map.class) {
            // Map->HashMap
            classToCreate = HashMap.class;
        } else if (type == SortedSet.class) {
            // SortedSet->TreeSet
            classToCreate = TreeSet.class;
        } else if (type == Set.class) {
            // Set->HashSet
            classToCreate = HashSet.class;
        } else {
            // 除此以外，就用原来的类型
            classToCreate = type;
        }
        return classToCreate;
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }
}
