package com.yc.bank.controllers;

import com.yc.bank.bean.Accounts;
import com.yc.bank.bean.OpTypes;
import com.yc.bank.service.AccountService;
import com.yc.bank.vo.AccountVO;
import com.yc.bank.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-24 20:52
 */
@Controller
@Slf4j
@Api(value = "银行账户操作接口", tags = {"账户操作接口", "控制层"})
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/openAccounts", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "取款", notes = "根据账户编号及金额来完成取款操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountid", value = "账户编号", required = true, dataType = "java.lang.String"),
            @ApiImplicitParam(name = "balance", value = "操作金额", required = true, dataType = "java.lang.Double")})
    public @ResponseBody
    ResultVO openAccounts(AccountVO accountVO) {

        log.debug("用户请求开户,存入" + accountVO.getMoney());
        ResultVO rv = new ResultVO();

        try {


            Accounts a = new Accounts();
            double money = 0;
            if (accountVO.getMoney() != null && accountVO.getMoney() > 0) {
                money = accountVO.getMoney();
            }

            Integer id = accountService.openAccount(a, money);
            a.setAccountId(id);
            a.setBalance(money);
            rv.setCode(1);
            rv.setData(a);

        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    @ApiOperation(value = "存款", notes = "根据账号,存款金额完成存款操作，注意此时的金额表示存好的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountid", value = "账户编号", required = true, dataType = "java.lang.String"),
            @ApiImplicitParam(name = "money", value = "存款金额", required = true, dataType = "java.lang.Double")})
    public @ResponseBody
    ResultVO<Accounts> deposit(AccountVO accountVO) {
        ResultVO<Accounts> rv = new ResultVO<>();
        Accounts a = new Accounts();
        a.setAccountId(accountVO.getAccountId());
        try {
            a = accountService.deposite(a, accountVO.getMoney(), OpTypes.deposize.getName(), "");
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ApiOperation(value = "取款", notes = "根据账号,存款金额完成取款操作，注意此时的金额表示剩余金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountid", value = "账户编号", required = true, dataType = "java.lang.String"),
            @ApiImplicitParam(name = "money", value = "取款剩余金额", required = true, dataType = "java.lang.Double")})
    public @ResponseBody
    ResultVO<Accounts> withdraw(AccountVO accountVO) {
        ResultVO<Accounts> rv = new ResultVO<>();
        Accounts a = new Accounts();
        a.setAccountId(accountVO.getAccountId());
        try {
            a = accountService.withdraw(a, accountVO.getMoney(), OpTypes.withdraw.getName(), "");
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }


    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    @ApiOperation(value = "转账", notes = "根据账户编号及金额, 对方接收账号来完成转账操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true, dataType = "java.lang.Integer"),
            @ApiImplicitParam(name = "money", value = "转账金额", required = true, dataType = "java.lang.Double"),
            @ApiImplicitParam(name = "inAccountId", value = "对方接收账号", required = true, dataType = "java.lang.Integer")})
    public @ResponseBody
    ResultVO<Accounts> transfer(AccountVO accountVO) {
        Accounts inAccount = new Accounts();
        inAccount.setAccountId(accountVO.getInAccountId());
        Accounts outAccount = new Accounts();
        outAccount.setAccountId(accountVO.getAccountId());
        ResultVO<Accounts> rv = new ResultVO();
        try {
            Accounts a = accountService.transfer(inAccount, outAccount, accountVO.getMoney());
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception ex) {
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }
        return rv;
    }

    @RequestMapping(value = "/showBalance", method = RequestMethod.POST)
    @ApiOperation(value = "查询", notes = "根据账号查询剩余金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountid", value = "账户编号", required = true, dataType = "java.lang.String"),
            @ApiImplicitParam(name = "money", value = "账户余额", required = true, dataType = "java.lang.Double")})
    public @ResponseBody
    ResultVO<Accounts> showBalance(AccountVO accountVO) {
        ResultVO<Accounts> rv = new ResultVO();
        Accounts a = new Accounts();
        a.setAccountId(accountVO.getAccountId());
        try {
            a = accountService.showBalance(a);
            rv.setCode(1);
            rv.setData(a);
        } catch (Exception e) {
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;
    }


}
