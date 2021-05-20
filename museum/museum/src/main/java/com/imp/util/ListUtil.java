package com.imp.util;


import com.imp.model.UserModel;
import org.apache.shiro.SecurityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtil {

    public static final String SUCCESS_STRING = "success";
    public static final String MESSAGE_STRING = "message";
    public static final Integer DEFAULT_INSERT_LIST_SIZE = 100;
    public static final String DEFAULT_PASSWORD = "c4ca4238a0b923820dcc509a6f75849b";
    public static final String DEFAULT_SOFTTDOG = "MQ==";
    public static final Long ADMIN_ORG_ID = 0L;

    /**
     * 获取系统当前登录帐户
     *
     * @return
     */
    public static UserModel getLoginUser() {
        return (UserModel) SecurityUtils.getSubject().getPrincipal();
    }


    /**
     * 获取返回Map
     *
     * @param result 返回结果
     */
    public static Map<String, Object> getResultMap(boolean result) {
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put(SUCCESS_STRING, result);
        return resultMap;
    }

    /**
     * 将一个List分割为多个list
     *
     * @param allList 要分割的List
     * @param size    分割的大小
     */
    public static <T> List<List<T>> getSubList(List<T> allList, int size) {
        List<List<T>> lists = new ArrayList<>();
        for (int i = 0; i < allList.size(); i += size) {
            if (i + size < allList.size()) {
                lists.add(allList.subList(i, i + size));
            } else {
                lists.add(allList.subList(i, allList.size()));
            }
        }
        return lists;
    }

    public static <T> List<List<T>> getSubList(List<T> allList) {
        return getSubList(allList, DEFAULT_INSERT_LIST_SIZE);
    }


    /**
     * 根据不同浏览器将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     *
     * @param s 原文件名
     * @return 重新编码后的文件名
     */
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    b = new byte[0];
                }
                for (byte aB : b) {
                    int k = aB;
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%").append(Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public static Map<String, String> getExtras(String key, String dataId) {
        Map<String, String> extras = new HashMap<>(3);
        extras.put("key", key);
        extras.put("dataId", dataId);
        return extras;
    }


}
