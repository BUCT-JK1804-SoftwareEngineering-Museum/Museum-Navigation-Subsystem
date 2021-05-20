package com.example.maptest.bean;

import java.io.Serializable;

public class UpVideoBean implements Serializable {

    /**
     * data : {"musId":3303,"musName":"中国丝绸博物馆","userId":92897403133,"vidAddr":"audio/1621300465339-0c72e8c3794a4380ae3e95ef6cb561a8.aac","vidInfo":"敏明您","vidName":"210518091107.aac","vidStatus":1,"vidTime":"2021-05-18 09:14:25"}
     * success : true
     */

    private DataBean data;
    private String message;
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

    public static class DataBean implements Serializable{
        /**
         * musId : 3303
         * musName : 中国丝绸博物馆
         * userId : 92897403133
         * vidAddr : audio/1621300465339-0c72e8c3794a4380ae3e95ef6cb561a8.aac
         * vidInfo : 敏明您
         * vidName : 210518091107.aac
         * vidStatus : 1
         * vidTime : 2021-05-18 09:14:25
         */

        private int musId;
        private String musName;
        private long userId;
        private String vidAddr;
        private String vidInfo;
        private String vidName;
        private int vidStatus;
        private String vidTime;

        public int getMusId() {
            return musId;
        }

        public void setMusId(int musId) {
            this.musId = musId;
        }

        public String getMusName() {
            return musName;
        }

        public void setMusName(String musName) {
            this.musName = musName;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getVidAddr() {
            return vidAddr;
        }

        public void setVidAddr(String vidAddr) {
            this.vidAddr = vidAddr;
        }

        public String getVidInfo() {
            return vidInfo;
        }

        public void setVidInfo(String vidInfo) {
            this.vidInfo = vidInfo;
        }

        public String getVidName() {
            return vidName;
        }

        public void setVidName(String vidName) {
            this.vidName = vidName;
        }

        public int getVidStatus() {
            return vidStatus;
        }

        public void setVidStatus(int vidStatus) {
            this.vidStatus = vidStatus;
        }

        public String getVidTime() {
            return vidTime;
        }

        public void setVidTime(String vidTime) {
            this.vidTime = vidTime;
        }
    }
}
