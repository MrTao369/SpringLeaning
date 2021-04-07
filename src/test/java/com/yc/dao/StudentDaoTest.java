package com.yc.dao;

import com.yc.biz.StudentBizImpl;
import junit.framework.TestCase;

public class StudentDaoTest extends TestCase {
    private StudentDao studentDao;
    private StudentBizImpl studentBiz;

    @Override
    protected void setUp() throws Exception {
        //1、能否自动完成  实例化对象
        studentDao = new StudentDaoJpaImpl();

        studentBiz = new StudentBizImpl();

        studentBiz.setStudentDao(studentDao);
    }

    public void testAdd() {
        studentDao.add("张三");

    }

    public void testUpdate() {
        studentDao.update("张三");

    }

    public void testBizAdd() {
        studentBiz.add("张三");
    }


}