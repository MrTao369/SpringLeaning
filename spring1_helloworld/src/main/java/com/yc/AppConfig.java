package com.yc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-04 15:31
 */
@Configuration      //表示当前的类是一个配置类
@ComponentScan(basePackages = "com.yc")     //将要托管的bean要扫描的及子包
public class AppConfig {
    
}
