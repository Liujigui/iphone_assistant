package com.apple.iphone.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: iphone
 * @ClassName User
 * @description:
 * @author: Liujg
 * @create: 2020-10-27 22:03
 * @Version 1.0
 **/
@Data
public class User {
    private int id;
    private int model;
    private String email;
    private String city;
    private Date creationTime;
}
