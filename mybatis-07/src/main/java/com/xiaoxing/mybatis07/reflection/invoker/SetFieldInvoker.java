package com.xiaoxing.mybatis07.reflection.invoker;

import java.lang.reflect.Field;

/**
 * <p>
 * 属性调用者
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/22 23:16
 */
public class SetFieldInvoker implements Invoker {
    private Field field;

    public SetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        field.set(target, args[0]);
        return null;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
