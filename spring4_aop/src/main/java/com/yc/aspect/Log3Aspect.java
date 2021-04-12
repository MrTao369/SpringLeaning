package com.yc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-10 18:52
 */
@Aspect
@Component  //实现让spring托管功能
@Order(value = 1)
public class Log3Aspect {

    @Around("execution(* com.yc.biz.StudentBizImpl.find*(..))")
    public Object computer2(ProceedingJoinPoint pjp) throws Throwable { //  DI
        System.out.println("*********************computer2  进到  增强了...");
        long start = System.currentTimeMillis();
        Object retVal = pjp.proceed();      //目标类的目标方法
        long end = System.currentTimeMillis();
        System.out.println("computer2 要退出增强了.......");
        System.out.println("**********************这个方法运行的时长为：" + (end - start));
        return retVal;
    }
}
