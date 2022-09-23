package com.xiaoxing.mybatis07.reflection.wrapper;

import com.xiaoxing.mybatis07.reflection.MetaObject;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/23 23:55
 */
public interface ObjectWrapperFactory {

    /**
     * 判断有没有包装器
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);
}
