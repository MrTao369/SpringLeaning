package com.yc.bank.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-24 20:38
 */
@Data
public class AccountVO implements Serializable {

    private static final long serialVersionUID = 2572551903849528088L;
    private Integer accountId;
    private Double money;
    private Integer InAccountId;
}
