package com.xiaoxing.mybatis04.mapping;

import com.xiaoxing.mybatis04.session.Configuration;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/22 23:16
 */
public class MappedStatement {

    //配置
    private Configuration configuration;

    private String id;

    private SqlCommandType sqlCommandType;

    private String parameterType;

    private String resultType;

    private String sql;

    private Map<Integer, String> parameter;

    MappedStatement() {
        // constructor disabled
    }

    /**
     * 建造者
     */
    public static class Builder {

        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, String parameterType, String resultType, String sql, Map<Integer, String> parameter) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.parameterType = parameterType;
            mappedStatement.resultType = resultType;
            mappedStatement.sql = sql;
            mappedStatement.parameter = parameter;
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

    public String getParameterType() {
        return parameterType;
    }

    public MappedStatement setParameterType(String parameterType) {
        this.parameterType = parameterType;
        return this;
    }

    public String getResultType() {
        return resultType;
    }

    public MappedStatement setResultType(String resultType) {
        this.resultType = resultType;
        return this;
    }

    public String getSql() {
        return sql;
    }

    public MappedStatement setSql(String sql) {
        this.sql = sql;
        return this;
    }

    public Map<Integer, String> getParameter() {
        return parameter;
    }

    public MappedStatement setParameter(Map<Integer, String> parameter) {
        this.parameter = parameter;
        return this;
    }
}
