package com.yc.biz;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-10 19:18
 */
public interface StudentBiz {
    int add(String name);

    void update(String name);

    String find(String name);
}
