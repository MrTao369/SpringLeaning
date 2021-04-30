package com.yc.demo01;

/**
 * @program: testspring
 * @description: 中介
 * @author: 张韬
 * @create: 2021-04-12 20:05
 */
public class Proxy implements Rent {
    private Host host;

    public Proxy(Host host) {
        this.host = host;
    }

    public Proxy() {
    }

    @Override
    public void rent() {
        seeHouse();
        hetong();
        fare();
        host.rent();
    }

    public void seeHouse() {
        System.out.println("中介带你看房");
    }

    public void hetong() {
        System.out.println("和中介签合同");
    }

    public void fare() {
        System.out.println("收中介费");
    }
}
