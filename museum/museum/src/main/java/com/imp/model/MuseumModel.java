package com.imp.model;

import java.io.Serializable;

/**
 * Created by i_it on 2021/05/16.
 * 博物馆表 Model
 */
public class MuseumModel implements Serializable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long musId;

    /**
     * 博物馆名称
     */
    private String musName;

    /**
     * 图片
     */
    private String musPicture;

    /**
     * 等级
     */
    private Double musGrade;

    /**
     * 开放时间
     */
    private String musTime;

    /**
     * 电话
     */
    private String musPhone;

    /**
     * 馆长
     */
    private String musMaster;

    /**
     * 地址
     */
    private String musAddress;

    /**
     * 网址
     */
    private String musRemark;

    public Long getMusId() {
        return musId;
    }

    public void setMusId(Long musId) {
        this.musId = musId;
    }

    public String getMusName() {
        return musName;
    }

    public void setMusName(String musName) {
        this.musName = musName == null ? null : musName.trim();
    }

    public String getMusPicture() {
        return musPicture;
    }

    public void setMusPicture(String musPicture) {
        this.musPicture = musPicture == null ? null : musPicture.trim();
    }

    public Double getMusGrade() {
        return musGrade;
    }

    public void setMusGrade(Double musGrade) {
        this.musGrade = musGrade;
    }

    public String getMusTime() {
        return musTime;
    }

    public void setMusTime(String musTime) {
        this.musTime = musTime == null ? null : musTime.trim();
    }

    public String getMusPhone() {
        return musPhone;
    }

    public void setMusPhone(String musPhone) {
        this.musPhone = musPhone == null ? null : musPhone.trim();
    }

    public String getMusMaster() {
        return musMaster;
    }

    public void setMusMaster(String musMaster) {
        this.musMaster = musMaster == null ? null : musMaster.trim();
    }

    public String getMusAddress() {
        return musAddress;
    }

    public void setMusAddress(String musAddress) {
        this.musAddress = musAddress == null ? null : musAddress.trim();
    }

    public String getMusRemark() {
        return musRemark;
    }

    public void setMusRemark(String musRemark) {
        this.musRemark = musRemark == null ? null : musRemark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", musId=").append(musId);
        sb.append(", musName=").append(musName);
        sb.append(", musPicture=").append(musPicture);
        sb.append(", musGrade=").append(musGrade);
        sb.append(", musTime=").append(musTime);
        sb.append(", musPhone=").append(musPhone);
        sb.append(", musMaster=").append(musMaster);
        sb.append(", musAddress=").append(musAddress);
        sb.append(", musRemark=").append(musRemark);
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
        MuseumModel other = (MuseumModel) that;
        return (this.getMusId() == null ? other.getMusId() == null : this.getMusId().equals(other.getMusId()))
            && (this.getMusName() == null ? other.getMusName() == null : this.getMusName().equals(other.getMusName()))
            && (this.getMusPicture() == null ? other.getMusPicture() == null : this.getMusPicture().equals(other.getMusPicture()))
            && (this.getMusGrade() == null ? other.getMusGrade() == null : this.getMusGrade().equals(other.getMusGrade()))
            && (this.getMusTime() == null ? other.getMusTime() == null : this.getMusTime().equals(other.getMusTime()))
            && (this.getMusPhone() == null ? other.getMusPhone() == null : this.getMusPhone().equals(other.getMusPhone()))
            && (this.getMusMaster() == null ? other.getMusMaster() == null : this.getMusMaster().equals(other.getMusMaster()))
            && (this.getMusAddress() == null ? other.getMusAddress() == null : this.getMusAddress().equals(other.getMusAddress()))
            && (this.getMusRemark() == null ? other.getMusRemark() == null : this.getMusRemark().equals(other.getMusRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMusId() == null) ? 0 : getMusId().hashCode());
        result = prime * result + ((getMusName() == null) ? 0 : getMusName().hashCode());
        result = prime * result + ((getMusPicture() == null) ? 0 : getMusPicture().hashCode());
        result = prime * result + ((getMusGrade() == null) ? 0 : getMusGrade().hashCode());
        result = prime * result + ((getMusTime() == null) ? 0 : getMusTime().hashCode());
        result = prime * result + ((getMusPhone() == null) ? 0 : getMusPhone().hashCode());
        result = prime * result + ((getMusMaster() == null) ? 0 : getMusMaster().hashCode());
        result = prime * result + ((getMusAddress() == null) ? 0 : getMusAddress().hashCode());
        result = prime * result + ((getMusRemark() == null) ? 0 : getMusRemark().hashCode());
        return result;
    }
}