package com.yc.bean;

import com.yc.springframework.stereotype.MyPostConstruct;
import com.yc.springframework.stereotype.MyPreDestroy;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-05 14:41
 */
//@MyComponet
public class HelloWorld {
    @MyPostConstruct
    public void setup() {
        System.out.println("MyPostConstuct");
    }

    @MyPreDestroy
    public void destroy() {
        System.out.println("MyPreDestory");
    }

    public HelloWorld() {
        System.out.println("hello world 构造");
    }

    public void show() {
        System.out.println("show");
    }
}
