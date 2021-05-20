package com.example.maptest.bean;

import java.io.Serializable;
import java.util.List;

public class AuditstusListBean implements Serializable {


    /**
     * endRow : 9
     * hasNextPage : false
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : true
     * list : [{"exaId":1,"exaTime":"2021-05-18 00:01:00","musId":1101,"musName":"故宫博物院","userId":92897403133,"vidAddr":"audio/1102.mp3","vidId":92801898000,"vidInfo":"测试音频","vidName":"1102 中国科学技术馆.mp3","vidStatus":3,"vidTime":"2021-05-18 00:01:00"},{"exaId":1,"exaTime":"2021-05-18 00:01:00","musId":1102,"musName":"中国科学技术馆","userId":92897403133,"vidAddr":"audio/1102.mp3","vidId":92801898001,"vidInfo":"测试音频","vidName":"1102 中国科学技术馆.mp3","vidStatus":3,"vidTime":"2021-05-18 00:01:00"},{"exaId":1,"exaTime":"2021-05-18 00:01:00","musId":1103,"musName":"中国地址博物馆","userId":92897403133,"vidAddr":"audio/1113.mp3","vidId":92801898002,"vidInfo":"测试音频","vidName":"1113 中国农业博物馆.mp3","vidStatus":3,"vidTime":"2021-05-18 00:01:00"},{"exaId":1,"exaTime":"2021-05-18 00:01:00","musId":1104,"musName":"中国人民革命军事博物馆","userId":92897403133,"vidAddr":"audio/1201.mp3","vidId":92801898003,"vidInfo":"测试音频","vidName":"1201 天津博物馆.mp3","vidStatus":3,"vidTime":"2021-05-18 00:01:00"},{"exaId":1,"exaTime":"2021-05-18 00:01:00","musId":1105,"musName":"中国航天博物馆","userId":92897403133,"vidAddr":"audio/1203.mp3","vidId":92801898004,"vidInfo":"测试音频","vidName":"1203 周恩来邓颖超纪念馆.mp3","vidStatus":3,"vidTime":"2021-05-18 00:01:00"},{"exaId":1,"exaTime":"2021-05-18 00:01:00","musId":1106,"musName":"北京鲁迅博物馆","userId":92897403133,"vidAddr":"audio/2103.mp3","vidId":92801898005,"vidInfo":"测试音频","vidName":"2103 抗美援朝纪念馆.mp3","vidStatus":3,"vidTime":"2021-05-18 00:01:00"},{"exaId":1,"exaTime":"2021-05-18 00:01:00","musId":1107,"musName":"首都博物馆","userId":92897403133,"vidAddr":"audio/3604.mp3","vidId":92801898006,"vidInfo":"测试音频","vidName":"3604 南昌八一起义纪念馆.mp3","vidStatus":3,"vidTime":"2021-05-18 00:01:00"},{"exaId":1,"exaTime":"2021-05-18 00:01:00","musId":1108,"musName":"北京自博物馆","userId":92897403133,"vidAddr":"audio/4104.mp3","vidId":92801898007,"vidInfo":"测试音频","vidName":"4104 南阳汉画馆.mp3","vidStatus":3,"vidTime":"2021-05-18 00:01:00"},{"exaId":1,"exaTime":"2021-05-18 00:01:00","musId":1109,"musName":"中国人民抗日战争纪念馆","userId":92897403133,"vidAddr":"audio/1109.mp3","vidId":92801898008,"vidInfo":"测试音频","vidName":"1109 中国人民抗日战争纪念馆.mp3","vidStatus":3,"vidTime":"2021-05-18 00:01:00"},{"exaId":1,"exaTime":"2021-05-18 00:01:00","musId":1110,"musName":"北京天文馆","userId":92897403133,"vidAddr":"audio/1102.mp3","vidId":92801898009,"vidInfo":"测试音频","vidName":"1103 中国科学技术馆.mp3","vidStatus":3,"vidTime":"2021-05-18 00:01:00"}]
     * navigateFirstPage : 1
     * navigateLastPage : 1
     * navigatePages : 8
     * navigatepageNums : [1]
     * nextPage : 0
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     * prePage : 0
     * size : 10
     * startRow : 0
     * total : 10
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

    public static class ListBean implements Serializable {
        /**
         * exaId : 1
         * exaTime : 2021-05-18 00:01:00
         * musId : 1101
         * musName : 故宫博物院
         * userId : 92897403133
         * vidAddr : audio/1102.mp3
         * vidId : 92801898000
         * vidInfo : 测试音频
         * vidName : 1102 中国科学技术馆.mp3
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

        public void setVidId(long vidId) {
            this.vidId = vidId;
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

        public String getVidTime() {
            return vidTime;
        }

        public void setVidTime(String vidTime) {
            this.vidTime = vidTime;
        }
    }
}
