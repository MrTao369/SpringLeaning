package com.yc.bank.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-24 20:40
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 8097659475950130135L;
    private Integer code;
    private T data;
    private String msg;
}
