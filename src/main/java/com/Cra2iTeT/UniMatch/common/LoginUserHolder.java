package com.Cra2iTeT.UniMatch.common;

import com.Cra2iTeT.UniMatch.model.vo.UserVo;

/**
 * @author Cra2iTeT
 * @since 2023/6/24 15:36
 */
public class LoginUserHolder {
    private LoginUserHolder() {
    }

    private static final ThreadLocal<UserVo> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void put(UserVo userVo) {
        USER_THREAD_LOCAL.set(userVo);
    }

    public static UserVo get() {
        return USER_THREAD_LOCAL.get();
    }

    public static void remove() {
        USER_THREAD_LOCAL.remove();
    }
}
