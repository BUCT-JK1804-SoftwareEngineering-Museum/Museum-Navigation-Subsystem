package com.example.maptest.bean;

import java.io.Serializable;
import java.util.List;

public class MusenumDetailBean implements Serializable {

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

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
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

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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
         * exaId : 1
         * exaTime : 2021-05-18 00:01:00
         * musId : 3302
         * musName : 浙江自然博物馆
         * userId : 92801898312
         * vidAddr : audio/1109.mp9
         * vidId : 92801898055
         * vidInfo : 测试音频
         * vidName : 1115 中国人民抗日战争纪念馆.mp3
         * vidStatus : 3
         * vidTime : 2021-05-18 00:01:00
         */

        private int exaId;
        private String exaTime;
        private int musId;
        private String musName;
        private long userId;
        private String vidAddr;
        private long vidId;
        private String vidInfo;
        private String vidName;
        private int vidStatus;
        private String vidTime;

        public int getExaId() {
            return exaId;
        }

        public void setExaId(int exaId) {
            this.exaId = exaId;
        }

        public String getExaTime() {
            return exaTime;
        }

        public void setExaTime(String exaTime) {
            this.exaTime = exaTime;
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

        public long getVidId() {
            return vidId;
        }

        public void setVidId(long vidId) {
            this.vidId = vidId;
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
