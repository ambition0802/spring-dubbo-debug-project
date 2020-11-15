package com.huang.dubboprovider.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
/**
 *  classpath:只从当前classpath加载指定资源
 *  classpath*: 从所有的classspath中加载指定资源
 */
@ImportResource({"classpath:dubbo-provider.xml"})
public class ImportDubboProviderConfig {
}
