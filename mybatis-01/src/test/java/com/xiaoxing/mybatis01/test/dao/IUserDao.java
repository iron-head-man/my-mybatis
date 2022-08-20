package com.xiaoxing.mybatis01.test.dao;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/20 21:22
 */
public interface IUserDao {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);

}
