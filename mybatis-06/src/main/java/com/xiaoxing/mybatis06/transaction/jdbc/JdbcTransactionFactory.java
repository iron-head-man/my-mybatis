package com.xiaoxing.mybatis06.transaction.jdbc;

import com.xiaoxing.mybatis06.session.TransactionIsolationLevel;
import com.xiaoxing.mybatis06.transaction.Transaction;
import com.xiaoxing.mybatis06.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * <p>
 * jdbc事务工厂
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/30 23:53
 */
public class JdbcTransactionFactory implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
