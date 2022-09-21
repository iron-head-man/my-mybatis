package com.xiaoxing.mybatis07.executor;

import com.xiaoxing.mybatis07.mapping.BoundSql;
import com.xiaoxing.mybatis07.mapping.MappedStatement;
import com.xiaoxing.mybatis07.session.Configuration;
import com.xiaoxing.mybatis07.session.ResultHandler;
import com.xiaoxing.mybatis07.transaction.Transaction;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 执行器抽象类
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/20 22:58
 */
public abstract class BaseExecutor implements Executor {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuration;
    protected Transaction transaction;
    protected Executor wrapper;

    private boolean closed;

    protected BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
        this.wrapper = this;
    }

    @Override
    public Transaction getTransaction() {
        if (closed) {
            throw new RuntimeException("Executor was closed.");
        }
        return transaction;
    }

    @Override
    public void rollback(boolean required) throws SQLException {
        if (!closed) {
            if (required) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void close(boolean forceRollback) {

        try {
            try {
                rollback(forceRollback);
            } finally {
                transaction.close();
            }
        } catch (SQLException e) {
            logger.warn("Unexpected exception on closing transaction.  Cause: " + e);
        } finally {
            transaction = null;
            closed = true;
        }
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        if (closed)
            throw new RuntimeException("Executor was closed.");
        return doQuery(ms, parameter, resultHandler, boundSql);
    }

    public abstract <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler,
                    BoundSql boundSql);

    @Override
    public void commit(boolean required) throws SQLException {
        if (closed)
            throw new RuntimeException("Cannot commit, transaction is already closed");
        if (required)
            transaction.commit();
    }
}
