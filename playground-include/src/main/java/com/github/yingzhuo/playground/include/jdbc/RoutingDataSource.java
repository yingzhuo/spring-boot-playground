package com.github.yingzhuo.playground.include.jdbc;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.MapDataSourceLookup;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class RoutingDataSource extends AbstractRoutingDataSource {

    public RoutingDataSource(DataSource defaultDataSource, Map<String, DataSource> targetDataSources) {
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(new HashMap<Object, Object>(targetDataSources));
        super.setDataSourceLookup(new MapDataSourceLookup(targetDataSources));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return RoutingDataSourceLookup.get();
    }

}
