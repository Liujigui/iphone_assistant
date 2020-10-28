package com.apple.iphone.controller;

import com.apple.iphone.entity.User;
import com.apple.iphone.service.InventoryInquiryService;
import com.apple.iphone.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * @program: iphone
 * @ClassName InventoryInquiryController
 * @description:
 * @author: Liujg
 * @create: 2020-10-27 21:54
 * @Version 1.0
 **/
@RestController
public class InventoryInquiryController {
    @Autowired
    private InventoryInquiryService inventoryInquiryService;

    /**
     * @return
     * @Author liujg
     * @Description //TODO 手动单次调取
     * @Date 21:55 2020/10/27
     * @Param
     **/
    @GetMapping(value = "/intervalQuery")
    public void intervalQuery() {
        inventoryInquiryService.intervalQuery();
    }

    /**
     * @return
     * @Author liujg
     * @Description //TODO 更新门店详细新
     * @Date 21:55 2020/10/27
     * @Param
     **/
    @GetMapping(value = "/queryStores")
    public ResultUtil queryStores() {
        return inventoryInquiryService.queryStores();
    }

    /**
     * @return
     * @Author liujg
     * @Description //TODO 新增订阅用户
     * @Date 22:01 2020/10/27
     * @Param
     **/
    @PostMapping(value = "/addUser")
    public ResultUtil addUser(@RequestBody User user) {
        return inventoryInquiryService.addUser(user);
    }

    /**
     * @param email 邮箱地址
     * @return
     * @Author liujg
     * @Description //TODO 删除订阅用户监测
     * @Date 22:01 2020/10/27
     * @Param
     */
    @GetMapping(value = "/delUser")
    public ResultUtil delUser(@RequestParam String email) {
        return inventoryInquiryService.delUser(email);
    }

    /**
     * @return
     * @Author liujg
     * @Description //TODO 获取直营店 城市
     * @Date 2020年10月27日23:07:48
     * @Param
     */
    @GetMapping(value = "/getStores")
    public ResultUtil getStores() {
        return inventoryInquiryService.getStores();
    }

    /**
     * @return
     * @Author liujg
     * @Description //TODO 获取型号
     * @Date 2020年10月27日23:14:54
     * @Param
     */
    @GetMapping(value = "/getModel")
    public ResultUtil getModel() {
        return inventoryInquiryService.getModel();
    }
}
