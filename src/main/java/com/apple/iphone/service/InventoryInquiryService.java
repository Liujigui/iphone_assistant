package com.apple.iphone.service;

import com.apple.iphone.entity.User;
import com.apple.iphone.util.ResultUtil;

import java.sql.SQLException;

/**
 * @program: iphone
 * @ClassName InventoryInquiryService
 * @description:
 * @author: Liujg
 * @create: 2020-10-27 19:43
 * @Version 1.0
 **/
public interface InventoryInquiryService {
    /**
     * @Author liujg
     * @Description //TODO 间隔定时执行查询
     * @Date 19:45 2020/10/27
     * @Param
     * @return
     **/
    void intervalQuery();
    /**
     * @Author liujg
     * @Description //TODO 查询门店信息
     * @Date 19:45 2020/10/27
     * @Param
     * @return
     **/
    ResultUtil queryStores();

    /** 
     * @Author liujg
     * @Description //TODO 新增订阅用户
     * @Date 22:01 2020/10/27
     * @Param 
     * @return 
     **/
    ResultUtil addUser(User user);

    /**
     * @Author liujg
     * @Description //TODO 删除订阅用户监测
     * @Date 22:01 2020/10/27
     * @Param
     * @return
     **/
    ResultUtil delUser(String email);

    /**
     * @return
     * @Author liujg
     * @Description //TODO 获取直营店 城市
     * @Date 2020年10月27日23:07:48
     * @Param
     */
    ResultUtil getStores();

    /**
     * @return
     * @Author liujg
     * @Description //TODO 获取型号
     * @Date 2020年10月27日23:14:54
     * @Param
     */
    ResultUtil getModel();
}
