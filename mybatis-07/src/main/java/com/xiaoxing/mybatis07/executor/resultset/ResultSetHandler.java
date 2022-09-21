package com.xiaoxing.mybatis07.executor.resultset;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * <p>
 * 结果集处理器
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/20 23:53
 */
public interface ResultSetHandler {

    <E> List<E> handleResultSets(Statement stmt) throws SQLException;
}
