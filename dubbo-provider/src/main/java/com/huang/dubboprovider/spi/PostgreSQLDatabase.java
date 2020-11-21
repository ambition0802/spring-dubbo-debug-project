package com.huang.dubboprovider.spi;

import org.apache.dubbo.common.extension.Adaptive;

public class PostgreSQLDatabase implements Database {

    private static final String PSQL_NAME = "PostgreSQL";

    @Override
    public String getDatabaseName() {
        return PSQL_NAME;
    }
}
