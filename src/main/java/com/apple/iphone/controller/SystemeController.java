package com.apple.iphone.controller;

import com.apple.iphone.service.InventoryInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: iphone
 * @ClassName SystemeController
 * @description:
 * @author: Liujg
 * @create: 2020-10-28 14:36:06
 * @Version 1.0
 **/
@Controller
public class SystemeController {
    @Autowired
    private InventoryInquiryService inventoryInquiryService;
    /**
     * @return
     * @Author liujg
     * @Description //TODO 系统首页
     * @Date 2020年10月28日14:27:37
     * @Param
     **/
    @RequestMapping(value = "/")
    public String index() {
        return "/index";
    }
}
