package table;

import org.w3c.dom.Text;

public class User {
    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_phone;
    private String user_pic;
    private String user_gender;
    private Text user_site;
    private int user_level;
    private int user_pallow;
    public Integer getUser_id(){
        return user_id;
    }
    public String getUser_name() {
        return user_name;
    }
    public String getUser_password() {
        return user_password;
    }
    public String getUser_phone() {
        return user_phone;
    }
    public String getUser_pic() {
        return user_pic;
    }
    public String getUser_gender() {
        return user_gender;
    }
    public Text getUser_site() {
        return user_site;
    }
    public int getUser_level() {
        return user_level;
    }
    public int getUser_pallow() {
        return user_pallow;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public void setUser_site(Text user_site) {
        this.user_site = user_site;
    }

    public void setUser_level(int user_level) {
        this.user_level = user_level;
    }

    public void setUser_pallow(int user_pallow) {
        this.user_pallow = user_pallow;
    }
}
