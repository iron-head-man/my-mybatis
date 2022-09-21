package com.xiaoxing.mybatis07.test;

import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/15 15:52
 */
public class ProxyTest {


    // @Test
    // public void test_11(){
    // IUserService userService = new UserServiceImpl();
    // MyProxy myProxy = new MyProxy(userService);
    // IUserService proxy =(IUserService) myProxy.getProxy();
    // proxy.add("1");
    // }
//    public static void main(String[] args) throws Throwable {
//
//    }

    @Test
    public void test_0001() throws Throwable {
        IUserService userService = new UserServiceImpl();
        MyProxy handler = new MyProxy(userService);

        // 根据目标生成代理对象
        IUserService proxy = (IUserService) handler.getProxy();
        proxy.addUser();
        proxy.add("1");
    }
}
