package com.imp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by i_it on 2021/05/16.
 * 讲解表 Model
 */
public class VideoModel implements Serializable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long vidId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 博物馆ID
     */
    private Long musId;

    /**
     * 视频名
     */
    private String vidName;

    /**
     * 视频储存位置
     */
    private String vidAddr;

    /**
     * 视频介绍
     */
    private String vidInfo;

    /**
     * 审核状态1：未审核；2：审核未通过；3：审核通过
     */
    private Integer vidStatus;

    /**
     * 审核人
     */
    private Long exaId;

    /**
     * 审核时间
     */
    private Date exaTime;

    /**
     * 上传时间
     */
    private Date vidTime;

    /**
     * 博物馆名称
     */
    private String musName;

    public Long getVidId() {
        return vidId;
    }

    public void setVidId(Long vidId) {
        this.vidId = vidId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMusId() {
        return musId;
    }

    public void setMusId(Long musId) {
        this.musId = musId;
    }

    public String getVidName() {
        return vidName;
    }

    public void setVidName(String vidName) {
        this.vidName = vidName == null ? null : vidName.trim();
    }

    public String getVidAddr() {
        return vidAddr;
    }

    public void setVidAddr(String vidAddr) {
        this.vidAddr = vidAddr == null ? null : vidAddr.trim();
    }

    public String getVidInfo() {
        return vidInfo;
    }

    public void setVidInfo(String vidInfo) {
        this.vidInfo = vidInfo == null ? null : vidInfo.trim();
    }

    public Integer getVidStatus() {
        return vidStatus;
    }

    public void setVidStatus(Integer vidStatus) {
        this.vidStatus = vidStatus;
    }

    public Long getExaId() {
        return exaId;
    }

    public void setExaId(Long exaId) {
        this.exaId = exaId;
    }

    public Date getExaTime() {
        return exaTime;
    }

    public void setExaTime(Date exaTime) {
        this.exaTime = exaTime;
    }

    public Date getVidTime() {
        return vidTime;
    }

    public void setVidTime(Date vidTime) {
        this.vidTime = vidTime;
    }

    public String getMusName() {
        return musName;
    }

    public void setMusName(String musName) {
        this.musName = musName == null ? null : musName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", vidId=").append(vidId);
        sb.append(", userId=").append(userId);
        sb.append(", musId=").append(musId);
        sb.append(", vidName=").append(vidName);
        sb.append(", vidAddr=").append(vidAddr);
        sb.append(", vidInfo=").append(vidInfo);
        sb.append(", vidStatus=").append(vidStatus);
        sb.append(", exaId=").append(exaId);
        sb.append(", exaTime=").append(exaTime);
        sb.append(", vidTime=").append(vidTime);
        sb.append(", musName=").append(musName);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        VideoModel other = (VideoModel) that;
        return (this.getVidId() == null ? other.getVidId() == null : this.getVidId().equals(other.getVidId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMusId() == null ? other.getMusId() == null : this.getMusId().equals(other.getMusId()))
            && (this.getVidName() == null ? other.getVidName() == null : this.getVidName().equals(other.getVidName()))
            && (this.getVidAddr() == null ? other.getVidAddr() == null : this.getVidAddr().equals(other.getVidAddr()))
            && (this.getVidInfo() == null ? other.getVidInfo() == null : this.getVidInfo().equals(other.getVidInfo()))
            && (this.getVidStatus() == null ? other.getVidStatus() == null : this.getVidStatus().equals(other.getVidStatus()))
            && (this.getExaId() == null ? other.getExaId() == null : this.getExaId().equals(other.getExaId()))
            && (this.getExaTime() == null ? other.getExaTime() == null : this.getExaTime().equals(other.getExaTime()))
            && (this.getVidTime() == null ? other.getVidTime() == null : this.getVidTime().equals(other.getVidTime()))
            && (this.getMusName() == null ? other.getMusName() == null : this.getMusName().equals(other.getMusName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVidId() == null) ? 0 : getVidId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMusId() == null) ? 0 : getMusId().hashCode());
        result = prime * result + ((getVidName() == null) ? 0 : getVidName().hashCode());
        result = prime * result + ((getVidAddr() == null) ? 0 : getVidAddr().hashCode());
        result = prime * result + ((getVidInfo() == null) ? 0 : getVidInfo().hashCode());
        result = prime * result + ((getVidStatus() == null) ? 0 : getVidStatus().hashCode());
        result = prime * result + ((getExaId() == null) ? 0 : getExaId().hashCode());
        result = prime * result + ((getExaTime() == null) ? 0 : getExaTime().hashCode());
        result = prime * result + ((getVidTime() == null) ? 0 : getVidTime().hashCode());
        result = prime * result + ((getMusName() == null) ? 0 : getMusName().hashCode());
        return result;
    }
}