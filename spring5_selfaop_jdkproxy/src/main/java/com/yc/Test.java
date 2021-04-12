package com.yc;

import com.yc.biz.StudentBiz;
import com.yc.biz.StudentBizImpl;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-10 19:31
 */
public class Test {
    public static void main(String[] args) {
        StudentBiz target = (StudentBiz) new StudentBizImpl();

        LogAspect la = new LogAspect(target);

        //生成代理
        Object obj = la.cresteProxy();//obj就是代理对象
        System.out.println(obj);

        if (obj instanceof StudentBiz) {
            StudentBiz sb = (StudentBiz) obj;
            sb.find("张三");//jvm会感知到这个sb是一个proxy,jvm就调用这个proxy中的invoke
            sb.add("李四");
            sb.update("小明");
        }
    }
}
