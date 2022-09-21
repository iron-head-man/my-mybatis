package com.xiaoxing.mybatis07.mapping;

import java.util.Map;

/**
 * <p>
 * 绑定的sql，从sqlsource而来，将动态内容都处理完成得到的sql语句字符串
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/12 21:55
 */
public class BoundSql {

    private String sql;

    private Map<Integer, String> parameterMappings;

    private String parameterType;

    private String resultType;

    // 全部由构造器注入
    public BoundSql(String sql, Map<Integer, String> parameterMappings, String parameterType, String resultType) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterType = parameterType;
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public Map<Integer, String> getParameterMappings() {
        return parameterMappings;
    }

    public String getParameterType() {
        return parameterType;
    }

    public String getResultType() {
        return resultType;
    }
}
