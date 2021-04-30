package com.yc.demo02;

/**
 * @program: testspring
 * @description:增删改查的实现方法
 * @author: 张韬
 * @create: 2021-04-12 20:22
 */
public class UserImpl implements User {
    @Override
    public void add() {
        System.out.println("增加了一个用户");
    }

    @Override
    public void update() {
        System.out.println("修改了一个用户");
    }

    @Override
    public void delete() {
        System.out.println("删除了一个用户");
    }

    @Override
    public void find() {
        System.out.println("查找了一个用户");
    }
}
