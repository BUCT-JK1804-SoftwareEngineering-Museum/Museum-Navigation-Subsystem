package com.example.maptest.bean;

import java.io.Serializable;
import java.util.List;

public class MuseumListBean implements Serializable {


    /**
     * endRow : 0
     * hasNextPage : false
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : true
     * list : [{"musAddress":"浙江省杭州市下城区西湖文化广场6号","musGrade":5,"musId":3302,"musLat":30.282674,"musLng":120.171317,"musMaster":"严洪明","musName":"浙江自然博物馆","musPhone":"0571-88212712","musPicture":"http://www.zmnh.com/uploadFiles/imgs/c993ed6b-8598-4615-995b-e893ca4667a2.jpg","musRemark":"http://www.zmnh.com/","musTime":"9：00-17：00"}]
     * navigateFirstPage : 1
     * navigateLastPage : 1
     * navigatePages : 8
     * navigatepageNums : [1]
     * nextPage : 0
     * pageNum : 1
     * pageSize : 1
     * pages : 1
     * prePage : 0
     * size : 1
     * startRow : 0
     * total : 1
     */

    private int endRow;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int navigateFirstPage;
    private int navigateLastPage;
    private int navigatePages;
    private int nextPage;
    private int pageNum;
    private int pageSize;
    private int pages;
    private int prePage;
    private int size;
    private int startRow;
    private int total;
    private List<ListBean> list;
    private List<Integer> navigatepageNums;

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean implements Serializable{
        /**
         * musAddress : 浙江省杭州市下城区西湖文化广场6号
         * musGrade : 5.0
         * musId : 3302
         * musLat : 30.282674
         * musLng : 120.171317
         * musMaster : 严洪明
         * musName : 浙江自然博物馆
         * musPhone : 0571-88212712
         * musPicture : http://www.zmnh.com/uploadFiles/imgs/c993ed6b-8598-4615-995b-e893ca4667a2.jpg
         * musRemark : http://www.zmnh.com/
         * musTime : 9：00-17：00
         */

        private String musAddress;
        private double musGrade;
        private int musId;
        private double musLat;
        private double musLng;
        private String musMaster;
        private String musName;
        private String musPhone;
        private String musPicture;
        private String musRemark;
        private String musTime;

        public String getMusAddress() {
            return musAddress;
        }

        public void setMusAddress(String musAddress) {
            this.musAddress = musAddress;
        }

        public double getMusGrade() {
            return musGrade;
        }

        public void setMusGrade(double musGrade) {
            this.musGrade = musGrade;
        }

        public int getMusId() {
            return musId;
        }

        public void setMusId(int musId) {
            this.musId = musId;
        }

        public double getMusLat() {
            return musLat;
        }

        public void setMusLat(double musLat) {
            this.musLat = musLat;
        }

        public double getMusLng() {
            return musLng;
        }

        public void setMusLng(double musLng) {
            this.musLng = musLng;
        }

        public String getMusMaster() {
            return musMaster;
        }

        public void setMusMaster(String musMaster) {
            this.musMaster = musMaster;
        }

        public String getMusName() {
            return musName;
        }

        public void setMusName(String musName) {
            this.musName = musName;
        }

        public String getMusPhone() {
            return musPhone;
        }

        public void setMusPhone(String musPhone) {
            this.musPhone = musPhone;
        }

        public String getMusPicture() {
            return musPicture;
        }

        public void setMusPicture(String musPicture) {
            this.musPicture = musPicture;
        }

        public String getMusRemark() {
            return musRemark;
        }

        public void setMusRemark(String musRemark) {
            this.musRemark = musRemark;
        }

        public String getMusTime() {
            return musTime;
        }

        public void setMusTime(String musTime) {
            this.musTime = musTime;
        }
    }
}
