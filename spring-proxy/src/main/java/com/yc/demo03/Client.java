package com.yc.demo03;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-14 13:44
 */
public class Client {

    public static void main(String[] args) {
        //真实角色
        Host host = new Host();
        //代理的角色
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //通过调用程序角色来处理我们要调用的接口对象
        pih.setRent(host);
        Rent proxy = (Rent) pih.getProxy();
        proxy.rent();
    }


}
