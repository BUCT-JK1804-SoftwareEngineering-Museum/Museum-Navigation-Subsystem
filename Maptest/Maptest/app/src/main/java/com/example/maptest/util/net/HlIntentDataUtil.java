package com.example.maptest.util.net;

import com.example.maptest.util.LogUtils;
import com.example.maptest.util.net.imp.HlAppInterface;
import com.example.maptest.util.net.imp.RetrofitServiceImpl;
import org.json.JSONObject;
import java.io.File;
import java.util.ArrayList;


public class HlIntentDataUtil {

    public static <T> void getJsonEnqueue(final Class<T> tClass, final String url, final String json, final JSONObject heard, final HlAppInterface.OnHlResultListener<T> resultListener) {
        LogUtils.i("入参：" + json);

        IntentDataUtil.getJsonEnqueue(tClass, url, NetConfig.Url.getBaseUrl(), json, heard, new RetrofitServiceImpl.ResponseOnListener<T>() {
            @Override
            public void onSuccess(T bean) {
                resultListener.onSuccess(bean);
            }

            @Override
            public void onError(String e) {
                resultListener.onError(e);
            }
        });

    }


    public static <T> void postJsonHeardEnqueue(final Class<T> tClass, final String url, final JSONObject json,
                                                final JSONObject heard, final HlAppInterface.OnHlResultListener<T> resultListener) {
        if (json != null) {
            LogUtils.i("入参：" + json.toString());
        }

        IntentDataUtil.postJsonHeardEnqueue(tClass, url, json, heard, new RetrofitServiceImpl.ResponseOnListener<T>() {
            @Override
            public void onSuccess(T bean) {
                resultListener.onSuccess(bean);
            }

            @Override
            public void onError(String e) {
                resultListener.onError(e);
            }
        });

    }

    public static <T> void postFormHeardEnqueue(final Class<T> tClass, final String url, final JSONObject json,
                                                final JSONObject heard, final HlAppInterface.OnHlResultListener<T> resultListener) {
        if (json != null) {
            LogUtils.i("入参：" + json.toString());
        }

        IntentDataUtil.postFormHeardEnqueue(tClass, url, json, heard, new RetrofitServiceImpl.ResponseOnListener<T>() {
            @Override
            public void onSuccess(T bean) {
                resultListener.onSuccess(bean);
            }

            @Override
            public void onError(String e) {
                resultListener.onError(e);
            }
        });

    }

    public static <T> void postFormHeardImgEnqueue(final Class<T> tClass, final String url, final JSONObject json,
                                                   final JSONObject heard, ArrayList<String> list, final HlAppInterface.OnHlResultListener<T> resultListener) {
        if (json != null) {
            LogUtils.i("入参：" + json.toString());
        }

        IntentDataUtil.postJsonHeardImgEnqueue(tClass, url, json, heard, list, new RetrofitServiceImpl.ResponseOnListener<T>() {

            @Override
            public void onSuccess(T bean) {
                resultListener.onSuccess(bean);
            }

            @Override
            public void onError(String e) {
                resultListener.onError(e);
            }
        });
    }

    // 同步
    public static <T> void postFormHeardImgExecute(final Class<T> tClass, final String url, final JSONObject json,
                                                   final JSONObject heard, String pathImg, final HlAppInterface.OnHlResultListener<T> resultListener) {
        if (json != null) {
            LogUtils.i("入参：" + json.toString());
        }

        ArrayList<File> filesList = new ArrayList<>();
        filesList.add(new File(pathImg));

        IntentDataUtil.postExecuteImg2(tClass, url, json, heard, filesList, new RetrofitServiceImpl.ResponseOnListener<T>() {

            @Override
            public void onSuccess(T bean) {
                resultListener.onSuccess(bean);
            }

            @Override
            public void onError(String e) {
                resultListener.onError(e);
            }
        });
    }


    public static <T> void putEnqueue(final Class<T> tClass, final String url, final JSONObject json,
                                      final JSONObject heard, final HlAppInterface.OnHlResultListener<T> resultListener) {
        if (json != null) {
            LogUtils.i("入参：" + json.toString());
        }

        IntentDataUtil.retofitPUT(tClass, url, json, heard, new RetrofitServiceImpl.ResponseOnListener<T>() {
            @Override
            public void onSuccess(T bean) {
                resultListener.onSuccess(bean);
            }

            @Override
            public void onError(String e) {
                resultListener.onError(e);
            }
        });
    }


    public static <T> void deleteEnqueue(final Class<T> tClass, final String url, final JSONObject json,
                                         final JSONObject heard, final HlAppInterface.OnHlResultListener<T> resultListener) {
        if (json != null) {
            LogUtils.i("入参：" + json.toString());
        }

        IntentDataUtil.retofitDelete(tClass, url, json, heard, new RetrofitServiceImpl.ResponseOnListener<T>() {
            @Override
            public void onSuccess(T bean) {
                resultListener.onSuccess(bean);
            }

            @Override
            public void onError(String e) {
                resultListener.onError(e);
            }
        });
    }

    public interface TestTokenResultListener {
        void isResult(Boolean flag, String msg);
    }

}
