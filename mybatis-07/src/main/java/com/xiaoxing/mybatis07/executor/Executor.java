package com.xiaoxing.mybatis07.executor;

import java.sql.SQLException;
import java.util.List;

import com.xiaoxing.mybatis07.mapping.BoundSql;
import com.xiaoxing.mybatis07.mapping.MappedStatement;
import com.xiaoxing.mybatis07.session.ResultHandler;
import com.xiaoxing.mybatis07.transaction.Transaction;

/**
 * <p>
 * 执行器接口：定义出执行器的标准方法-->查询，事务，提交，回滚，关闭
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/20 22:57
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);
}
