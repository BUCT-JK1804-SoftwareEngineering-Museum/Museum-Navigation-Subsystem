package com.example.maptest.util.net;

public class NetConfig {


    /**
     * 响应的返回key
     */
    public class Code {
        public static final String SUCCESS = "success";
        public static final String MSG = "errorMsg";
        public static final String CODE = "errorCode";
        public static final String MODEL = "data";
    }



    /**
     * 网络请求Url
     */
    public static class Url {

        //服务器地址
        interface BaseUrl {
            String SERVER_PRODUCTION = "http://192.168.43.95:9000/";
        }

        /**
         * 返回服务器基础地址
         */
        public static String getBaseUrl(String url) {
            return BaseUrl.SERVER_PRODUCTION + url;
        }
        public static String getBaseUrl() {
            return BaseUrl.SERVER_PRODUCTION ;
        }
    }

    public static final String NetError = "暂无网络连接";
    public static final String ConnectError = "服务器连接失败";
    public static final String DataError = "数据返回异常";
    public static final int Success = 200;
    public static final int OutApp = 401;
    public static final String DialogUpMsg = "正在提交，请稍后...";
    public static final String DialogSearchMsg = "正在查询，请稍后...";

}