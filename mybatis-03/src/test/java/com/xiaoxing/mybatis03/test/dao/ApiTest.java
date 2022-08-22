package com.xiaoxing.mybatis03.test.dao;


import com.xiaoxing.mybatis03.binding.MapperRegistry;
import com.xiaoxing.mybatis03.session.Configuration;
import com.xiaoxing.mybatis03.session.SqlSession;
import com.xiaoxing.mybatis03.session.SqlSessionFactory;
import com.xiaoxing.mybatis03.session.defaults.DefaultSqlSession;
import com.xiaoxing.mybatis03.session.defaults.DefaultSqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/20 21:22
 */
public class ApiTest {


    private Logger logger = LoggerFactory.getLogger(ApiTest.class);
//    @Test
//    public void test_MapperProxyFactory() {
//
//        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
//        HashMap<String, String> sqlSession = new HashMap<>();
//
//        sqlSession.put("com.xiaoxing.mybatis03.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
//        sqlSession.put("com.xiaoxing.mybatis03.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");
//
//        IUserDao userDao = factory.newInstance(sqlSession);
//
//        String res = userDao.queryUserName("10001");
//        logger.info("测试结果：{}", res);
//
//
//    }

    @Test
    public void test_MapperProxyFactory() {
        Configuration configuration = new Configuration();
        //1.注册mapper
        MapperRegistry mapperRegistry = new MapperRegistry(configuration);
        mapperRegistry.addMappers("com.xiaoxing.mybatis03.test.dao");

        //2.从sqlSession工厂获取sqlSeSSION
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = defaultSqlSessionFactory.openSqlSession();

        // 3.获取映射器对象
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        String s = dao.queryUserName("10001");
        logger.info("测试结果：{}", s);
    }
}
