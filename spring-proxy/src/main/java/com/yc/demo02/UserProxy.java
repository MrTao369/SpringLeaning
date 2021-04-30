package com.yc.demo02;

/**
 * @program: testspring
 * @description: 代理类
 * @author: 张韬
 * @create: 2021-04-12 20:29
 */
public class UserProxy implements User {
    private UserImpl user;

    public void setUser(UserImpl user) {
        this.user = user;
    }

    @Override
    public void add() {
        log("add");
        user.add();
    }

    @Override
    public void update() {
        log("update");
        user.update();
    }

    @Override
    public void delete() {
        log("delete");
        user.delete();
    }

    @Override
    public void find() {
        log("find");
        user.find();
    }

    public void log(String msg) {
        System.out.println("使用了" + msg + "方法");
    }
}
