package com.apple.iphone.scheduling;

import com.apple.iphone.service.InventoryInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: iphone
 * @ClassName TimedTask
 * @description:
 * @author: Liujg
 * @create: 2020-10-27 22:26
 * @Version 1.0
 **/
@Component
public class TimedTask {
    @Autowired
    private InventoryInquiryService inventoryInquiryService;

    //表示每隔3秒 执行一次
    @Scheduled(fixedRate = 2000)
    private void monitorInventory(){
        inventoryInquiryService.intervalQuery();
    }
}
