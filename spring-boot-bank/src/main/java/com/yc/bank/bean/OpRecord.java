package com.yc.bank.bean;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-15 20:16
 */
@Data
public class OpRecord {

    private Integer id;
    private Integer accountid;
    private Double opmoney;
    private Timestamp optime;
    private String optype;
    private String transferid;

}
