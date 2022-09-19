package com.xiaoxing.mybatis06.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>
 * 事务
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/30 23:34
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;
}
