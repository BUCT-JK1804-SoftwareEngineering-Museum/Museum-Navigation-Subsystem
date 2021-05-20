package com.imp.enums;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//审核状态1：未审核；2：审核未通过；3：审核通过
public enum CheckType {
    NOT_CHECK("未审核", 1),
    FAILED("未通过", 2),
    PASS("通过", 3);
    private String text;
    private Integer value;

    CheckType(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public static String getJsonString() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for (CheckType gender : CheckType.values()) {
            jsonObject = new JSONObject();
            jsonObject.put("text", gender.getText());
            jsonObject.put("value", gender.getValue());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    public static String getMapJsonString() {
        List list = new ArrayList();
        Map<Object, Object> map = new HashMap<>();
        for (CheckType gender : CheckType.values()) {
            JSONObject jsonObject;
            jsonObject = new JSONObject();
            jsonObject.put("text", gender.getText());
            jsonObject.put("value", gender.getValue());
            list.add(jsonObject);
            map.put(gender.getValue(), gender.getText());
        }
        map.put("_list", list);
        return JSON.toJSONString(map);
    }

    /**
     * @param i
     * @return
     */
    public static CheckType valueOf(int i) {
        switch (i) {
            case 1:
                return NOT_CHECK;
            case 2:
                return FAILED;
            case 3:
                return PASS;
            default:
                return null;
        }
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
