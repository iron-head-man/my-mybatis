package com.xiaoxing.mybatis07.session.defaults;

import com.xiaoxing.mybatis07.executor.Executor;
import com.xiaoxing.mybatis07.mapping.Environment;
import com.xiaoxing.mybatis07.session.Configuration;
import com.xiaoxing.mybatis07.session.SqlSession;
import com.xiaoxing.mybatis07.session.SqlSessionFactory;
import com.xiaoxing.mybatis07.session.TransactionIsolationLevel;
import com.xiaoxing.mybatis07.transaction.Transaction;
import com.xiaoxing.mybatis07.transaction.TransactionFactory;

import java.sql.SQLException;

/**
 * <p>
 * sqlSessionFactory默认实现
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/21 22:55
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {


    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public SqlSession openSession() {

        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(environment.getDataSource(),
                            TransactionIsolationLevel.READ_COMMITTED, false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }
}
