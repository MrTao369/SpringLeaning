package com.yc.biz;

import com.yc.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-04 14:49
 */
@Service//给spring的类托管
public class StudentBizImpl implements StudentBiz {
    private StudentDao studentDao;

    public StudentBizImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentBizImpl() {

    }

    @Autowired
    @Qualifier("studentDaoJpaImpl")//因为有多个对象可以注入，所以这里必须用@Name(studentDaoJpaImpl)
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int add(String name) {
        System.out.println("==========业务层===========");
        System.out.println("用户名是否重名");
        int result = studentDao.add(name);
        System.out.println("======业务操作结束==============");
        return result;
    }


    @Override
    public void update(String name) {
        System.out.println("==========业务层===========");
        System.out.println("用户名是否重名");
        studentDao.update(name);
        System.out.println("======业务操作结束==============");

    }

    @Override
    public void find(String name) {
        System.out.println("==========业务层===========");
        System.out.println("业务层查询用户名");
        studentDao.find(name);
        Random r = new Random();
        try {
            Thread.sleep(r.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("======业务操作结束==============");

    }
}

