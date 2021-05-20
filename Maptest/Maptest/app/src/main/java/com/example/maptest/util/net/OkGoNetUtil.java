package com.example.maptest.util.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.maptest.base.BaseApplication;
import com.example.maptest.util.LogUtils;
import com.example.maptest.util.NetworkUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class OkGoNetUtil {
    public static OkGoNetUtil okGoUtil;
    public static Context mContext;


    public static void setHeard(String msg) {
        HttpHeaders header = OkGo.getInstance().getCommonHeaders();
        header.put("Content-Type", msg);
//        header.put("Accept", msg);
        OkGo.getInstance().addCommonHeaders(header);
    }


    //get 请求
    public static <T> void getParam(final Class<T> tClass, final String url, final OnResultListener<T> responseOnListener) {

        if (mContext == null) {
            mContext = BaseApplication.getApplication();
        }

        boolean netWorkAvailable = NetworkUtil.isNetWorkAvailable2(mContext);
        if (!netWorkAvailable) {
            responseOnListener.onError(NetConfig.NetError);
            return;
        }

        OkGo.<String>get(url).tag(mContext).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    String body = response.body();
//                    LogUtils.i("HX", "-getParam-->" + url + "-->\r\n" + body);
                    if (body != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            int code = jsonObject.optInt("code");
                            if (code == NetConfig.OutApp) {
                                responseOnListener.onOutApp(body);
                                String message = jsonObject.getString("message");
//                                //OutAppDialog.getInstance().showOutAppDialog(BaseActivity.mContext, message);
                            } else {
                                T t = new Gson().fromJson(body.toString(), tClass);
                                responseOnListener.onSuccess(t, body);
                            }
                        } catch (Exception e) {
                            e.fillInStackTrace();
                            responseOnListener.onError(NetConfig.DataError);
                        }
                    } else {
                        responseOnListener.onError(NetConfig.DataError);
                    }
                } catch (Exception e) {
                    e.fillInStackTrace();
                    responseOnListener.onError(e.getMessage());
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                responseOnListener.onConnectFail(NetConfig.ConnectError, response);
            }
        });
    }

    //post 请求
    public static <T> void postParam(final Class<T> tClass, final String url, HashMap<String, String> map, final OnResultListener<T> responseOnListener) {

        if (mContext == null) {
            mContext = BaseApplication.getApplication();
        }

        boolean netWorkAvailable = NetworkUtil.isNetWorkAvailable2(mContext);
        if (!netWorkAvailable) {
            responseOnListener.onError(NetConfig.NetError);
            return;
        }
        JSONObject jsonObject = new JSONObject(map);
        OkGo.<String>post(url).tag(mContext).upJson(jsonObject.toString()).execute(new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                LogUtils.i("HX", "-postParam-->" + url + "-->\r\n" + body);
                if (body != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        int code = jsonObject.optInt("code");
                        if (code == NetConfig.OutApp) {
                            responseOnListener.onOutApp(body);
                            String message = jsonObject.getString("message");
//                            //OutAppDialog.getInstance().showOutAppDialog(BaseActivity.mContext, message);
                        } else {
                            String result = jsonObject.optString("result");
                            if (TextUtils.isEmpty(result) || "null".equals(result)) {
                                JSONObject object = new JSONObject();

                                if (jsonObject.has("code")) {
                                    object.put("code", jsonObject.getString("code"));
                                }
                                if (jsonObject.has("message")) {
                                    object.put("message", jsonObject.getString("message"));
                                }

                                T t = new Gson().fromJson(object.toString(), tClass);
                                responseOnListener.onSuccess(t, object.toString());
                            } else {
                                T t = new Gson().fromJson(body, tClass);
                                responseOnListener.onSuccess(t, body);
                            }
                        }
                    } catch (Exception e) {
                        e.fillInStackTrace();
                        Log.i("stf","---cuowu--->"+e.fillInStackTrace());
                        responseOnListener.onError(NetConfig.DataError);
                    }
                } else {
                    Log.i("stf","---cuowu--->");
                    responseOnListener.onError(NetConfig.DataError);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                responseOnListener.onConnectFail(NetConfig.ConnectError, response);
            }
        });
    }
    public static <T> void postParamBean(final Class<T> tClass, final String url, HashMap<String, String> map, final OnResultListener<T> responseOnListener) {

        if (mContext == null) {
            mContext = BaseApplication.getApplication();
        }

        boolean netWorkAvailable = NetworkUtil.isNetWorkAvailable2(mContext);
        if (!netWorkAvailable) {
            responseOnListener.onError(NetConfig.NetError);
            return;
        }
        JSONObject jsonObject = new JSONObject(map);
        OkGo.<String>post(url).tag(mContext).upJson(jsonObject.toString()).execute(new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                LogUtils.i("HX", "-postParam-->" + url + "-->\r\n" + body);
                if (body != null) {
                    try {
                        T t = new Gson().fromJson(body, tClass);
                        responseOnListener.onSuccess(t, body);
                    } catch (Exception e) {
                        e.fillInStackTrace();
                        Log.i("stf","---cuowu--->"+e.fillInStackTrace());
                        responseOnListener.onError(NetConfig.DataError);
                    }
                } else {
                    Log.i("stf","---cuowu--->");
                    responseOnListener.onError(NetConfig.DataError);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                responseOnListener.onConnectFail(NetConfig.ConnectError, response);
            }
        });
    }

    //post 请求
    public static <T> void postParamString(final Class<T> tClass, HttpHeaders headers , final String url, HttpParams  map, final OnResultListener<T> responseOnListener) {

        if (mContext == null) {
            mContext = BaseApplication.getApplication();
        }

        boolean netWorkAvailable = NetworkUtil.isNetWorkAvailable2(mContext);
        if (!netWorkAvailable) {
            responseOnListener.onError(NetConfig.NetError);
            return;
        }
        OkGo.<String>post(url).headers(headers).tag(mContext).params(map).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                LogUtils.i("HX", "-postParam-->" + url + "-->\r\n" + body);
                if (body != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        int code = jsonObject.optInt("code");
                        if (code == NetConfig.OutApp) {
                            responseOnListener.onOutApp(body);
                            String message = jsonObject.getString("message");
//                            //OutAppDialog.getInstance().showOutAppDialog(BaseActivity.mContext, message);
                        } else {
                            String result = jsonObject.optString("result");
                            if (TextUtils.isEmpty(result) || "null".equals(result)) {
                                JSONObject object = new JSONObject();

                                if (jsonObject.has("code")) {
                                    object.put("code", jsonObject.getString("code"));
                                }
                                if (jsonObject.has("message")) {
                                    object.put("message", jsonObject.getString("message"));
                                }
                                T t = new Gson().fromJson(object.toString(), tClass);
                                responseOnListener.onSuccess(t, body);
                            } else {
                                T t = new Gson().fromJson(body, tClass);
                                responseOnListener.onSuccess(t, body);
                            }
                        }
                    } catch (Exception e) {
                        e.fillInStackTrace();
                        responseOnListener.onError(NetConfig.DataError);
                    }
                } else {
                    responseOnListener.onError(NetConfig.DataError);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                responseOnListener.onConnectFail(NetConfig.ConnectError, response);
            }
        });
    }
    public static <T> void postParam2(final Class<T> tClass, final String url, HashMap<String, Object> map, final OnResultListener<T> responseOnListener) {

        if (mContext == null) {
            mContext = BaseApplication.getApplication();
        }

        boolean netWorkAvailable = NetworkUtil.isNetWorkAvailable2(mContext);
        if (!netWorkAvailable) {
            responseOnListener.onError(NetConfig.NetError);
            return;
        }

        JSONObject jsonObject = new JSONObject(map);
        OkGo.<String>post(url).tag(mContext).upJson(jsonObject.toString()).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    String body = response.body();
                    LogUtils.i("HX", "-postParam-->" + url + "-->\r\n" + body);
                    if (body != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            int code = jsonObject.optInt("code");
                            if (code == NetConfig.OutApp) {
                                responseOnListener.onOutApp(body);
                                String message = jsonObject.getString("message");
//                                //OutAppDialog.getInstance().showOutAppDialog(BaseActivity.mContext, message);
                            } else {
                                String result = jsonObject.optString("result");
                                if (TextUtils.isEmpty(result) || "null".equals(result)) {
                                    JSONObject object = new JSONObject();

                                    if (jsonObject.has("code")) {
                                        object.put("code", jsonObject.getString("code"));
                                    }
                                    if (jsonObject.has("message")) {
                                        object.put("message", jsonObject.getString("message"));
                                    }
                                    T t = new Gson().fromJson(object.toString(), tClass);
                                    responseOnListener.onSuccess(t, body);
                                } else {
                                    T t = new Gson().fromJson(body, tClass);
                                    responseOnListener.onSuccess(t, body);
                                }
                            }
                        } catch (Exception e) {
                            e.fillInStackTrace();
                            responseOnListener.onError(NetConfig.DataError);
                        }
                    } else {
                        responseOnListener.onError(NetConfig.DataError);
                    }
                } catch (Exception e) {
                    e.fillInStackTrace();
                    responseOnListener.onError(e.getMessage()!=null?e.getMessage():"");
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                responseOnListener.onConnectFail(NetConfig.ConnectError, response);
            }
        });
    }


    public static <T> void postParam3(final Class<T> tClass, final String url, String map, final OnResultListener<T> responseOnListener) {

        if (mContext == null) {
            mContext = BaseApplication.getApplication();
        }

        boolean netWorkAvailable = NetworkUtil.isNetWorkAvailable2(mContext);
        if (!netWorkAvailable) {
            responseOnListener.onError(NetConfig.NetError);
            return;
        }


        OkGo.<String>post(url).tag(mContext).upJson(map).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    String body = response.body();
                    if (body != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            int code = jsonObject.optInt("code");
                            if (code == NetConfig.OutApp) {
                                responseOnListener.onOutApp(body);
                                String message = jsonObject.getString("message");
//                                //OutAppDialog.getInstance().showOutAppDialog(BaseActivity.mContext, message);
                            } else {
                                String result = jsonObject.optString("result");
                                if (TextUtils.isEmpty(result) || "null".equals(result)) {
                                    JSONObject object = new JSONObject();

                                    if (jsonObject.has("code")) {
                                        object.put("code", jsonObject.getString("code"));
                                    }
                                    if (jsonObject.has("message")) {
                                        object.put("message", jsonObject.getString("message"));
                                    }
                                    T t = new Gson().fromJson(object.toString(), tClass);
                                    responseOnListener.onSuccess(t, body);
                                } else {
                                    T t = new Gson().fromJson(body, tClass);
                                    responseOnListener.onSuccess(t, body);
                                }
                            }
                        } catch (Exception e) {
                            e.fillInStackTrace();
                            responseOnListener.onError(NetConfig.DataError);
                        }
                    } else {
                        responseOnListener.onError(NetConfig.DataError);
                    }
                } catch (Exception e) {
                    e.fillInStackTrace();
                    responseOnListener.onError(e.getMessage());
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                responseOnListener.onConnectFail(NetConfig.ConnectError, response);
            }
        });
    }


    // 上传图片
    public static <T> void postImgParam(final Class<T> tClass, final String url, ArrayList<File> list, final OnResultListener<T> responseOnListener) {
        if (mContext == null) {
            mContext = BaseApplication.getApplication();
        }

        boolean netWorkAvailable = NetworkUtil.isNetWorkAvailable2(mContext);
        if (!netWorkAvailable) {
            responseOnListener.onError(NetConfig.NetError);
            return;
        }

        OkGo.<String>post(url).tag(mContext).isMultipart(true).addFileParams("files", list).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    String body = response.body();
                    if (body != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            int code = jsonObject.optInt("code");
                            if (code == NetConfig.OutApp) {
                                responseOnListener.onOutApp(body);
                                String message = jsonObject.getString("message");
                                //OutAppDialog.getInstance().showOutAppDialog(BaseActivity.mContext, message);
//                                new OutAppDialog().showOutAppDialog(BaseActivity.mContext, message);
                            } else {
                                String result = jsonObject.optString("result");
                                if (TextUtils.isEmpty(result) || "null".equals(result)) {
                                    JSONObject object = new JSONObject();

                                    if (jsonObject.has("code")) {
                                        object.put("code", jsonObject.getString("code"));
                                    }
                                    if (jsonObject.has("message")) {
                                        object.put("message", jsonObject.getString("message"));
                                    }
                                    T t = new Gson().fromJson(object.toString(), tClass);
                                    responseOnListener.onSuccess(t, body);
                                } else {
                                    T t = new Gson().fromJson(body, tClass);
                                    responseOnListener.onSuccess(t, body);
                                }
                            }
                        } catch (Exception e) {
                            e.fillInStackTrace();
                            responseOnListener.onError(NetConfig.DataError);
                        }
                    } else {
                        responseOnListener.onError(NetConfig.DataError);
                    }
                } catch (Exception e) {
                    e.fillInStackTrace();
                    responseOnListener.onError(e.getMessage());
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                responseOnListener.onConnectFail(NetConfig.ConnectError, response);
            }
        });
    }

    // put 请求
    public static <T> void putParam(final Class<T> tClass, final String url, HashMap<String, Object> map, final OnResultListener<T> responseOnListener) {
        if (mContext == null) {
            mContext = BaseApplication.getApplication();
        }

        boolean netWorkAvailable = NetworkUtil.isNetWorkAvailable2(mContext);
        if (!netWorkAvailable) {
            responseOnListener.onError(NetConfig.NetError);
            return;
        }
        JSONObject jsonObject = new JSONObject(map);
        OkGo.<String>put(url).tag(mContext).upJson(jsonObject.toString()).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    String body = response.body();

                    if (body != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            int code = jsonObject.optInt("code");
                            if (code == NetConfig.OutApp) {
                                String message = jsonObject.getString("message");
                                //OutAppDialog.getInstance().showOutAppDialog(BaseActivity.mContext, message);
                                responseOnListener.onOutApp(body);
                            } else {
                                String result = jsonObject.optString("result");
                                if (TextUtils.isEmpty(result) || "null".equals(result)) {
                                    JSONObject object = new JSONObject();

                                    if (jsonObject.has("code")) {
                                        object.put("code", jsonObject.getString("code"));
                                    }
                                    if (jsonObject.has("message")) {
                                        object.put("message", jsonObject.getString("message"));
                                    }
                                    T t = new Gson().fromJson(object.toString(), tClass);
                                    responseOnListener.onSuccess(t, body);
                                } else {
                                    T t = new Gson().fromJson(body, tClass);
                                    responseOnListener.onSuccess(t, body);
                                }
                            }
                        } catch (Exception e) {
                            e.fillInStackTrace();
                            responseOnListener.onError(NetConfig.DataError);
                        }
                    } else {
                        responseOnListener.onError(NetConfig.DataError);
                    }
                } catch (Exception e) {
                    e.fillInStackTrace();
                    responseOnListener.onError(e.getMessage());
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                responseOnListener.onConnectFail(NetConfig.ConnectError, response);
            }
        });
    }




    /**
    * 适配第三方接口（如预约挂号等）
    * */
    public static <T> void postThirdAppParam(final Class<T> tClass, final String url, HashMap map, final OnResultListener<T> responseOnListener) {

        if (mContext == null) {
            mContext = BaseApplication.getApplication();
        }

        boolean netWorkAvailable = NetworkUtil.isNetWorkAvailable2(mContext);
        if (!netWorkAvailable) {
            responseOnListener.onError(NetConfig.NetError);
            return;
        }


        JSONObject jsonObject = new JSONObject(map);
        OkGo.<String>post(url).tag(mContext).upJson(jsonObject.toString()).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                LogUtils.i("HX", "-postParam-->" + url + "-->\r\n" + body);
                if (body != null) {
                    try {
                        T t = new Gson().fromJson(body, tClass);
                        responseOnListener.onSuccess(t, body);
                    } catch (Exception e) {
                        e.fillInStackTrace();
                        responseOnListener.onError(NetConfig.DataError);
                    }
                } else {
                    responseOnListener.onError(NetConfig.DataError);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                responseOnListener.onConnectFail(NetConfig.ConnectError, response);
            }
        });
    }


    public interface OnResultListener<T> {
        void onSuccess(T bean, String json);

        void onError(String e);

        void onConnectFail(String msg, Response<String> response);

        void onOutApp(String json);

    }
}
