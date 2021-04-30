package com.yc.bank.service;

import com.yc.bank.bean.Accounts;
import com.yc.bank.bean.OpTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {
    @Autowired
    private AccountService accountService;

    @Test
    //@Transactional
    public void openAccount() {
        Integer accountid = accountService.openAccount(new Accounts(), 100);
        System.out.println(accountid);
        Assert.assertNotNull(accountid);
    }

    @Test
    //@Transactional
    public void testdeposite() {
        Accounts a = new Accounts();
        a.setAccountId(3);
        Accounts aa = accountService.deposite(a, 100, OpTypes.deposize.getName(), null);
        System.out.println(aa);
    }

    @Test
    @Transactional
    public void withdraw() {
        Accounts a = new Accounts();
        a.setAccountId(3);
        Accounts aa = accountService.withdraw(a, 10, OpTypes.withdraw.getName(), null);
        System.out.println(aa);
    }

    @Test
    @Transactional
    public void transfer() {
        Accounts out = new Accounts();
        out.setAccountId(2);

        Accounts in = new Accounts();
        in.setAccountId(3);

        this.accountService.transfer(in, out, 5);
    }

    @Test
    public void showBalance() {
        Accounts a = new Accounts();
        a.setAccountId(3);
        a = this.accountService.showBalance(a);
        System.out.println(a);
    }

    @Test
    public void findById() {
    }
}