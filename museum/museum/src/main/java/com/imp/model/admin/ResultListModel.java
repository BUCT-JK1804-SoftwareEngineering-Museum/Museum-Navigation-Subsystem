package com.imp.model.admin;
import java.util.List;


public class ResultListModel<T>{

    private Boolean success;
    private String message;
    private List<T> list;

    public ResultListModel() {

    }

    public ResultListModel(List<T> list) {
        this.success = true;
        this.list = list;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
