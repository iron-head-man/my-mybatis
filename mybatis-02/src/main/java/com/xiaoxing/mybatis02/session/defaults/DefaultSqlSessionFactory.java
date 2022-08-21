package com.xiaoxing.mybatis02.session.defaults;

import com.xiaoxing.mybatis02.binding.MapperRegistry;
import com.xiaoxing.mybatis02.session.SqlSession;
import com.xiaoxing.mybatis02.session.SqlSessionFactory;

/**
 * <p>
 * sqlSessionFactory默认实现
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/21 22:55
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
