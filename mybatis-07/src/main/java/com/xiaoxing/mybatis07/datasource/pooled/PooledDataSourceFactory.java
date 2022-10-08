package com.xiaoxing.mybatis07.datasource.pooled;

import com.xiaoxing.mybatis07.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * <p>
 *
 * </p>
 *
 * @author xxingheng@hand-china.com 2022/09/14 22:18
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }
}
