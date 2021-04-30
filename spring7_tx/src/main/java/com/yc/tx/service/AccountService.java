package com.yc.tx.service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;

import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-17 16:38
 */
public interface AccountService {
    /**
     * 开户
     */
    Integer openAccount(Accounts account, double money);

    /**
     * 存钱
     */
    Accounts deposite(Accounts account, double money, String optype, String transferid);

    /**
     * 取钱
     */
    Accounts withdraw(Accounts account, double money, String optype, String transferid);

    /**
     * 转账
     */
    Accounts transfer(Accounts inAccount, Accounts outAccount, double money);

    /**
     * 查看余额
     */
    Accounts showBalance(Accounts account);

    /**
     * 查看日志
     */
    List<OpRecord> findById(Accounts account);
}
