package com.xiaoxing.mybatis05.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * <p>
 * 数据源工厂
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/08/30 23:18
 */
public interface DataSourceFactory {

    void setProperties(Properties properties);

    DataSource getDataSource();
}
