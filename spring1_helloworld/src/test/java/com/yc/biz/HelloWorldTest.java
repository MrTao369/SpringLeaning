package com.yc.biz;

import com.yc.AppConfig;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class HelloWorldTest extends TestCase {
    private ApplicationContext ac;//spring容器

    @Override
    @Before
    public void setUp() {
        //AnnotationConfigApplicationContext基于注解的配置容器类
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //读取AppConfig.class-->basePackages="com.yc"->得到扫描的路径

    }

    @Test
    public void testHello() {
        HelloWorld hw = (HelloWorld) ac.getBean("helloWorld");
        hw.hello();


        //spring 容器是单例模型
    }
}