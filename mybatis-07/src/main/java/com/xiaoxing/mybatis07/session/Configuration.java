package com.xiaoxing.mybatis07.session;

import java.util.HashMap;
import java.util.Map;

import com.xiaoxing.mybatis07.binding.MapperRegistry;
import com.xiaoxing.mybatis07.datasource.druid.DruidDataSourceFactory;
import com.xiaoxing.mybatis07.datasource.pooled.PooledDataSourceFactory;
import com.xiaoxing.mybatis07.datasource.unpooled.UnpooledDataSourceFactory;
import com.xiaoxing.mybatis07.executor.Executor;
import com.xiaoxing.mybatis07.executor.SimpleExecutor;
import com.xiaoxing.mybatis07.executor.resultset.DefaultResultSetHandler;
import com.xiaoxing.mybatis07.executor.resultset.ResultSetHandler;
import com.xiaoxing.mybatis07.executor.statement.PreparedStatementHandler;
import com.xiaoxing.mybatis07.executor.statement.StatementHandler;
import com.xiaoxing.mybatis07.mapping.BoundSql;
import com.xiaoxing.mybatis07.mapping.Environment;
import com.xiaoxing.mybatis07.mapping.MappedStatement;
import com.xiaoxing.mybatis07.transaction.Transaction;
import com.xiaoxing.mybatis07.transaction.jdbc.JdbcTransactionFactory;
import com.xiaoxing.mybatis07.type.TypeAliasRegistry;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/22 23:46
 */
public class Configuration {

    // 环境
    protected Environment environment;

    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Configuration setEnvironment(Environment environment) {
        this.environment = environment;
        return this;
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }


    /**
     * 创建结果集处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor,mappedStatement,boundSql);
    }

    /**
     * 创建语句处理器
     */
    public StatementHandler newStatementHandler(SimpleExecutor simpleExecutor, MappedStatement ms, Object parameter,
                                                ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(simpleExecutor, ms, parameter, resultHandler, boundSql);
    }

    /**
     * 生产执行器
     */
    public Executor newExecutor(Transaction tx) {
        return new SimpleExecutor(this, tx);
    }
}
