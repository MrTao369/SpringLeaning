package com.yc.biz;

import com.yc.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StudentBizImplTest {
    //容器
    ApplicationContext ac;
    private StudentBizImpl studentBiz;

    @Before
    public void setUp() {
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
        studentBiz = (StudentBizImpl) ac.getBean("studentBizImpl");
    }

    @Test
    public void add() {
        studentBiz.add("张三");

    }

    @Test
    public void update() {
        studentBiz.update("李四");
    }
}