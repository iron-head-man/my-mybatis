package com.xiaoxing.mybatis06.test;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/15 18:27
 */
public class UserServiceImpl implements IUserService{
    @Override
    public void add(String s) {
        System.out.println("------------原方法-----------");
    }

    @Override
    public void addUser() {
        System.out.println("------------原方法-----------");
    }

    @Override
    public void editUser() {
        System.out.println("------------原方法-----------");
    }
}
