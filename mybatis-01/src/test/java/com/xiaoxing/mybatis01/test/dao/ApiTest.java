package com.xiaoxing.mybatis01.test.dao;

import com.xiaoxing.mybatis01.binding.MapperProxyFactory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/20 21:22
 */
public class ApiTest {


    private Logger logger = LoggerFactory.getLogger(ApiTest.class);
    @Test
    public void test_MapperProxyFactory() {

        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        HashMap<String, String> sqlSession = new HashMap<>();

        sqlSession.put("com.xiaoxing.mybatis01.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("com.xiaoxing.mybatis01.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");

        IUserDao userDao = factory.newInstance(sqlSession);

        String res = userDao.queryUserName("10001");
        logger.info("测试结果：{}", res);


    }
}
