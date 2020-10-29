package com.apple.iphone.util;


public enum CodeEnum {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 操作失败
     */
    ERROR(500, "操作失败");

    CodeEnum(int value, String msg) {
        this.val = value;
        this.msg = msg;
    }

    public int val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private int val;
    private String msg;
}