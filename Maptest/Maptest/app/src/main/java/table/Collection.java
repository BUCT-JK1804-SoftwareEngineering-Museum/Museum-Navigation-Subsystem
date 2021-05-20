package table;

import org.w3c.dom.Text;

public class Collection {
    private double col_id;//藏品编号
    private int mus_id;//博物馆编号
    private String col_name;//藏品名称
    private String col_era;//年代
    private Text col_info;//基本介绍
    private String mus_name;//博物馆名称
    private String col_picture;//藏品图片

    public double getCol_id() {
        return col_id;
    }

    public int getMus_id() {
        return mus_id;
    }

    public String getCol_name() {
        return col_name;
    }

    public String getCol_era() {
        return col_era;
    }

    public Text getCol_info() {
        return col_info;
    }

    public String getMus_name() {
        return mus_name;
    }

    public String getCol_picture() {
        return col_picture;
    }

    public void setCol_id(double col_id) {
        this.col_id = col_id;
    }

    public void setMus_id(int mus_id) {
        this.mus_id = mus_id;
    }

    public void setCol_name(String col_name) {
        this.col_name = col_name;
    }

    public void setCol_era(String col_era) {
        this.col_era = col_era;
    }

    public void setCol_info(Text col_info) {
        this.col_info = col_info;
    }

    public void setMus_name(String mus_name) {
        this.mus_name = mus_name;
    }

    public void setCol_picture(String col_picture) {
        this.col_picture = col_picture;
    }
}

