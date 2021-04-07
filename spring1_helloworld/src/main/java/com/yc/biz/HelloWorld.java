package com.yc.biz;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-04 15:29
 */
@Component  // 只要加这个注解，这个类就可以被spring托管
@Lazy(value = true)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HelloWorld {
    public HelloWorld() {
        System.out.println("无参构造方法");
    }

    public void hello() {
        System.out.println("hello world");
    }
}
