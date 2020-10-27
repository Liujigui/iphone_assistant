package com.apple.iphone.entity;

import lombok.Data;

/**
 * @program: iphone
 * @ClassName Stores
 * @description:
 * @author: Liujg
 * @create: 2020-10-27 20:07
 * @Version 1.0
 **/
@Data
public class Stores {
    private int id;
    private String storeNumber;
    private String city;
    private String storeName;
    private float latitude;
    private float longitude;
    private boolean enabled;
}
