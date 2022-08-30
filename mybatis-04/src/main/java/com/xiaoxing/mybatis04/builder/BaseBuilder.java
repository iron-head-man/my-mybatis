package com.xiaoxing.mybatis04.builder;

import com.xiaoxing.mybatis04.session.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/22 23:14
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }
    public Configuration getConfiguration() {
        return configuration;
    }
}
