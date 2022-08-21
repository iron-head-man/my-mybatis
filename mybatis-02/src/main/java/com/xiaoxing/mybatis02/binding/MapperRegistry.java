package com.xiaoxing.mybatis02.binding;

import cn.hutool.core.lang.ClassScanner;
import com.xiaoxing.mybatis02.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * mapper注册
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/21 21:35
 */
public class MapperRegistry {

    /**
     * 将已添加的映射器代理加入到 HashMap
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("type" + type + "is not known to the MapperRegistry");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T> void addMapper(Class<T> type) {
        /*必须是接口才能注册*/
        if (type.isInterface()) {
            if (hasMapper(type)) {
                //重复添加则报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    private <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        mapperSet.forEach(this::addMapper);
    }


}
