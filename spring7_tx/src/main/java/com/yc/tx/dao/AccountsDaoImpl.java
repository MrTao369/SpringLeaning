package com.yc.tx.dao;

import com.yc.tx.bean.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-15 15:13
 */
@Repository
public class AccountsDaoImpl implements AccountsDao {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDateSourse(DataSource dateSourse) {
        this.jdbcTemplate = new JdbcTemplate(dateSourse);
    }

    /**
     * 增加
     */
    @Override
    public int saveAccount(Accounts account) {
        String sql = "insert into accounts(balance) values( ? )";
        //生成键的保存起
        KeyHolder keyHolder = new GeneratedKeyHolder();
        //connection由spring调用的时候注入
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setDouble(1, account.getBalance());
            return ps;
        }, keyHolder);
        //方案一:用匿名内部类
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement pstmt = connection.prepareStatement(sql, new String[]{"accountid"});
//                pstmt.setDouble(1, account.getBalance());
//                return pstmt;
//            }
//        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    /**
     * 更新
     */
    @Override
    public Accounts updateAccount(Accounts account) {
        String sql = "update accounts set balance=? where accountid=?";
        this.jdbcTemplate.update(sql, account.getBalance(), account.getAccountId());
        return account;
    }

    @Override
    public Accounts findAccount(int accountid) {
        String sql = "select * from accounts where accountid=?";

        return this.jdbcTemplate.queryForObject(sql,
                (resultSet, rowNum) -> {
                    Accounts a = new Accounts();
                    a.setAccountId(resultSet.getInt("accountid"));
                    a.setBalance(resultSet.getDouble("balance"));
                    return a;
                },
                accountid);
    }

    @Override
    public List<Accounts> findAll() {
        String sql = "select * from accounts";
        List<Accounts> list = this.jdbcTemplate.query(sql, (resultSet, i) -> {
            System.out.println("当前取的是第" + i + "行的数据");
            Accounts a = new Accounts();
            a.setAccountId(resultSet.getInt("accountid"));

            a.setBalance(resultSet.getDouble("balance"));

            return a;

        });

//        List<Account> list = this.jdbcTemplate.query(sql, new RowMapper<Account>() {
//            @Override
//            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
//                System.out.println("当前取的是第" + i + "行的数据");
//                Account a = new Account();
//                a.setAccountId(resultSet.getInt("accountid"));
//
//                a.setBalance(resultSet.getDouble("balance"));
//
//                return a;
//            }
//        });
        return list;
    }

    @Override
    public void delete(int accountid) {
        String sql = "delete from accounts where accountid=?";
        this.jdbcTemplate.update(sql, accountid);
    }
}
