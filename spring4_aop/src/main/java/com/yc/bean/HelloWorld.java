package com.yc.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-05 14:41
 */
@Component
public class HelloWorld {
    @PostConstruct
    public void setup() {
        System.out.println("MyPostConstuct");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("MyPreDestory");
    }

    public HelloWorld() {
        System.out.println("hello world 构造");
    }

    public void show() {
        System.out.println("show");
    }
}
