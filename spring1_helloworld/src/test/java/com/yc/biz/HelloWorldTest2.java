package com.yc.biz;

import com.yc.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//
@ContextConfiguration(classes = {AppConfig.class})
public class HelloWorldTest2 {
    @Autowired
    private HelloWorld hw;
    @Autowired
    private HelloWorld hw1;

    @Test
    public void test() {
        System.out.println("哈哈哈哈");


        System.out.println(hw.hashCode());
        System.out.println(hw1.hashCode());
    }

}