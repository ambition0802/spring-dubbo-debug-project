package com.huang.dubboprovider.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI
public interface Database {

    String getDatabaseName();

    @Adaptive("test")
    default void test1(URL url) { }

    @Adaptive({"test","test2"})
    default void test2(URL url) { }

    default void test3(URL url) { }
}
