package com.yc.tx;

import com.yc.tx.bean.Accounts;
import com.yc.tx.dao.AccountsDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppConfigTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccountsDao accountsDao;

    //数据源测试
    @Test
    public void testDataSource() throws SQLException {
        Assert.assertNotNull(dataSource);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testAccountsDaoImmpl() {
        Assert.assertNotNull(accountsDao);
    }

    @Test
    public void testOpenAccounts() {
        Accounts a = new Accounts();
        a.setBalance(20.0);
        int accountid = accountsDao.saveAccount(a);
        System.out.println("开户成功,新开户头id为:" + accountid);
    }

    @Test
    public void testFindAll() {
        List<Accounts> list = this.accountsDao.findAll();
        System.out.println(list);
    }

    @Test
    public void testFindByid() {
        Accounts a = this.accountsDao.findAccount(4);
        System.out.println(a);
    }
}