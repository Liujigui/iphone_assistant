package com.apple.iphone.util;

import lombok.Data;

/**
 * @program: iphone
 * @ClassName ResultUtil
 * @description:
 * @author: Liujg
 * @create: 2020-10-27 22:08
 * @Version 1.0
 **/
@Data
public class ResultUtil {

    private int code;

    private String msg;

    private Object data;

    public static ResultUtil success(Object data) {
        return resultData(CodeEnum.SUCCESS.val(), CodeEnum.SUCCESS.msg(), data);
    }

    public static ResultUtil success(Object data, String msg) {
        return resultData(CodeEnum.SUCCESS.val(), msg, data);
    }

    public static ResultUtil fail(int code, String msg) {
        return resultData(code, msg, null);
    }

    public static ResultUtil fail(int code, String msg, Object data) {
        return resultData(code, msg, data);
    }

    private static ResultUtil resultData(int code, String msg, Object data) {
        ResultUtil resultData = new ResultUtil();
        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

}
