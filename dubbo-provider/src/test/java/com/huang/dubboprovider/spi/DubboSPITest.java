package com.huang.dubboprovider.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DubboSPITest implements ApplicationContextAware {

    private ApplicationContext springContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
    }

    /**
     * 测试 Dubbo SPI 的包装类机制
     *
     * {@link Database} 接口存在包装类 {@link DatabaseNameUpperCaseWrapper},
     * 会包装{@link Database} 接口所有的实现类，将getDatabaseName()方法返回的字符串进行装饰，将小写字母全都转成大写字母。
     */
    @Test
    public void testWrapperClass() {
        Database mysql = ExtensionLoader.getExtensionLoader(Database.class).getExtension("mysql");
        assertEquals(mysql.getDatabaseName(), "MYSQL");
    }


    /**
     * 测试 无法直接获取Dubbo SPI的包装类
     */
    @Test
    public void testCantGetWrapperClassDirectly() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                ExtensionLoader.getExtensionLoader(Database.class)
                        .getExtension("databaseNameUpperCaseWrapper"));
        assertTrue(exception.getMessage().contains("No such extension com.huang.dubboprovider.spi.Database by name"));

    }

    /**
     * 测试 Dubbo SPI 的 从Spring上下文中获取依赖并注入的功能
     *
     * {@link DatabaseNameUpperCaseWrapper#databaseRecord} 通过Dubbo SPI 的依赖注入
     * 从Spring上下文中获取UsingDatabaseRecord的bean,
     * 每次通过{@link DatabaseNameUpperCaseWrapper}调用{@link Database#getDatabaseName()}方法时，
     * 只要{@link Database#getDatabaseName()}返回值trim之后不会null且不为空，就会保存到
     * {@link DatabaseNameUpperCaseWrapper#databaseRecord}中。
     *
     */
    @Test
    public void testDIFromSpringContext() {
        ExtensionLoader.getExtensionLoader(Database.class).getExtension("mysql").getDatabaseName();
        ExtensionLoader.getExtensionLoader(Database.class).getExtension("psql").getDatabaseName();
        ExtensionLoader.getExtensionLoader(Database.class).getExtension("sqlserver").getDatabaseName();

        UsingDatabaseRecord bean = springContext.getBean(UsingDatabaseRecord.class);
        assertEquals(bean.usingDatabaseName.size(), 3);
        assertTrue(bean.usingDatabaseName.contains("MYSQL"));
        assertTrue(bean.usingDatabaseName.contains("POSTGRESQL"));
        assertTrue(bean.usingDatabaseName.contains("SQLSERVER"));

    }
}
