package com.xiaoxing.mybatis07.reflection.invoker;

/**
 * <p>
 * 调用者
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/22 23:13
 */
public interface Invoker {


    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();

}
