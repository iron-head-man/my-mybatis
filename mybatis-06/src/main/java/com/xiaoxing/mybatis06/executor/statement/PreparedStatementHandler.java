package com.xiaoxing.mybatis06.executor.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.xiaoxing.mybatis06.executor.Executor;
import com.xiaoxing.mybatis06.mapping.BoundSql;
import com.xiaoxing.mybatis06.mapping.MappedStatement;
import com.xiaoxing.mybatis06.session.ResultHandler;

/**
 * <p>
 * 预处理语句处理器
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/21 22:12
 */
public class PreparedStatementHandler extends BaseStatementHandler {

    public PreparedStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject,
                    ResultHandler resultHandler, BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject, resultHandler, boundSql);
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.setLong(1, Long.parseLong(((Object[]) parameterObject)[0].toString()));
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.execute();
        return resultSetHandler.<E>handleResultSets(ps);
    }

    @Override
    public Statement instantiateStatement(Connection connection) throws SQLException {
        String sql = boundSql.getSql();
        return connection.prepareStatement(sql);
    }
}
