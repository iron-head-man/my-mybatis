package com.xiaoxing.mybatis03.mapping;

import com.xiaoxing.mybatis03.session.Configuration;

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

    private Map<Integer,String> parameter;

    MappedStatement() {
        // constructor disabled
    }
}
