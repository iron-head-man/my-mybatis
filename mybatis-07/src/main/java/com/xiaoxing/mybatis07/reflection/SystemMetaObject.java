package com.xiaoxing.mybatis07.reflection;

import com.xiaoxing.mybatis07.reflection.factory.DefaultObjectFactory;
import com.xiaoxing.mybatis07.reflection.factory.ObjectFactory;
import com.xiaoxing.mybatis07.reflection.wrapper.DefaultObjectWrapperFactory;
import com.xiaoxing.mybatis07.reflection.wrapper.ObjectWrapperFactory;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/23 23:53
 */
public class SystemMetaObject {
    public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    public static final MetaObject NULL_META_OBJECT = MetaObject.forObject(NullObject.class, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);

    private SystemMetaObject() {
        // Prevent Instantiation of Static Class
    }

    /**
     * 空对象
     */
    private static class NullObject {
    }

    public static MetaObject forObject(Object object) {
        return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    }
}
