package com.xiaoxing.mybatis01.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * <p>
 * MapperProxyFactory 是对 MapperProxy 的包装，对外提供实例化对象的操作。当我们后面开始给每个操作数据库的接口映射器注册代理的时候，就需要使用到这个工厂类了。
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/20 13:55
 */
public class MapperProxyFactory<T> {
    private final Class<T> mapperInterface;


    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);

        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}

