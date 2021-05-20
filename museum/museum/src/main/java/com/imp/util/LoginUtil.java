package com.imp.util;

import com.imp.model.UserModel;
import org.apache.shiro.SecurityUtils;

public class LoginUtil {
    /**
     * 获取系统当前登录帐户
     *
     * @return
     */
    public static UserModel getLoginUser() {
        return (UserModel) SecurityUtils.getSubject().getPrincipal();
    }
}
