package com.xiaoxing.mybatis06.session;

import java.util.HashMap;
import java.util.Map;

import com.xiaoxing.mybatis06.binding.MapperRegistry;
import com.xiaoxing.mybatis06.datasource.druid.DruidDataSourceFactory;
import com.xiaoxing.mybatis06.datasource.pooled.PooledDataSourceFactory;
import com.xiaoxing.mybatis06.datasource.unpooled.UnpooledDataSourceFactory;
import com.xiaoxing.mybatis06.mapping.Environment;
import com.xiaoxing.mybatis06.mapping.MappedStatement;
import com.xiaoxing.mybatis06.transaction.jdbc.JdbcTransactionFactory;
import com.xiaoxing.mybatis06.type.TypeAliasRegistry;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/22 23:46
 */
public class Configuration {

    //环境
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

    public <T> T getMapper(Class<T> type, com.xiaoxing.mybatis06.session.SqlSession sqlSession) {
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
}
