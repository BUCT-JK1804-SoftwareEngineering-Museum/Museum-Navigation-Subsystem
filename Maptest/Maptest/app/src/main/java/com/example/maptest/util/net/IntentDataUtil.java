package com.example.maptest.util.net;

import android.util.Log;

import com.example.maptest.base.BaseApplication;
import com.example.maptest.util.LogUtils;
import com.example.maptest.util.NetworkUtil;
import com.example.maptest.util.net.imp.RetrofitServiceImpl;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class IntentDataUtil {
    private static int getMethodName(String flag) {
        return 0;
    }

    public interface OnResultListener<T> {

        void onSuccess(T bean);

        void onError();
    }

    //-异步---------------------------------------------------------------------------
    public static <T> void postEnqueue(final Class<T> tClass, final String url, final String jsonObjectJson,
                                       final JSONObject heard, final RetrofitServiceImpl.ResponseOnListener<T> responseOnListener) {
        if (!NetworkUtil.isNetWorkAvailable2(BaseApplication.getApplication())) {
            responseOnListener.onError("暂无网络连接");
            return;
        }

        Map<String, String> arrayMap = null;
        arrayMap = new HashMap<>();
        arrayMap.put("Action", url);
        arrayMap.put("Parameters", jsonObjectJson);

        getRetrofit(heard, url).create(RetrofitServiceImpl.class).intentDataPost("", arrayMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String mjson = response.body().string();
                        LogUtils.printJson("stf", mjson, "json");
                        Gson gson = new Gson();
                        T t = gson.fromJson(mjson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                } else {
                    try {
                        String errorJson = response.errorBody().string();
                        LogUtils.e("stf", "-errorJson-mjson-->" + errorJson);
                        Gson gson = new Gson();
                        T t = gson.fromJson(errorJson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtils.e("stf", "--链接服务器失败--->");
                responseOnListener.onError("服务器连接异常");
            }
        });
    }

    public static <T> void postFormHeardEnqueue(final Class<T> tClass, final String url, final JSONObject jsonObjectJson,
                                                final JSONObject heard, final RetrofitServiceImpl.ResponseOnListener<T> responseOnListener) {
        if (!NetworkUtil.isNetWorkAvailable2(BaseApplication.getApplication())) {
            responseOnListener.onError("暂无网络连接");
            return;
        }

        Map<String, String> arrayMap = null;
        if (jsonObjectJson != null) {
            arrayMap = new HashMap<>();
            Iterator<String> keys = jsonObjectJson.keys();
            while (keys.hasNext()) {
                try {
                    String key = keys.next();
                    arrayMap.put(key, jsonObjectJson.getString(key));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            arrayMap = new HashMap<>();
            arrayMap.put("", "");
        }

        getRetrofit(heard, url).create(RetrofitServiceImpl.class).intentDataPostFormHeard(arrayMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String mjson = response.body().string();
                        LogUtils.e("stf", "-body-mjson-->" + mjson);
                        LogUtils.printJson("stf", mjson, "json");
                        Gson gson = new Gson();
                        T t = gson.fromJson(mjson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                } else {
                    try {
                        String errorJson = response.errorBody().string();
                        LogUtils.e("stf", "-errorJson-mjson-->" + errorJson);
                        Gson gson = new Gson();
                        T t = gson.fromJson(errorJson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtils.e("stf", "--链接服务器失败--->");
                responseOnListener.onError("服务器连接异常");
            }
        });
    }

    public static <T> void postJsonHeardEnqueue(final Class<T> tClass, final String url, final JSONObject jsonObjectJson,
                                                final JSONObject heard, final RetrofitServiceImpl.ResponseOnListener<T> responseOnListener) {
        if (!NetworkUtil.isNetWorkAvailable2(BaseApplication.getApplication())) {
            responseOnListener.onError("暂无网络连接");
            return;
        }

        Map<String, String> arrayMap = null;
        if (jsonObjectJson != null) {
            arrayMap = new HashMap<>();
            Iterator<String> keys = jsonObjectJson.keys();
            while (keys.hasNext()) {
                try {
                    String key = keys.next();
                    arrayMap.put(key, jsonObjectJson.getString(key));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            arrayMap = new HashMap<>();
            arrayMap.put("", "");
        }

        getRetrofit(heard, url).create(RetrofitServiceImpl.class).intentDataPostJsonHeard(arrayMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String mjson = response.body().string();
//                        LogUtils.e("stf", "-postJsonHeardEnqueue-body-mjson-->" + mjson);
                        LogUtils.printJson("stf", mjson, "postJsonHeardEnqueuejson");
                        Gson gson = new Gson();
                        T t = gson.fromJson(mjson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                } else {
                    try {
                        String errorJson = response.errorBody().string();
                        LogUtils.e("stf", "-postJsonHeardEnqueue-errorJson-mjson-->" + errorJson);
                        Gson gson = new Gson();
                        T t = gson.fromJson(errorJson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtils.e("stf", "-postJsonHeardEnqueue-链接服务器失败--->");
                responseOnListener.onError("服务器连接异常");
            }
        });
    }

    // 异步请求 上传照片
    public static <T> void postJsonHeardImgEnqueue(final Class<T> tClass, final String url, final JSONObject jsonObjectJson,
                                                   final JSONObject heard, ArrayList<String> pathList, final RetrofitServiceImpl.ResponseOnListener<T> responseOnListener) {
        if (!NetworkUtil.isNetWorkAvailable2(BaseApplication.getApplication())) {
            responseOnListener.onError("暂无网络连接");
            return;
        }

        HashMap<String, String> arrayMap = null;
        if (jsonObjectJson != null) {
            arrayMap = new HashMap<>();
            Iterator<String> keys = jsonObjectJson.keys();
            while (keys.hasNext()) {
                try {
                    String key = keys.next();
                    arrayMap.put(key, jsonObjectJson.getString(key));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            arrayMap = new HashMap<>();
            arrayMap.put("", "");
        }

        ArrayList<File> filesList = new ArrayList<>();
        for (int i = 0; i < pathList.size(); i++) {
            filesList.add(new File(pathList.get(i)));
        }

        Log.i("stf", "-filesList--->" + filesList);

//        MultipartBody multipartBody = createMultipartBody(arrayMap, filesList);

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (File file : filesList) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }
        MultipartBody multipartBody = builder.build();

        getRetrofit(heard, url).create(RetrofitServiceImpl.class).upLoad(multipartBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String mjson = response.body().string();
//                        LogUtils.e("stf", "-postJsonHeardImgEnqueue-body-mjson-->" + mjson);
                        LogUtils.printJson("stf", mjson, "postJsonHeardImgEnqueue");
                        Gson gson = new Gson();
                        T t = gson.fromJson(mjson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                } else {
                    try {
                        String errorJson = response.errorBody().string();
                        LogUtils.e("stf", "-postJsonHeardImgEnqueue-errorJson-mjson-->" + errorJson);
                        Gson gson = new Gson();
                        T t = gson.fromJson(errorJson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtils.e("stf", "-postJsonHeardImgEnqueue-链接服务器失败--->");
                responseOnListener.onError("服务器连接异常");
            }
        });
    }

    // 同步请求 上传照片
    public static <T> void postExecuteImg(final Class<T> tClass, final String url, final JSONObject jsonObjectJson,
                                          final JSONObject heard, ArrayList<String> pathList, final RetrofitServiceImpl.ResponseOnListener<T> responseOnListener) {

        if (!NetworkUtil.isNetWorkAvailable2(BaseApplication.getApplication())) {
            responseOnListener.onError("暂无网络连接");
            return;
        }

        HashMap<String, String> arrayMap = null;
        if (jsonObjectJson != null) {
            arrayMap = new HashMap<>();
            Iterator<String> keys = jsonObjectJson.keys();
            while (keys.hasNext()) {
                try {
                    String key = keys.next();
                    arrayMap.put(key, jsonObjectJson.getString(key));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            arrayMap = new HashMap<>();
            arrayMap.put("", "");
        }

        ArrayList<File> filesList = new ArrayList<>();
        for (int i = 0; i < pathList.size(); i++) {
            filesList.add(new File(pathList.get(i)));
        }

        Log.i("stf", "-filesList--->" + filesList);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (File file : filesList) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }
        MultipartBody multipartBody = builder.build();

        try {
            retrofit2.Response<ResponseBody> execute = getRetrofit(heard, url).create(RetrofitServiceImpl.class).upLoad(multipartBody).execute();
            if (execute != null) {
                ResponseBody body = execute.body();
                if (body == null) {
                    try {
                        String string = execute.errorBody().string();
                        LogUtils.printJson("stf", string, "postExecuteImgjson");
                        Gson gson = new Gson();
                        T t = gson.fromJson(string, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                } else {
                    try {
                        String mjson = execute.body().string();
                        LogUtils.e("stf", "-postJsonHeardImgEnqueue-body-mjson-->" + mjson);
                        Gson gson = new Gson();
                        T t = gson.fromJson(mjson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                }
            } else {
                responseOnListener.onError("服务器连接异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseOnListener.onError("服务器连接异常,请重试");
        }
    }

    // 同步请求上传照片2
    public static <T> void postExecuteImg2(final Class<T> tClass, final String url, final JSONObject jsonObjectJson,
                                           final JSONObject heard, ArrayList<File> filesList, final RetrofitServiceImpl.ResponseOnListener<T> responseOnListener) {

        if (!NetworkUtil.isNetWorkAvailable2(BaseApplication.getApplication())) {
            responseOnListener.onError("暂无网络连接");
            return;
        }

        HashMap<String, String> arrayMap = null;
        if (jsonObjectJson != null) {
            arrayMap = new HashMap<>();
            Iterator<String> keys = jsonObjectJson.keys();
            while (keys.hasNext()) {
                try {
                    String key = keys.next();
                    arrayMap.put(key, jsonObjectJson.getString(key));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            arrayMap = new HashMap<>();
            arrayMap.put("", "");
        }

//        ArrayList<File> filesList = new ArrayList<>();
//        filesList.add(new File(pathImg));

        Log.i("stf", "-filesList--->" + filesList);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (File file : filesList) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }
        MultipartBody multipartBody = builder.build();

        try {
            retrofit2.Response<ResponseBody> execute = getRetrofit(heard, url).create(RetrofitServiceImpl.class).upLoad(multipartBody).execute();
            if (execute != null) {
                ResponseBody body = execute.body();
                if (body == null) {
                    try {
                        String string = execute.errorBody().string();
                        LogUtils.printJson("stf", string, "postJsonHeardImgEnqueuejson");
                        Gson gson = new Gson();
                        T t = gson.fromJson(string, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                } else {
                    try {
                        String mjson = execute.body().string();
                        LogUtils.e("stf", "-postJsonHeardImgEnqueue-body-mjson-->" + mjson);
                        Gson gson = new Gson();
                        T t = gson.fromJson(mjson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                }
            } else {
                responseOnListener.onError("服务器连接异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseOnListener.onError("服务器连接异常,请重试");
        }
    }


    public static <T> void retofitPUT(final Class<T> tClass, final String url, final JSONObject jsonObjectJson, final JSONObject heard, final RetrofitServiceImpl.ResponseOnListener<T> responseOnListener) {
        if (!NetworkUtil.isNetWorkAvailable2(BaseApplication.getApplication())) {
            responseOnListener.onError("暂无网络连接");
            return;
        }

        HashMap<String, String> arrayMap = null;
        if (jsonObjectJson != null) {
            arrayMap = new HashMap<>();
            Iterator<String> keys = jsonObjectJson.keys();
            while (keys.hasNext()) {
                try {
                    String key = keys.next();
                    arrayMap.put(key, jsonObjectJson.getString(key));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            arrayMap = new HashMap<>();
            arrayMap.put("", "");
        }

        getRetrofit(heard, url).create(RetrofitServiceImpl.class).intentDataPut(arrayMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String mjson = response.body().string();
                        Gson gson = new Gson();
                        T t = gson.fromJson(mjson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                } else {
                    try {
                        String errorJson = response.errorBody().string();
                        Gson gson = new Gson();
                        T t = gson.fromJson(errorJson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                responseOnListener.onError("服务器连接异常");
            }
        });

    }

    public static <T> void retofitDelete(final Class<T> tClass, final String url, final JSONObject jsonObjectJson, final JSONObject heard, final RetrofitServiceImpl.ResponseOnListener<T> responseOnListener) {
        if (!NetworkUtil.isNetWorkAvailable2(BaseApplication.getApplication())) {
            responseOnListener.onError("暂无网络连接");
            return;
        }

        HashMap<String, String> arrayMap = null;
        if (jsonObjectJson != null) {
            arrayMap = new HashMap<>();
            Iterator<String> keys = jsonObjectJson.keys();
            while (keys.hasNext()) {
                try {
                    String key = keys.next();
                    arrayMap.put(key, jsonObjectJson.getString(key));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            arrayMap = new HashMap<>();
            arrayMap.put("", "");
        }

        getRetrofit(heard, url).create(RetrofitServiceImpl.class).intentDataDelete(arrayMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String mjson = response.body().string();
                        Gson gson = new Gson();
                        T t = gson.fromJson(mjson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                } else {
                    try {
                        String errorJson = response.errorBody().string();
                        Gson gson = new Gson();
                        T t = gson.fromJson(errorJson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                responseOnListener.onError("服务器连接异常");
            }
        });

    }


    public static <T> void getJsonEnqueue(final Class<T> tClass, final String url, String baseUrl, final String json, final JSONObject heard, final RetrofitServiceImpl.ResponseOnListener<T> responseOnListener) {

        if (!NetworkUtil.isNetWorkAvailable2(BaseApplication.getApplication())) {
            responseOnListener.onError("暂无网络连接");
            return;
        }

        getRetrofit(heard, baseUrl).create(RetrofitServiceImpl.class).intentDataGetJson(url + json).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String mjson = response.body().string();
                        LogUtils.printJson("stf", mjson, "GET返回json");
//                        LogUtils.e("stf", "--GET-返回json->" + mjson);
                        Gson gson = new Gson();
                        T t = gson.fromJson(mjson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                } else {
                    try {
                        String errorJson = response.errorBody().string();
                        LogUtils.e("stf", "--GET-返回errorJson->" + errorJson);
                        Gson gson = new Gson();
                        T t = gson.fromJson(errorJson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtils.e("stf", "--链接服务器失败--->");
                responseOnListener.onError("服务器连接异常");
            }
        });
    }

    public static <T> void getFormEnqueue(final Class<T> tClass, final String url, String baseUrl, final String json, final JSONObject heard, final RetrofitServiceImpl.ResponseOnListener<T> responseOnListener) {

        if (!NetworkUtil.isNetWorkAvailable2(BaseApplication.getApplication())) {
            responseOnListener.onError("暂无网络连接");
            return;
        }

        getRetrofit(heard, baseUrl).create(RetrofitServiceImpl.class).intentDataGetForm(url + json).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String mjson = response.body().string();
//                        LogUtils.e("stf", "--GET-返回json->" + mjson);
                        LogUtils.printJson("stf", mjson, "GET返回json");
                        Gson gson = new Gson();
                        T t = gson.fromJson(mjson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                } else {
                    try {
                        String errorJson = response.errorBody().string();
                        LogUtils.e("stf", "--GET-返回errorJson->" + errorJson);
                        Gson gson = new Gson();
                        T t = gson.fromJson(errorJson, tClass);
                        responseOnListener.onSuccess(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseOnListener.onError("数据返回异常");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtils.e("stf", "--链接服务器失败--->");
                responseOnListener.onError("服务器连接异常");
            }
        });
    }


    private static Retrofit getRetrofit(final JSONObject heard, String basrUrl) {
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                Log.i("stf", "拦截器==" + message);
//            }
//        });
//
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request().newBuilder();
                if (heard != null) {
                    Iterator<String> keys = heard.keys();
                    while (keys.hasNext()) {
                        try {
                            String key = keys.next();
                            request.addHeader(key, heard.getString(key));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                request.build();
                return chain.proceed(request.build());
            }
        });
//        client.addInterceptor(httpLoggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(basrUrl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static MultipartBody createMultipartBody(HashMap<String, String> FormDataPartMap, List<File> fileList) {
        MultipartBody.Builder builder = (new MultipartBody.Builder()).setType(MultipartBody.FORM);
        Iterator var4 = FormDataPartMap.entrySet().iterator();

        while (var4.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) var4.next();
            builder.addFormDataPart((String) entry.getKey(), (String) entry.getValue());
        }

        var4 = fileList.iterator();

        while (var4.hasNext()) {
            File file = (File) var4.next();
            builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        }
        return builder.build();
    }
}
