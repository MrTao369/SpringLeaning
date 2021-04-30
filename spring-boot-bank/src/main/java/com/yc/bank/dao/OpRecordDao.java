package com.yc.bank.dao;


import com.yc.bank.bean.OpRecord;

import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-15 20:10
 */
public interface OpRecordDao {

    public void saveOpRecord(OpRecord opRecord);

    public List<OpRecord> findAll();

    public List<OpRecord> findByAccountid(int accountid);
}
