package com.yc;

import com.yc.springframework.stereotype.MyComponentScan;
import com.yc.springframework.stereotype.MyConfiguration;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-05 11:47
 */
@MyConfiguration
@MyComponentScan(basePackages = {"com.yc.dao", "com.yc.biz"})
public class MyAppConfig {
//    @MyBean
//    public HelloWorld hw() {
//        return new HelloWorld();
//    }
//
//    @MyBean
//    public HelloWorld hw2() {
//        return new HelloWorld();
//    }


}
