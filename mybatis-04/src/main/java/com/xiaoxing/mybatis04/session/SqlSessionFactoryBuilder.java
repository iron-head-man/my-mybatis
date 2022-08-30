package com.xiaoxing.mybatis04.session;

import com.xiaoxing.mybatis04.builder.xml.XMLConfigBuilder;
import com.xiaoxing.mybatis04.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/22 23:43
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
