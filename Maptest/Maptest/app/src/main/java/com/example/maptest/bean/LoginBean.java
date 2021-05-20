package com.example.maptest.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {

    /**
     * success : boolean
     * message : string
     * data : {"userId":"long //主键","userName":"string //用户名","userPassword":"string //密码","userPhone":"string //手机","userPic":"string //头像","userGender":"string //用户性别","userSite":"string //用户位置","userLevel":"int //用户权限等级1级：普通用户；2级：管理员；3级：超级管理员","userPallow":"int //是否允许评论：0不允许。1：允许"}
     */

    private boolean success;
    private String message;
    private DataBean data;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * userId : long //主键
         * userName : string //用户名
         * userPassword : string //密码
         * userPhone : string //手机
         * userPic : string //头像
         * userGender : string //用户性别
         * userSite : string //用户位置
         * userLevel : int //用户权限等级1级：普通用户；2级：管理员；3级：超级管理员
         * userPallow : int //是否允许评论：0不允许。1：允许
         */

        private String userId;
        private String userName;
        private String userPassword;
        private String userPhone;
        private String userPic;
        private String userGender;
        private String userSite;
        private String userLevel;
        private String userPallow;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserPic() {
            return userPic;
        }

        public String getUserGender() {
            return userGender;
        }

        public void setUserGender(String userGender) {
            this.userGender = userGender;
        }

        public String getUserSite() {
            return userSite;
        }

        public void setUserSite(String userSite) {
            this.userSite = userSite;
        }

        public String getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(String userLevel) {
            this.userLevel = userLevel;
        }

        public String getUserPallow() {
            return userPallow;
        }

        public void setUserPallow(String userPallow) {
            this.userPallow = userPallow;
        }
    }
}
