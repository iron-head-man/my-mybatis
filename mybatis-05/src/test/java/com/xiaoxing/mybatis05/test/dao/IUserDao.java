package com.xiaoxing.mybatis05.test.dao;

import com.xiaoxing.mybatis05.test.po.User;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/20 21:22
 */
public interface IUserDao {

    User queryUserInfoById(Long uId);

}
