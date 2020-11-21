package com.huang.dubboprovider.spi;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component("databaseRecord")
public class UsingDatabaseRecord {

    // 记录正在被使用的 Database 的名字。
    public final List<String> usingDatabaseName = new ArrayList<>();

}
