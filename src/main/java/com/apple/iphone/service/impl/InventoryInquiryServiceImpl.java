package com.apple.iphone.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.apple.iphone.entity.Stores;
import com.apple.iphone.entity.User;
import com.apple.iphone.service.InventoryInquiryService;
import com.apple.iphone.service.MailService;
import com.apple.iphone.util.CodeEnum;
import com.apple.iphone.util.ResultUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: iphone
 * @ClassName InventoryInquiryServiceImpl
 * @description:
 * @author: Liujg
 * @create: 2020-10-27 19:43
 * @Version 1.0
 **/
@Service
@Slf4j
public class InventoryInquiryServiceImpl implements InventoryInquiryService {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private MailService mailService;
    /**
     * @Author liujg
     * @Description //TODO 库存请求地址
     * @Date 20:09 2020/10/27
     * @Param
     * @return
     **/
    @Value("${apple.stockAddress}")
    private String stockAddress;
    /**
     * @Author liujg
     * @Description //TODO 门店信息地址
     * @Date 20:09 2020/10/27
     * @Param
     * @return
     **/
    @Value("${apple.stores}")
    private String stores;

    /**
     * @Author liujg
     * @Description //TODO server酱 秘钥
     * @Date 08:27 2020/10/28
     * @Param
     * @return
     **/
    @Value("${serverj.sckey}")
    private String sckey;

    /**
     * @return
     * @Author liujg
     * @Description //TODO 间隔定时执行查询
     * @Date 19:45 2020/10/27
     * @Param
     **/
    @Override
    public void intervalQuery() {

        //发送请求 查询当前门店库存情况
        String ret = HttpUtil.get(stockAddress);
        JSONObject jsonObject = JSONUtil.parseObj(ret);
        JSONObject storesInfo = jsonObject.getJSONObject("stores");

        //执行通知
        notify(storesInfo);
    }


    /**
     * @return
     * @Author liujg
     * @Description //TODO 执行通知
     * @Date 21:35 2020/10/27
     * @Param
     **/
    public void notify(JSONObject storesInfo) {
        if (StringUtils.isEmpty(storesInfo)) {
            log.error("接口已关闭，维护中！！！");
            return;
        }
        try {
            List<Entity> result = Db.use().query("SELECT U.id,M.model,S.storeNumber,U.email,u.city,s.storeName FROM USER U JOIN MODEL M ON U.model=M.ID JOIN stores S ON U.city=S.city WHERE U.`status`=?", 0);
            result.forEach(entity -> {
                //查询指定门店库存信息
                JSONObject stores = storesInfo.getJSONObject(entity.getStr("storeNumber"));
                //查询门店指定机型库存信息
                JSONObject model = stores.getJSONObject(entity.getStr("model"));
                //是否有库存
                boolean unlocked = model.getJSONObject("availability").getBool("unlocked");
                //城市和门店名称
                String storeName = entity.getStr("city") + entity.getStr("storeName");
                //监测到有库存
                if (unlocked) {
                    log.info(DateUtil.now() + ": {}发现有库存！", storeName);
                    //查询是否可以进行发送邮件  防止短时间发送过多 造成打扰
                    if (intervals(entity.getInt("id"))) {
                        //开始发送邮件
                        mailService.sendHtmlMail("兄弟，有库存了", "<html><head><title>contact us</title></head><body><b>" + storeName + ":</b></br><b>已经检测到你订阅的型号有库存，快冲！！！</b></br><a href=\"https://reserve-prime.apple.com/CN/zh_CN/reserve/A/availability?iUP=N\">点击预约</a></body></html>", new String[]{entity.getStr("email")});
                        //更新邮件发送时间
                        updateNotificationTime(entity.getInt("id"));
                    }
                    serverj(storeName, "已经检测到你订阅的型号有库存，快冲！！！");
                } else {
                    //log.warn(DateUtil.now() + ": {}没有库存！", storeName);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return
     * @Author liujg
     * @Description //TODO 查询门店信息
     * @Date 21:12 2020/10/27
     * @Param
     **/
    @Override
    public ResultUtil queryStores() {
        try {
            //发送请求
            String ret = HttpUtil.get(stores);
            JSONObject jsonObject = JSONUtil.parseObj(ret);
            String stores = jsonObject.getStr("stores");
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, Stores.class);

            //json转换成 实体对象集合
            List<Stores> list = MAPPER.readValue(stores, javaType);
            list.forEach(e -> {
                //执行插入数据库
                try {
                    Db.use().insert(
                            Entity.create("stores")
                                    .set("storeNumber", e.getStoreNumber())
                                    .set("city", e.getCity())
                                    .set("storeName", e.getStoreName())
                    );
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.success("门店详细信息更新完成！");
    }

    /**
     * @return
     * @Author liujg
     * @Description //TODO 新增订阅用户
     * @Date 22:01 2020/10/27
     * @Param
     **/
    @Override
    public ResultUtil addUser(User user) {
        //参数异常
        if (StringUtils.isEmpty(user)) {
            return ResultUtil.fail(CodeEnum.ERROR.val(), "参数非法，存在必填选项为空！");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            return ResultUtil.fail(CodeEnum.ERROR.val(), "参数非法，通知邮箱不允许为空！");
        }
        if (StringUtils.isEmpty(user.getCity())) {
            return ResultUtil.fail(CodeEnum.ERROR.val(), "参数非法，监控城市所在地不允许为空！");
        }
        if (StringUtils.isEmpty(user.getModel())) {
            return ResultUtil.fail(CodeEnum.ERROR.val(), "参数非法，监控机型不允许为空！");
        }
        log.info("用户数据：{}", user.toString());
        //插入数据库
        try {
            Db.use().insert(
                    Entity.create("user")
                            .set("model", user.getModel())
                            .set("email", user.getEmail())
                            .set("city", user.getCity())
                            .set("creationTime", new Date())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResultUtil.success("iPhone直营店库存订阅成功！");
    }

    /**
     * @param email
     * @return
     * @Author liujg
     * @Description //TODO 删除订阅用户监测
     * @Date 22:01 2020/10/27
     * @Param
     */
    @Override
    public ResultUtil delUser(String email) {
        if (StringUtils.isEmpty(email)) {
            return ResultUtil.fail(CodeEnum.ERROR.val(), "参数非法，邮箱地址不允许为空！");
        }
        try {
            Db.use().update(
                    Entity.create().set("status", 1), //修改的数据
                    Entity.create("user").set("email", email) //where条件
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("已退订库存监测：{}",email);
        return ResultUtil.success("库存监测订阅取消完成！");
    }

    /**
     * @return
     * @Author liujg
     * @Description //TODO 获取直营店 城市
     * @Date 2020年10月27日23:07:48
     * @Param
     */
    @Override
    public ResultUtil getStores() {
        try {
            List<Entity> result = Db.use().query("SELECT DISTINCT city FROM stores");
            return ResultUtil.success(result, "查询完成");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResultUtil.fail(CodeEnum.ERROR.val(), "系统异常");
    }

    /**
     * @return
     * @Author liujg
     * @Description //TODO 获取型号
     * @Date 2020年10月27日23:14:54
     * @Param
     */
    @Override
    public ResultUtil getModel() {
        try {
            List<Entity> result = Db.use().query("SELECT id,content FROM MODEL");
            return ResultUtil.success(result, "查询完成");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResultUtil.fail(CodeEnum.ERROR.val(), "系统异常");
    }

    /**
     * 查询 指定用户上次发送邮件距离本次发送邮件市场
     * 30秒内 不重复通知
     * 避免邮件轰炸骚扰
     *
     * @param id 用户id
     * @return
     */
    public Boolean intervals(Integer id) {
        final boolean[] flag = {false};
        try {
            //根据用户ID查询上一次发送邮件时间 和 当前时间
            List<Entity> ret = Db.use().query("SELECT notificationTime,now() as currentTime FROM user where id = ?", id);
            ret.forEach(entity -> {
                //上一次发送邮件时间
                String notificationTime = entity.getStr("notificationTime");
                //当前系统时间
                String currentTime = entity.getStr("currentTime");
                Date curTime = DateUtil.parse(currentTime, "yyyy-MM-dd HH:mm:ss");
                if (!StringUtils.isEmpty(notificationTime)) {
                    Date notifTime = DateUtil.parse(notificationTime, "yyyy-MM-dd HH:mm:ss");
                    long betweenSecond = DateUtil.between(curTime, notifTime, DateUnit.SECOND);
                    //大于等于30则可以进行重新发送邮件通知
                    flag[0] = betweenSecond >= 30;
                    log.info("相隔时间(秒)：{}", betweenSecond);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag[0];
    }

    /**
     * 更新邮件通知时间
     *
     * @param id 用户id
     * @return
     */
    public void updateNotificationTime(Integer id) {
        try {
            Db.use().update(
                    Entity.create().set("notificationTime", new Date()), //修改的数据
                    Entity.create("user").set("id", id) //where条件
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @return
     * @Author liujg
     * @Description //TODO 发送微信消息通知
     * @Date 2020年10月28日08:22:22
     * @Param text：消息标题，最长为256，必填。
     * @Param desp：消息内容，最长64Kb，可空，支持MarkDown。
     **/
    public void serverj(String text, String desp) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("text", text);
        paramMap.put("desp", desp);
        HttpUtil.post("https://sc.ftqq.com/" + sckey + ".send", paramMap);
        log.info("微信消息发送成功！");
    }
}
