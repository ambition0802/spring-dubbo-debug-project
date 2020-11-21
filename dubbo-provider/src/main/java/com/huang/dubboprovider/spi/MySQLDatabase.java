package com.huang.dubboprovider.spi;

import org.apache.dubbo.common.extension.Adaptive;

public class MySQLDatabase implements Database{

    private static final String MYSQL_NAME = "MySQL";

    @Override
    public String getDatabaseName() {
        return MYSQL_NAME;
    }
}
