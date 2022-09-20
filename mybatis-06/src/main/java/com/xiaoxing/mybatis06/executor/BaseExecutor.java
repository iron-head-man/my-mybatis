package com.xiaoxing.mybatis06.executor;

import com.xiaoxing.mybatis06.session.Configuration;
import com.xiaoxing.mybatis06.transaction.Transaction;

/**
 * <p>
 * 执行器抽象类
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/20 22:58
 */
public abstract class BaseExecutor implements Executor {

    protected Configuration configuration;
    protected Transaction transaction;
    protected Executor wrapper;

    private boolean closed;

    protected BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
        this.wrapper = this;
    }
}
