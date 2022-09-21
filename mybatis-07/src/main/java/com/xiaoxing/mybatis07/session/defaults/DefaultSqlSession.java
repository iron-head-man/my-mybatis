package com.xiaoxing.mybatis07.session.defaults;

import java.util.List;

import com.xiaoxing.mybatis07.executor.Executor;
import com.xiaoxing.mybatis07.mapping.MappedStatement;
import com.xiaoxing.mybatis07.session.Configuration;
import com.xiaoxing.mybatis07.session.SqlSession;

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

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        return null;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {

        MappedStatement mappedStatement = configuration.getMappedStatement(statement);

        List<T> list = executor.query(mappedStatement, parameter, Executor.NO_RESULT_HANDLER,
                        mappedStatement.getBoundSql());
        return list.get(0);
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
