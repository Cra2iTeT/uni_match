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
    USER_NOT_EXISTED(10012, "用户不存在或已注销"),
    TAGS_SUCCESS(10020, "标签更改成功"),
    RELATION_SUCCESS(10040, "关注列表获取成功"),
    RELATION_IS_FOLLOWED(10041, "已经关注请勿重复关注"),
    RELATION_FOLLOW_SUCCESS(10042, "关注成功"),
    RELATION_FOLLOW_FAIL(10043, "关注失败"),
    RELATION_NOT_FOLLOWED(10044, "未关注该用户"),
    RELATION_UNFOLLOW_SUCCESS(10045, "取消关注成功"),
    RELATION_UNFOLLOW_FAIL(10046, "取消关注失败"),
    RELATION_NOT_FAN(10047, "该用户非您粉丝"),
    RELATION_BLOCK_SUCCESS(10048, "已添加至黑名单"),
    RELATION_BLOCK_FAIl(10048, "添加至黑名单失败"),
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
