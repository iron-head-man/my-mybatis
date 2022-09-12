package com.xiaoxing.mybatis04.mapping;

import com.xiaoxing.mybatis04.session.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/22 23:16
 */
public class MappedStatement {

    // 配置
    private Configuration configuration;

    private String id;

    private SqlCommandType sqlCommandType;

    private BoundSql boundSql;

    MappedStatement() {
        // constructor disabled
    }

    /**
     * 建造者
     */
    public static class Builder {

        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, BoundSql boundSql) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.boundSql = boundSql;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            return mappedStatement;
        }

    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public MappedStatement setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    public String getId() {
        return id;
    }

    public MappedStatement setId(String id) {
        this.id = id;
        return this;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public MappedStatement setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
        return this;
    }

    public BoundSql getBoundSql() {
        return boundSql;
    }

    public MappedStatement setBoundSql(BoundSql boundSql) {
        this.boundSql = boundSql;
        return this;
    }
}
