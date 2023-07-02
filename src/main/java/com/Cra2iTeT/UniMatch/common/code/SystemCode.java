package com.Cra2iTeT.UniMatch.common.code;

/**
 * @author Cra2iTeT
 * @since 2023/4/29 14:12
 */
public enum SystemCode {

    REG_CODE_ERROR(10000, "验证码错误"),
    REG_USER_EXISTED(10001, "用户已存在"),
    REG_SUCCESS(10002, "注册成功"),
    REG_FAIL(10003, "注册失败"),
    LOGIN_FAIL(10010, "用户不存在或密码错误"),
    LOGIN_SUCCESS(10011, "登录成功"),
    TAGS_SUCCESS(10020, "标签更改成功"),
    VALID_USER(100, "非法用户请求"),

    SUCCESS(200, "Success"),

    ERROR(401, "Error");

    private int code;
    private String msg;

    SystemCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
