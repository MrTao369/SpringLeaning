package com.yc.biz;

import com.yc.dao.StudentDao;
import com.yc.springframework.stereotype.MyAutowired;
import com.yc.springframework.stereotype.MyQualifile;
import com.yc.springframework.stereotype.MyService;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-04 14:49
 */
@MyService//给spring的类托管
public class StudentBizImpl {
    private StudentDao studentDao;

    public StudentBizImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentBizImpl() {

    }

    @MyAutowired
    @MyQualifile("studentDaoJpaImpl")      //因为有多个对象可以注入，所以这里必须用@Name(studentDaoJpaImpl)
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int add(String name) {
        System.out.println("==========业务层===========");
        System.out.println("用户名是否重名");
        int result = studentDao.add(name);
        System.out.println("======业务操作结束==============");
        return result;
    }

    public void update(String name) {
        System.out.println("==========业务层===========");
        System.out.println("用户名是否重名");
        studentDao.update(name);
        System.out.println("======业务操作结束==============");

    }
}

