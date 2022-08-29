package com.xiaoxing.mybatis03.session;

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
    SqlSession openSession();
}
