package com.yc.springframework.context;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-05 11:40
 */
public interface MyApplicationContext {

    public Object getBean(String id);
}
