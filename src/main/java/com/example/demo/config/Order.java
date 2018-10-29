package com.example.demo.config;

import lombok.Data;

import java.util.Date;

/**
 * @author THLiu
 */
@Data
public class Order {
    private String orderNum;

    private String type;

    private int num;

    private Date createAt;
}
