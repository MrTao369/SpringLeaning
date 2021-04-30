package com.yc.demo01;

/**
 * @program: testspring
 * @description:客户
 * @author: 张韬
 * @create: 2021-04-12 20:00
 */
public class Client {
    public static void main(String[] args) {
        //房东要租房子
        Host host = new Host();
        //代理  中介帮房东租房子,代理角色有附属操作
        Proxy proxy = new Proxy(host);
        
        proxy.rent();
    }
}
