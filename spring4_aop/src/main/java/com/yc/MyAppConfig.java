package com.yc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-05 11:47
 */
@Configuration
@ComponentScan(basePackages = "com.yc")
@EnableAspectJAutoProxy  //启用@AspectJ支持
public class MyAppConfig {


}
