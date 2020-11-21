package com.huang.dubboprovider.spi;

import org.springframework.util.StringUtils;

/**
 * Database的包装类
 * 将getDatabaseName()方法返回的字符串进行装饰，将小写字母全都转成大写字母。
 *
 */
public class DatabaseNameUpperCaseWrapper implements Database {

    public DatabaseNameUpperCaseWrapper(Database database) {
        this.delegate = database;
    }

    private final Database delegate;

    private UsingDatabaseRecord databaseRecord;

    public void setUsingDatabaseRecord(UsingDatabaseRecord databaseRecord) {
        this.databaseRecord = databaseRecord;
    }


    @Override
    public String getDatabaseName() {
        String databaseName = delegate.getDatabaseName();
        databaseName = databaseName == null ? null : databaseName.trim().toUpperCase();
        if (databaseName != null && databaseName.length() > 0 && databaseRecord != null) {
            databaseRecord.usingDatabaseName.add(databaseName);
        }
        return databaseName;
    }
}
