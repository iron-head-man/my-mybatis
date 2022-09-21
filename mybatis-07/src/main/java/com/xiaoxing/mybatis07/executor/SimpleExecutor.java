package com.xiaoxing.mybatis07.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.xiaoxing.mybatis07.executor.statement.StatementHandler;
import com.xiaoxing.mybatis07.mapping.BoundSql;
import com.xiaoxing.mybatis07.mapping.MappedStatement;
import com.xiaoxing.mybatis07.session.Configuration;
import com.xiaoxing.mybatis07.session.ResultHandler;
import com.xiaoxing.mybatis07.transaction.Transaction;

/**
 * <p>
 * 简单执行器的实现
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/21 21:41
 */
public class SimpleExecutor extends BaseExecutor{


    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    public <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, resultHandler, boundSql);
            Connection connection = transaction.getConnection();
            Statement stmt = handler.prepare(connection);
            handler.parameterize(stmt);
            return handler.query(stmt, resultHandler);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
