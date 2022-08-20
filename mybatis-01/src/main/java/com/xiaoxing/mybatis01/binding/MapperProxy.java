package com.xiaoxing.mybatis01.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p>
 * MapperProxy 负责实现 InvocationHandler 接口的 invoke 方法，最终所有的实际调用都会调用到这个方法包装的逻辑。
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/20 13:55
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;

    private Map<String, String> sqlSession;

    private final Class<T> mapperInterface;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
        }
        return "你的被代理了！" + sqlSession.get(mapperInterface.getName() + "." + method.getName());
    }


}
