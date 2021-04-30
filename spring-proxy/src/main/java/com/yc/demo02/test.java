package com.yc.demo02;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-12 20:26
 */
public class test {
    public static void main(String[] args) {

        UserImpl u = new UserImpl();

        UserProxy userProxy = new UserProxy();
        
        userProxy.setUser(u);

        userProxy.add();
        userProxy.delete();
        userProxy.update();
        userProxy.find();

    }
}
