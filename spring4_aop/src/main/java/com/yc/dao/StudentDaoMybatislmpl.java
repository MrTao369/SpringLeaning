package com.yc.dao;

import org.springframework.stereotype.Repository;

import java.util.Random;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-04 14:48
 */
@Repository     //提供异常转换
public class StudentDaoMybatislmpl implements StudentDao {
    @Override
    public int add(String name) {
        System.out.println("mybatis添加学生：" + name);
        Random r = new Random();
        return r.nextInt();
    }

    @Override
    public void update(String name) {
        System.out.println("mybatis更新学生:" + name);
    }

    @Override
    public String find(String name) {
        System.out.println("mybatis查找学生:" + name);
        return name;
    }
}
