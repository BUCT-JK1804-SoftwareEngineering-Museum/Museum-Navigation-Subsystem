package table;

import org.w3c.dom.Text;

public class Exhibition {
    private int exh_id;//展览编号
    private int mus_id;//博物馆编号
    private String exh_name;//展览名称
    private Text exh_info;//展览内容
    private String mus_name;//博物馆名称
    private String exh_picture;//展览图片
    private String exh_time;//展览时间

    public int getExh_id() {
        return exh_id;
    }

    public int getMus_id() {
        return mus_id;
    }

    public String getExh_name() {
        return exh_name;
    }

    public Text getExh_info() {
        return exh_info;
    }

    public String getMus_name() {
        return mus_name;
    }

    public String getExh_picture() {
        return exh_picture;
    }

    public String getExh_time() {
        return exh_time;
    }

    public void setExh_id(int exh_id) {
        this.exh_id = exh_id;
    }

    public void setMus_id(int mus_id) {
        this.mus_id = mus_id;
    }

    public void setExh_name(String exh_name) {
        this.exh_name = exh_name;
    }

    public void setExh_info(Text exh_info) {
        this.exh_info = exh_info;
    }
    public void setMus_name(String mus_name) {
        this.mus_name = mus_name;
    }
    public void setExh_picture(String exh_picture) {
        this.exh_picture = exh_picture;
    }
    public void setExh_time(String exh_time) {
        this.exh_time = exh_time;
    }
}

