package com.xiaoxing.mybatis04.session.defaults;

import com.xiaoxing.mybatis04.session.Configuration;
import com.xiaoxing.mybatis04.session.SqlSession;
import com.xiaoxing.mybatis04.session.SqlSessionFactory;

/**
 * <p>
 * sqlSessionFactory默认实现
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/21 22:55
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {


    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
