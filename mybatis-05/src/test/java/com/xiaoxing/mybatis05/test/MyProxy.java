package com.xiaoxing.mybatis05.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/15 10:16
 */
public class MyProxy implements InvocationHandler {

    // /**
    // * 要是个实例对象
    // */
    // private Object target;
    //
    // public MyProxy(Object target) {
    // this.target = target;
    // }
    //
    // /**
    // * 获取被代理接口实例对象
    // *
    // * @return
    // */
     public Object getProxy() {
     return Proxy.newProxyInstance(target.getClass().getClassLoader(),
     target.getClass().getInterfaces(), this);
     }
    //
    // @Override
    // public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    // System.out.println("你被代理了————start");
    // Object invoke = method.invoke(method, target, args);
    // System.out.println("你被代理了-----end");
    // return invoke;
    // }

    /**
     * 目标对象
     */
    private Object target;

    /**
     * 构造函数
     *
     * @param target
     */
    public MyProxy(Object target) {
//        super();
        this.target = target;
    }

    /**
     * 实现InvocationHandler接口的方法
     *
     * 执行目标对象的方法，并进行增强
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        System.out.println("代理类方法，进行增强。。。");
        System.out.println("事务开始。。。");
        // 执行目标方法对象
        result = method.invoke(target, args);
        System.out.println("事务结束。。。");
        return result;
    }

//    /**
//     * 创建代理实例
//     *
//     * @return
//     * @throws Throwable
//     */
//    public Object getProxy() throws Throwable {
//        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
//                        this.target.getClass().getInterfaces(), this);
//        // 这样写只返回了目标对象，没有生成代理对象
//        // return null;
//    }
}
