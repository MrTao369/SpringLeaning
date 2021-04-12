package com.yc.biz;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-10 19:51
 */
public class StudentBizImpl {

    public int add(String name) {
        System.out.println("调用了 StudentBizImpl 中的 add" + name);
        return 100;

    }

    public void update(String name) {
        System.out.println("调用了StudentBizImpl  中的update " + name);
    }

    public String find(String name) {
        System.out.println("调用了StudentBizImpl  中的 find" + name);
        return name + "========";
    }
}
