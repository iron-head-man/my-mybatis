package com.xiaoxing.mybatis03.session;

import com.xiaoxing.mybatis03.binding.MapperRegistry;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/22 23:46
 */
public class Configuration {

    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);
}
