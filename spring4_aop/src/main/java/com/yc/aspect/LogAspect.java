package com.yc.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: testspring
 * @description: 切面类   你要增强的功能写在这里
 * @author: 张韬
 * @create: 2021-04-09 19:34
 */
@Aspect
@Component  //实现让spring托管功能
@Order(value = 100)
public class LogAspect {

    @Around("execution(* com.yc.biz.StudentBizImpl.find*(..))")
    public Object computer(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("=========================computer  进到  增强了...");
        long start = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("computer 要退出增强了.......");
        System.out.println("============================这个方法运行的时长为：" + (end - start));
        return retVal;
    }

    //切入点声明
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.add*(..))") //切入点表达式：哪些方法上加增强
    private void add() {
    }

    @Pointcut("execution(* com.yc.biz.StudentBizImpl.update*(..))") //切入点表达式：哪些方法上加增强
    private void update() {
    }

    @Pointcut("add() || update()") //切入点表达式：哪些方法上加增强
    private void addAndUpdate() {
    }


    //增强声明
    //@Before("com.yc.aspect.LogAspect.addAndUpdate()")
    public void log() {
        System.out.println("=================前置增强日志==================");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dstr = sdf.format(d);
        System.out.println("执行时间:" + dstr);
        System.out.println("=================前置增强日志的结束==================");
    }
}
