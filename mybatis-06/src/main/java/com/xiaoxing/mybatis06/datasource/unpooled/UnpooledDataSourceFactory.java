package com.xiaoxing.mybatis06.datasource.unpooled;

import com.xiaoxing.mybatis06.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/14 21:57
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {

    protected Properties props;

    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }

    @Override
    public DataSource getDataSource() {
        UnpooledDataSource unpooledDataSource = new UnpooledDataSource();
        unpooledDataSource.setDriver(props.getProperty("driver"));
        unpooledDataSource.setUrl(props.getProperty("url"));
        unpooledDataSource.setUsername(props.getProperty("username"));
        unpooledDataSource.setPassword(props.getProperty("password"));
        return unpooledDataSource;
    }
}
