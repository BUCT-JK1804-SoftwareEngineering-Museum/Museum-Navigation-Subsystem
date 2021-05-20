package com.imp.model;

import java.io.Serializable;

/**
 * Created by i_it on 2021/05/16.
 * 藏品表 Model
 */
public class CollectionModel implements Serializable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long colId;

    /**
     * 博物馆ID 
     */
    private Long musId;

    /**
     * 藏品名称
     */
    private String colName;

    /**
     * 年代
     */
    private String colEra;

    /**
     * 博物馆名称
     */
    private String musName;

    /**
     * 藏品图片
     */
    private String colPicture;

    /**
     * 基本介绍
     */
    private String colInfo;

    public Long getColId() {
        return colId;
    }

    public void setColId(Long colId) {
        this.colId = colId;
    }

    public Long getMusId() {
        return musId;
    }

    public void setMusId(Long musId) {
        this.musId = musId;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName == null ? null : colName.trim();
    }

    public String getColEra() {
        return colEra;
    }

    public void setColEra(String colEra) {
        this.colEra = colEra == null ? null : colEra.trim();
    }

    public String getMusName() {
        return musName;
    }

    public void setMusName(String musName) {
        this.musName = musName == null ? null : musName.trim();
    }

    public String getColPicture() {
        return colPicture;
    }

    public void setColPicture(String colPicture) {
        this.colPicture = colPicture == null ? null : colPicture.trim();
    }

    public String getColInfo() {
        return colInfo;
    }

    public void setColInfo(String colInfo) {
        this.colInfo = colInfo == null ? null : colInfo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", colId=").append(colId);
        sb.append(", musId=").append(musId);
        sb.append(", colName=").append(colName);
        sb.append(", colEra=").append(colEra);
        sb.append(", musName=").append(musName);
        sb.append(", colPicture=").append(colPicture);
        sb.append(", colInfo=").append(colInfo);
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
        CollectionModel other = (CollectionModel) that;
        return (this.getColId() == null ? other.getColId() == null : this.getColId().equals(other.getColId()))
            && (this.getMusId() == null ? other.getMusId() == null : this.getMusId().equals(other.getMusId()))
            && (this.getColName() == null ? other.getColName() == null : this.getColName().equals(other.getColName()))
            && (this.getColEra() == null ? other.getColEra() == null : this.getColEra().equals(other.getColEra()))
            && (this.getMusName() == null ? other.getMusName() == null : this.getMusName().equals(other.getMusName()))
            && (this.getColPicture() == null ? other.getColPicture() == null : this.getColPicture().equals(other.getColPicture()))
            && (this.getColInfo() == null ? other.getColInfo() == null : this.getColInfo().equals(other.getColInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getColId() == null) ? 0 : getColId().hashCode());
        result = prime * result + ((getMusId() == null) ? 0 : getMusId().hashCode());
        result = prime * result + ((getColName() == null) ? 0 : getColName().hashCode());
        result = prime * result + ((getColEra() == null) ? 0 : getColEra().hashCode());
        result = prime * result + ((getMusName() == null) ? 0 : getMusName().hashCode());
        result = prime * result + ((getColPicture() == null) ? 0 : getColPicture().hashCode());
        result = prime * result + ((getColInfo() == null) ? 0 : getColInfo().hashCode());
        return result;
    }
}