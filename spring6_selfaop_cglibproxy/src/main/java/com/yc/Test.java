package com.yc;

import com.yc.biz.StudentBizImpl;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-10 19:57
 */
public class Test {
    public static void main(String[] args) {
        StudentBizImpl target = (StudentBizImpl) new StudentBizImpl();

        LogAspectCglib lc = new LogAspectCglib(target);

        //生成代理
        Object obj = lc.createProxy();//obj就是代理对象
        //System.out.println(obj);

        if (obj instanceof StudentBizImpl) {
            StudentBizImpl sb = (StudentBizImpl) obj;
            sb.find("张三");//jvm会感知到这个sb是一个proxy,jvm就调用这个proxy中的invoke
            sb.add("李四");
            sb.update("王五");
        }
    }
}
