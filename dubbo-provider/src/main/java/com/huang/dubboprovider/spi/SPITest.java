package com.huang.dubboprovider.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

public class SPITest {

    public static void main(String[] args) {

        // 获取Database接口的ExtensionLoader
        ExtensionLoader<Database> extensionLoader = ExtensionLoader.getExtensionLoader(Database.class);

        Database adaptiveExtension = extensionLoader.getAdaptiveExtension();
        adaptiveExtension.test1(URL.valueOf("dubbo://xxxx?test=mysql"));

        // 使用Database的ExtensionLoader获取指定的实现类
        Database mysql = extensionLoader.getExtension("mysql");
        System.out.println(mysql.getDatabaseName());
        Database psql = extensionLoader.getExtension("psql");
        System.out.println(psql.getDatabaseName());
        Database sqlServer = extensionLoader.getExtension("sqlserver");
        System.out.println(sqlServer.getDatabaseName());

    }

}
