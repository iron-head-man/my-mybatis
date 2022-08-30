package com.xiaoxing.mybatis04.session.defaults;

import com.xiaoxing.mybatis04.mapping.MappedStatement;
import com.xiaoxing.mybatis04.session.Configuration;
import com.xiaoxing.mybatis04.session.SqlSession;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/21 22:52
 */
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射注册机
     */
    private Configuration configuration;


    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return null;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("你被代理了！" + "\n方法：" + statement + "\n入参：" + parameter + "\n待执行SQL：" + mappedStatement.getSql());
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
