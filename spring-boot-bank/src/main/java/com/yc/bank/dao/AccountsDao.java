package com.yc.bank.dao;


import com.yc.bank.bean.Accounts;

import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-14 20:21
 */
public interface AccountsDao {

    public int saveAccount(Accounts account);

    public Accounts updateAccount(Accounts account);

    public Accounts findAccount(int accountid);

    public List<Accounts> findAll();

    public void delete(int accountid);
}
