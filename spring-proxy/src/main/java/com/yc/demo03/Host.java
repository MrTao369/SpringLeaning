package com.yc.demo03;

/**
 * @program: testspring
 * @description: 房东
 * @author: 张韬
 * @create: 2021-04-12 19:58
 */
public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("房东要出租房子");
    }
}
