package com.example.maptest.bean;

import java.io.Serializable;
import java.util.List;

public class CangBean implements Serializable {

    /**
     * endRow : 35
     * hasNextPage : false
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : true
     * list : [{"colEra":"新石器时代","colId":640210001,"colInfo":"新石器时代直径14.2厘米 好径3.2一3.9厘米 壁厚0.8一1.1厘米宁夏固原张易堡征集壁表面磨光，薄厚均匀，一面有错向割锯折断时留下的台面，台面高低错位，没有经过打磨，留有简单刮刨的崩茬。另一面的据痕则经过仔细打磨，与器表平齐面直钻，好孔呈斜面，壁面钻研时的旋纹清晰。边缘有小疤.通体青绿边缘沁白。","colName":"玉璧","colPicture":"http://www.nxbwg.com/uploads/thumbs/17231e76a8faa8d92bd4f46cbf5650fa_thumb.jpg","musId":6402,"musName":"宁夏博物馆"},{"colId":640210002,"colInfo":"新石器时代高24.7厘米，口径13.5厘米，腹径23厘米，底径8.4厘米。宁复海原县菜园遗址出土菜园文化遗址出土陶器数最极多，分为炊器、水器、储藏器和食器，制作技术简洁而统一，普遍使用泥条盘筑法，常见的纹饰为篮纹，多为夹砂陶，在总体上显示出强烈的地域特色.此陶罐侈口，束颈，下腹斜内收，平底，最大腹径在器体中部.器壁饰浅斜篮纹，颈下饰一周波状附加堆纹，肩部均匀贴饰三个\u201cx\u201d纹.器物简洁实用，为菜园文化的典型器物。","colName":"篮纹红陶罐","colPicture":"http://www.nxbwg.com/uploads/thumbs/f0d0ee44ffda17b304b1a0f4e02036f4_thumb.jpg","musId":6402,"musName":"宁夏博物馆"},{"colEra":"玉琮","colId":640210003,"colInfo":"玉琮新石器时代通高4.8厘米 宽5.5厘米 射高0.8一1厘米 孔径4.6厘米宁夏固原张易堡征集方柱体，四周稍弧，射圈圆整，射圈与方柱角相接处呈弧形。方柱面基本呈正方形，柱体高不及边长，琮孔双面对钻，中间稍窄，有钻研痕迹.通体呈黄绿色。","colName":"玉琮","colPicture":"http://www.nxbwg.com/uploads/thumbs/71a4ba188523ddce61978f9d78bf74fe_thumb.jpg","musId":6402,"musName":"宁夏博物馆"}]
     */

    private int endRow;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private List<ListBean> list;

    public int getEndRow() {
        return endRow;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable {
        /**
         * colEra : 新石器时代
         * colId : 640210001
         * colInfo : 新石器时代直径14.2厘米 好径3.2一3.9厘米 壁厚0.8一1.1厘米宁夏固原张易堡征集壁表面磨光，薄厚均匀，一面有错向割锯折断时留下的台面，台面高低错位，没有经过打磨，留有简单刮刨的崩茬。另一面的据痕则经过仔细打磨，与器表平齐面直钻，好孔呈斜面，壁面钻研时的旋纹清晰。边缘有小疤.通体青绿边缘沁白。
         * colName : 玉璧
         * colPicture : http://www.nxbwg.com/uploads/thumbs/17231e76a8faa8d92bd4f46cbf5650fa_thumb.jpg
         * musId : 6402
         * musName : 宁夏博物馆
         */

        private String colEra;
        private int colId;
        private String colInfo;
        private String colName;
        private String colPicture;
        private int musId;
        private String musName;

        public void setColId(int colId) {
            this.colId = colId;
        }

        public String getColInfo() {
            return colInfo;
        }

        public String getColName() {
            return colName;
        }

        public void setColName(String colName) {
            this.colName = colName;
        }

        public String getColPicture() {
            return colPicture;
        }

        public void setColPicture(String colPicture) {
            this.colPicture = colPicture;
        }

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
    }
}
