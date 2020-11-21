package com.huang.dubboprovider.spi;

public class SQLServerDatabase implements Database {

    private static final String SQLSERVER_NAME = "SQLServer";

    @Override
    public String getDatabaseName() {
        return SQLSERVER_NAME;
    }
}
