package com.xiaoxing.mybatis07.executor.statement;

import com.xiaoxing.mybatis07.executor.Executor;
import com.xiaoxing.mybatis07.executor.resultset.ResultSetHandler;
import com.xiaoxing.mybatis07.mapping.BoundSql;
import com.xiaoxing.mybatis07.mapping.MappedStatement;
import com.xiaoxing.mybatis07.session.Configuration;
import com.xiaoxing.mybatis07.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * 语句处理器基类
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/21 21:45
 */
public abstract class BaseStatementHandler implements StatementHandler {

    protected final Configuration configuration;
    protected final Executor executor;
    protected final MappedStatement mappedStatement;

    protected final Object parameterObject;
    protected final ResultSetHandler resultSetHandler;

    protected BoundSql boundSql;



    public abstract Statement instantiateStatement(Connection connection) throws SQLException;

    public BaseStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject,
                                ResultHandler resultHandler, BoundSql boundSql) {
        this.configuration = mappedStatement.getConfiguration();
        this.executor = executor;
        this.mappedStatement = mappedStatement;
        this.boundSql = boundSql;

        this.parameterObject = parameterObject;
        this.resultSetHandler = configuration.newResultSetHandler(executor, mappedStatement, boundSql);
    }

    @Override
    public Statement prepare(Connection connection) throws SQLException {
        Statement statement = null;
        try {
            // 实例化 Statement
            statement = instantiateStatement(connection);
            // 参数设置，可以被抽取，提供配置
            statement.setQueryTimeout(350);
            statement.setFetchSize(10000);
            return statement;
        } catch (Exception e) {
            throw new RuntimeException("Error preparing statement.  Cause:" + e, e);
        }
    }


}
