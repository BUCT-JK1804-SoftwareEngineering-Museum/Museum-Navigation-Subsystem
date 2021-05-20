package com.example.maptest.bean;

import java.io.Serializable;
import java.util.List;

public class MuseumListAllBean implements Serializable {

    /**
     * endRow : 132
     * hasNextPage : false
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : true
     * list : [{"musAddress":"北京市东城区景山前街4号","musGrade":5,"musId":1101,"musMaster":"王旭东","musName":"故宫博物院","musPhone":"010-85007062","musPicture":"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fzkres1.myzaker.com%2F201901%2F5c2ffa08622768bcce00d238_960.jpg&refer=http%3A%2F%2Fzkres1.myzaker.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1623460821&t=a0cb50ea15d88c38231bb7ee9b909345","musRemark":"https://www.dpm.org.cn/","musTime":"8:30-17:00"},{"musAddress":"北京市朝阳区北辰东路5号","musGrade":5,"musId":1102,"musLat":40.012384,"musLng":116.40504,"musMaster":"束为","musName":"中国科学技术馆","musPhone":"010-59041000","musPicture":"https://cstm.cdstm.cn/bgs/kjghk/202006/W020200611517311362678.jpg","musRemark":"https://cstm.cdstm.cn/","musTime":"9:30-17:00"},{"musAddress":"北京市西城区西四羊肉胡同15号","musGrade":5,"musId":1103,"musMaster":"高振西","musName":"中国地址博物馆","musPhone":"010-66557858","musPicture":"http://www.gmc.org.cn/Uploads/Picture/2019/04/24/s5cc027cd7e431.jpg","musRemark":"http://www.gmc.org.cn/","musTime":"8：30-16：30"},{"musAddress":"北京市复兴路9号","musGrade":5,"musId":1104,"musLat":39.914976,"musLng":116.330207,"musMaster":"董长军","musName":"中国人民革命军事博物馆","musPhone":"010-66866244","musPicture":"http://www.jb.mil.cn/sydt/202010/W020201022312701681274.jpg","musRemark":"http://www.jb.mil.cn/","musTime":"9:00-17:00（16:00停止入馆）。周一例行闭馆（法定节假日除外）"},{"musAddress":"北京市丰台南大红门1号","musGrade":5,"musId":1105,"musMaster":"陈青","musName":"中国航天博物馆","musPhone":"010-68384455","musPicture":"http://www.casc-spacemuseum.com/images/a_pic.jpg","musRemark":"http://www.casc-spacemuseum.com/","musTime":"9:30-10:30 13:30-14：30"},{"musAddress":"北京市西城区阜成门内大街宫门口二条19号","musGrade":5,"musId":1106,"musLat":39.931656,"musLng":116.365314,"musMaster":"孙毅","musName":"北京鲁迅博物馆","musPhone":"010-66128596","musPicture":"http://www.luxunmuseum.com.cn/uploads/allimg/150813/1-150Q3161J0212.png","musRemark":"http://www.luxunmuseum.com.cn","musTime":"09:00-16:50"},{"musAddress":"北京市西城区复兴门外大街16号","musGrade":5,"musId":1107,"musLat":39.912174,"musLng":116.348822,"musMaster":"韩战明","musName":"首都博物馆","musPhone":"010-63370491","musPicture":"http://www.capitalmuseum.org.cn/zjsb/content/images/attachement/jpg/site2/2006-04-12/4428677284865130573.JPG","musRemark":"http://www.capitalmuseum.org.cn/","musTime":"9:00\u201417:00"},{"musAddress":"中国北京市东城区天桥南大街","musGrade":5,"musId":1108,"musMaster":"孟庆金","musName":"北京自博物馆","musPhone":"010-67024431","musPicture":"https://baike.baidu.com/pic/%E5%8C%97%E4%BA%AC%E8%87%AA%E7%84%B6%E5%8D%9A%E7%89%A9%E9%A6%86/2002464/1/f7246b600c338744ebf82298a844cef9d72a61590a89?fr=lemma&ct=single#aid=1&pic=f7246b600c338744ebf82298a844cef9d72a61590a89","musRemark":"http://www.bmnh.org.cn/","musTime":"周二至周日9:00\u201417:00"},{"musAddress":"北京市丰台区卢沟桥宛平城内街101号","musGrade":5,"musId":1109,"musLat":39.857753,"musLng":116.232474,"musMaster":"李宗远","musName":"中国人民抗日战争纪念馆","musPhone":"010-63777088","musPicture":"http://www.1937china.com/kzjng/views/bgjs/picture/bgdsj.jpg","musRemark":"http://www.1937china.com/","musTime":"9:00-16:30"},{"musAddress":"北京西直门外大街138号","musGrade":5,"musId":1110,"musLat":39.943273,"musLng":116.343208,"musMaster":"朱进","musName":"北京天文馆","musPhone":"(010)51583311","musPicture":"https://baike.baidu.com/pic/%E5%8C%97%E4%BA%AC%E5%A4%A9%E6%96%87%E9%A6%86/1632709/1/1b4c510fd9f9d72af4ad90e5d22a2834359bbb79?fr=lemma&ct=single#aid=1&pic=1b4c510fd9f9d72af4ad90e5d22a2834359bbb79","musRemark":"http://www.bjp.org.cn/","musTime":"9:00\u201417:00"}]
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

    public void setEndRow(int endRow) {
        this.endRow = endRow;
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

    public static class ListBean {
        /**
         * musAddress : 北京市东城区景山前街4号
         * musGrade : 5.0
         * musId : 1101
         * musMaster : 王旭东
         * musName : 故宫博物院
         * musPhone : 010-85007062
         * musPicture : https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fzkres1.myzaker.com%2F201901%2F5c2ffa08622768bcce00d238_960.jpg&refer=http%3A%2F%2Fzkres1.myzaker.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1623460821&t=a0cb50ea15d88c38231bb7ee9b909345
         * musRemark : https://www.dpm.org.cn/
         * musTime : 8:30-17:00
         * musLat : 40.012384
         * musLng : 116.40504
         */

        private String musAddress;
        private double musGrade;
        private int musId;
        private String musMaster;
        private String musName;
        private String musPhone;
        private String musPicture;
        private String musRemark;
        private String musTime;
        //private double musLat;
        private double musLng;

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

        public double getMusLng() {
            return musLng;
        }

        public void setMusLng(double musLng) {
            this.musLng = musLng;
        }
    }
}
