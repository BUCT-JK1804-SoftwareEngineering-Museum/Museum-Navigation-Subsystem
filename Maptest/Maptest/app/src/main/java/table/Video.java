package table;

import org.w3c.dom.Text;

import java.lang.ref.PhantomReference;

public class Video {
    private int vid_id;//视频编号
    private int user_id;//用户编号
    private int mus_id;//博物馆编号
    private String vid_name;//视频名
    private String vid_addr;//视频储存位置
    private Text vid_info;//视频介绍
    private int vid_status;//审核状态
    private int exa_id;//审核人
    private String Exa_time;//审核时间
    private String vid_time;//上传时间
    private String mus_name;//博物馆名称

    public int getVid_id() {
        return vid_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getMus_id() {
        return mus_id;
    }

    public String getVid_name() {
        return vid_name;
    }

    public String getVid_addr() {
        return vid_addr;
    }

    public Text getVid_info() {
        return vid_info;
    }

    public int getVid_status() {
        return vid_status;
    }

    public int getExa_id() {
        return exa_id;
    }

    public String getExa_time() {
        return Exa_time;
    }

    public String getVid_time() {
        return vid_time;
    }

    public String getMus_name() {
        return mus_name;
    }

    public void setVid_id(int vid_id) {
        this.vid_id = vid_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setMus_id(int mus_id) {
        this.mus_id = mus_id;
    }

    public void setVid_name(String vid_name) {
        this.vid_name = vid_name;
    }

    public void setVid_addr(String vid_addr) {
        this.vid_addr = vid_addr;
    }

    public void setVid_info(Text vid_info) {
        this.vid_info = vid_info;
    }

    public void setVid_status(int vid_status) {
        this.vid_status = vid_status;
    }

    public void setExa_id(int exa_id) {
        this.exa_id = exa_id;
    }

    public void setExa_time(String exa_time) {
        Exa_time = exa_time;
    }

    public void setVid_time(String vid_time) {
        this.vid_time = vid_time;
    }

    public void setMus_name(String mus_name) {
        this.mus_name = mus_name;
    }
}
