package com.yc.tx.dao;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class OpRecordDaoImplTest {
    @Autowired
    private OpRecordDao accountsDao;


    @Test
    public void save() {
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(1);
        opRecord.setOpmoney(1.0);
        opRecord.setOptype(OpTypes.deposize.getName());  //用枚举做这个值，不容易出错
        opRecord.setOptime(new Timestamp(new Date().getTime()));    //这里是一个Timestamp
        opRecord.setTransferid(" ");//因为只是一个简单的操作,没有transferid
        accountsDao.saveOpRecord(opRecord);
    }

    @Test
    public void findAll() {
        List<OpRecord> list = accountsDao.findAll();
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public void findByAccountid() {
        List<OpRecord> list = accountsDao.findByAccountid(1);
        Assert.assertNotEquals(0, list.size());
    }
}