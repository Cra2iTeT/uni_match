package com.Cra2iTeT.UniMatch.common.code;

/**
 * @author Cra2iTeT
 * @since 2023/6/19 22:18
 */
public class UserCode {
    public static final boolean REG_FLAG_EMAIL = false;
    public static final boolean REG_FLAG_PHONE = true;

    public static final int ROLE_NEW_USER = 0;

    public static final String LOGIN_TOKEN_USER_INFO = "login:token:user_info:";
    public static final long LOGIN_TOKEN_USER_INFO_TTL = 1000 * 60 * 60 * 24;
}
