package com.example.maptest.bean;

import java.io.Serializable;

public class RegisterBean implements Serializable {


    /**
     * data : {"userId":92802353876,"userLevel":1,"userName":"李3","userPallow":1,"userPassword":"74e6524829ab29e9f5e76e1d02fc8f11","userPhone":"12345678901"}
     * success : true
     */
    private String message;
    private DataBean data;
    private boolean success;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean implements Serializable {
        /**
         * userId : 92802353876
         * userLevel : 1
         * userName : 李3
         * userPallow : 1
         * userPassword : 74e6524829ab29e9f5e76e1d02fc8f11
         * userPhone : 12345678901
         */

        private long userId;
        private String userName;
        private String userPhone;
        private String userPic;
        private String userGender;
        private String userSite;
        private int userLevel;
        private int userPallow;
        private String userPassword;

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
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

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserPallow() {
            return userPallow;
        }

        public void setUserPallow(int userPallow) {
            this.userPallow = userPallow;
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
    }
}
