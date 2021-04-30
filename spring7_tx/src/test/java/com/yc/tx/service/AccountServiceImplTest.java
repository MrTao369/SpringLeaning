package com.yc.tx.service;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void openAccount() {
        Integer accountid = accountService.openAccount(new Accounts(), 1);
        System.out.println(accountid);
        Assert.assertNotNull(accountid);
    }

    @Test
    public void deposite() {
        Accounts a = new Accounts();
        a.setAccountId(6);
        Accounts aa = accountService.deposite(a, 100, OpTypes.deposize.getName(), null);
        System.out.println(aa);
    }

    @Test
    public void withdraw() {
        Accounts a = new Accounts();
        a.setAccountId(6);
        Accounts aa = accountService.withdraw(a, 99, OpTypes.withdraw.getName(), null);
        System.out.println(aa);
    }

    @Test
    public void transfer() {
        Accounts out = new Accounts();
        out.setAccountId(7);

        Accounts in = new Accounts();
        in.setAccountId(6);

        this.accountService.transfer(in, out, 5);
    }

    @Test
    public void showBalance() {
        Accounts a = new Accounts();
        a.setAccountId(7);
        a = this.accountService.showBalance(a);
        System.out.println(a);
    }

    @Test
    public void findById() {
    }
}