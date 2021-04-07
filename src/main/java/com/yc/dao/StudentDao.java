package com.yc.dao;

import org.springframework.stereotype.Component;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-04 14:44
 */
@Component
public interface StudentDao {
    public int add(String name);

    public void update(String name);

}
