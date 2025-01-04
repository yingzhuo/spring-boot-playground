package com.github.yingzhuo.playground.include.jdbc;

import org.springframework.util.Assert;

public final class RoutingDataSourceLookup {

    private static final ThreadLocal<String> DATASOURCE_NAME_HOLDER = ThreadLocal.withInitial(() -> DataSourceNames.MASTER);

    public static void useMaster() {
        set(DataSourceNames.MASTER);
    }

    public static void useSlave() {
        set(DataSourceNames.SLAVE);
    }

    public static void set(String dataSourceName) {
        Assert.hasText(dataSourceName, "dataSourceName is required");
        DATASOURCE_NAME_HOLDER.set(dataSourceName);
    }

    public static String get() {
        return DATASOURCE_NAME_HOLDER.get();
    }

    public static void remove() {
        DATASOURCE_NAME_HOLDER.remove();
    }

}
