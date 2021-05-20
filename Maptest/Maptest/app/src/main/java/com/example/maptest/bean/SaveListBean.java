package com.example.maptest.bean;

import java.io.Serializable;
import java.util.List;

public class SaveListBean implements Serializable {


    /**
     * endRow : 13
     * hasNextPage : false
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : true
     * list : [{"exhId":330310001,"exhInfo":"展览地点：新猷资料馆\r\n                        展览时间：2011年11月25日 - 2023年12月31日\r\n                                 策展部门：陈列保管部联系方式：87032259      新猷资料馆是以蚕桑丝绸界老前辈朱新予和蒋猷龙两位先生命名的纺织信息中心，展示现代纺织面料样本、珍贵纺织人物档案和蚕桑丝绸史、染织服装史、纺织考古、丝绸之路相关的有历史价值的中外报刊书籍、音像资料，同时利用现代技术记录、整理，为纺织科研提供图情信息服务。","exhName":"新猷资料馆","exhPicture":"https://www.chinasilkmuseum.com//uploadfiles/2020/02/20200226165200382.jpg","exhTime":"展览时间：2011-11-25","musId":3303,"musName":"中国丝绸博物馆"},{"exhId":330310002,"exhInfo":"展览地点：纺织品文物修复展示馆\r\n                        展览时间：2011年11月25日 - 2023年12月31日\r\n                                 纺织品文物修复展示馆是国家文化遗产保护科技区域创新联盟（浙江省）示范应用基地。修复展示馆一楼用于纺织品的信息提取、修复、研究等工作，并有一部分区域向观众展示文物保护修复的全过程；二楼向观众展示修复之后的纺织品文物。","exhName":"纺织品文物修复展示馆","exhPicture":"https://www.chinasilkmuseum.com//uploadfiles/2020/02/20200226165012882.jpg","exhTime":"展览时间：2011-11-25","musId":3303,"musName":"中国丝绸博物馆"},{"exhId":330310003,"exhInfo":"展览地点：网上展览\r\n                        展览时间：2020年5月26日 - 2023年5月26日\r\n                                 中国丝绸已经走过了五千多年的历程，为中华文明写下了灿烂的一页，中国丝绸与丝绸之路为世界文明和文化交流贡献了辉煌的篇章。展览（共30块图版）内容包括天蚕灵机、丝绸之路、丝路之绸、丝绸之路上的文明交流、丝绸之路研究与纺织品科技保护等五个篇章，精炼地涵盖了中国丝绸已经走过了五千多年的历程，为中华文明写下了灿烂的一页，中国丝绸与丝绸之路为世界文明和文化交流贡献了辉煌的篇章。展览（共30块图版）内容包括天蚕灵机、丝绸之路、丝路之绸、丝绸之路上的文明交流、丝绸之路研究与纺织品科技保护等五个篇章，精炼地涵盖了丝绸起源、丝绸生产技艺、丝绸之路、丝路出土纺织品、东西方文明交流等各个方面的内容。《丝绸之路与丝路之绸》高清素材本展览提供高清图版，供大家自行办展。如有需要，可下载、填写页底申请表，并电话联络申请：0571-87035150中国丝绸博物馆高清图版素材使用申请表.docx","exhName":"丝绸之路与丝路之绸图片展","exhPicture":"https://www.chinasilkmuseum.com//uploadfiles/2020/05/20200526100017109.jpg","exhTime":"展览时间：2020-05-26","musId":3303,"musName":"中国丝绸博物馆"}]
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable {
        /**
         * exhId : 330310001
         * exhInfo : 展览地点：新猷资料馆
         展览时间：2011年11月25日 - 2023年12月31日
         策展部门：陈列保管部联系方式：87032259      新猷资料馆是以蚕桑丝绸界老前辈朱新予和蒋猷龙两位先生命名的纺织信息中心，展示现代纺织面料样本、珍贵纺织人物档案和蚕桑丝绸史、染织服装史、纺织考古、丝绸之路相关的有历史价值的中外报刊书籍、音像资料，同时利用现代技术记录、整理，为纺织科研提供图情信息服务。
         * exhName : 新猷资料馆
         * exhPicture : https://www.chinasilkmuseum.com//uploadfiles/2020/02/20200226165200382.jpg
         * exhTime : 展览时间：2011-11-25
         * musId : 3303
         * musName : 中国丝绸博物馆
         */

        private int exhId;
        private String exhInfo;
        private String exhName;
        private String exhPicture;
        private String exhTime;
        private int musId;
        private String musName;

        public int getExhId() {
            return exhId;
        }

        public void setExhId(int exhId) {
            this.exhId = exhId;
        }

        public String getExhInfo() {
            return exhInfo;
        }

        public void setExhInfo(String exhInfo) {
            this.exhInfo = exhInfo;
        }

        public String getExhName() {
            return exhName;
        }

        public void setExhName(String exhName) {
            this.exhName = exhName;
        }

        public String getExhPicture() {
            return exhPicture;
        }

        public void setExhPicture(String exhPicture) {
            this.exhPicture = exhPicture;
        }

        public String getExhTime() {
            return exhTime;
        }

        public void setExhTime(String exhTime) {
            this.exhTime = exhTime;
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
