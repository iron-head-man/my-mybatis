package com.xiaoxing.mybatis05.session;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/21 21:36
 */
public interface SqlSessionFactory {

    /**
     * 打开一个session
     *
     * @return
     */
    com.xiaoxing.mybatis05.session.SqlSession openSession();
}
