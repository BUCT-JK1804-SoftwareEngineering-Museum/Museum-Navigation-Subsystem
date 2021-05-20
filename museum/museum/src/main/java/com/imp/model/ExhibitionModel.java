package com.imp.model;

import java.io.Serializable;

/**
 * Created by i_it on 2021/05/16.
 * 展品信息表 Model
 */
public class ExhibitionModel implements Serializable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long exhId;

    /**
     * 展品名称
     */
    private String exhName;

    /**
     * 博物馆ID
     */
    private Long musId;

    /**
     * 博物馆名称
     */
    private String musName;

    /**
     * 照片
     */
    private String exhPicture;

    /**
     * 展览时间
     */
    private String exhTime;

    /**
     * 展品信息
     */
    private String exhInfo;

    public Long getExhId() {
        return exhId;
    }

    public void setExhId(Long exhId) {
        this.exhId = exhId;
    }

    public String getExhName() {
        return exhName;
    }

    public void setExhName(String exhName) {
        this.exhName = exhName == null ? null : exhName.trim();
    }

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

    public String getExhPicture() {
        return exhPicture;
    }

    public void setExhPicture(String exhPicture) {
        this.exhPicture = exhPicture == null ? null : exhPicture.trim();
    }

    public String getExhTime() {
        return exhTime;
    }

    public void setExhTime(String exhTime) {
        this.exhTime = exhTime == null ? null : exhTime.trim();
    }

    public String getExhInfo() {
        return exhInfo;
    }

    public void setExhInfo(String exhInfo) {
        this.exhInfo = exhInfo == null ? null : exhInfo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", exhId=").append(exhId);
        sb.append(", exhName=").append(exhName);
        sb.append(", musId=").append(musId);
        sb.append(", musName=").append(musName);
        sb.append(", exhPicture=").append(exhPicture);
        sb.append(", exhTime=").append(exhTime);
        sb.append(", exhInfo=").append(exhInfo);
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
        ExhibitionModel other = (ExhibitionModel) that;
        return (this.getExhId() == null ? other.getExhId() == null : this.getExhId().equals(other.getExhId()))
            && (this.getExhName() == null ? other.getExhName() == null : this.getExhName().equals(other.getExhName()))
            && (this.getMusId() == null ? other.getMusId() == null : this.getMusId().equals(other.getMusId()))
            && (this.getMusName() == null ? other.getMusName() == null : this.getMusName().equals(other.getMusName()))
            && (this.getExhPicture() == null ? other.getExhPicture() == null : this.getExhPicture().equals(other.getExhPicture()))
            && (this.getExhTime() == null ? other.getExhTime() == null : this.getExhTime().equals(other.getExhTime()))
            && (this.getExhInfo() == null ? other.getExhInfo() == null : this.getExhInfo().equals(other.getExhInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getExhId() == null) ? 0 : getExhId().hashCode());
        result = prime * result + ((getExhName() == null) ? 0 : getExhName().hashCode());
        result = prime * result + ((getMusId() == null) ? 0 : getMusId().hashCode());
        result = prime * result + ((getMusName() == null) ? 0 : getMusName().hashCode());
        result = prime * result + ((getExhPicture() == null) ? 0 : getExhPicture().hashCode());
        result = prime * result + ((getExhTime() == null) ? 0 : getExhTime().hashCode());
        result = prime * result + ((getExhInfo() == null) ? 0 : getExhInfo().hashCode());
        return result;
    }
}