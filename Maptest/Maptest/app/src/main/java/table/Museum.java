package table;

import org.w3c.dom.Text;

public class Museum {
    private int mus_id;//博物馆编号
    private String mus_name;//博物馆名称
    private String mus_picture;//博物馆图片
    private double mus_grade;//博物馆评分
    private String mus_time;//博物馆开放时间
    private Text mus_address;//博物馆地址
    private Text mus_remark;//博物馆其他信息
    private char[] mus_phone;//博物馆电话
    private String mus_master;//博物馆长

    public int getMus_id() {
        return mus_id;
    }

    public String getMus_name() {
        return mus_name;
    }

    public String getMus_picture() {
        return mus_picture;
    }

    public double getMus_grade() {
        return mus_grade;
    }

    public String getMus_time() {
        return mus_time;
    }

    public Text getMus_address() {
        return mus_address;
    }

    public Text getMus_remark() {
        return mus_remark;
    }

    public char[] getMus_phone() {
        return mus_phone;
    }

    public String getMus_master() {
        return mus_master;
    }

    public void setMus_id(int mus_id) {
        this.mus_id = mus_id;
    }

    public void setMus_name(String mus_name) {
        this.mus_name = mus_name;
    }

    public void setMus_picture(String mus_picture) {
        this.mus_picture = mus_picture;
    }

    public void setMus_grade(double mus_grade) {
        this.mus_grade = mus_grade;
    }

    public void setMus_time(String mus_time) {
        this.mus_time = mus_time;
    }

    public void setMus_address(Text mus_address) {
        this.mus_address = mus_address;
    }

    public void setMus_remark(Text mus_remark) {
        this.mus_remark = mus_remark;
    }

    public void setMus_phone(char[] mus_phone) {
        this.mus_phone = mus_phone;
    }

    public void setMus_master(String mus_master) {
        this.mus_master = mus_master;
    }
}
