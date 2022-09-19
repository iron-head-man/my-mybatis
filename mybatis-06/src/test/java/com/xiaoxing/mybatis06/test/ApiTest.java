package com.xiaoxing.mybatis06.test;


import java.io.IOException;
import java.io.Reader;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.xiaoxing.mybatis06.io.Resources;
import com.xiaoxing.mybatis06.session.SqlSession;
import com.xiaoxing.mybatis06.session.SqlSessionFactory;
import com.xiaoxing.mybatis06.session.SqlSessionFactoryBuilder;
import com.xiaoxing.mybatis06.test.dao.IUserDao;
import com.xiaoxing.mybatis06.test.po.User;

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
    public void test_SqlSessionFactory2() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        long l = System.currentTimeMillis();
        System.out.println();
        for (int i = 0; i < 50; i++) {
            User user = userDao.queryUserInfoById(1L);
            logger.info("测试结果：{}", JSON.toJSONString(user));
        }
        System.out.println("耗时："+(System.currentTimeMillis()-l));
    }


    @Test
    public void test_SqlSessionFactory1() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User user = userDao.queryUserInfoById(1L);
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }


    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        // 1.1加载资源文件，获取io流
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        // 1.2根据io流，构建sqlSessionFactory---带configuration，mapper文件中的sql语句已经注册在configuration的map中，mapper也注册在configuration中
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // 1.3 打开一个sqlSession,带configuration
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User res = userDao.queryUserInfoById(10001L);
        logger.info("测试结果：{}", res);
    }
//    @Test
//    public void test_MapperProxyFactory() {
//
//        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
//        HashMap<String, String> sqlSession = new HashMap<>();
//
//        sqlSession.put("com.xiaoxing.mybatis04.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
//        sqlSession.put("com.xiaoxing.mybatis04.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");
//
//        IUserDao userDao = factory.newInstance(sqlSession);
//
//        String res = userDao.queryUserName("10001");
//        logger.info("测试结果：{}", res);
//
//
//    }

//    @Test
//    public void test_MapperProxyFactory() {
//        Configuration configuration = new Configuration();
//        //1.注册mapper
//        MapperRegistry mapperRegistry = new MapperRegistry(configuration);
//        mapperRegistry.addMappers("com.xiaoxing.mybatis04.test.dao");
//
//        //2.从sqlSession工厂获取sqlSeSSION
//        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
//        SqlSession sqlSession = defaultSqlSessionFactory.openSqlSession();
//
//        // 3.获取映射器对象
//        IUserDao dao = sqlSession.getMapper(IUserDao.class);
//        String s = dao.queryUserName("10001");
//        logger.info("测试结果：{}", s);
//
//
//    }
}
