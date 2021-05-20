package com.example.maptest.util.net.imp;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;


public interface RetrofitServiceImpl {
    @POST(".")
    Call<ResponseBody> upLoad(@Body MultipartBody body);
//    @POST
//    Call<ResponseBody> upLoad(@Url String url, @Body MultipartBody body);


//    @Headers({"Content-Type: application/json"})//需要添加头
//    @POST(".")
//    Call<ResponseBody> upLoad(@Body RequestBody var2);

    @FormUrlEncoded
    @POST("{path}")
    Call<ResponseBody> intentDataPost(@Path("path") String var1, @FieldMap Map<String, String> var2);

    @Headers({"Content-Type: application/json"})//需要添加头
    @FormUrlEncoded
    @POST(".")
    Call<ResponseBody> intentDataPostJsonHeard(@FieldMap Map<String, String> arrayMap);


    @Headers({"Content-Type: application/x-www-form-urlencoded"})//需要添加头
    @FormUrlEncoded
    @POST(".")
    Call<ResponseBody> intentDataPostFormHeard(@FieldMap Map<String, String> params);

    //---------------------------------------------------------------------------------
//    @Headers({"Content-Type: application/json", "Appid:84e096f6bb5d36c665630768b45e1b48", "Appsecret:1799c5e84e8fae8b452b485eaaea5a32"})
    @Headers({"Content-Type: application/json"})
    //需要添加头
    @GET()
    Call<ResponseBody> intentDataGetJson(@Url String url);


    @Headers({"Content-Type: application/x-www-form-urlencoded"})//需要添加头
    @GET()
    Call<ResponseBody> intentDataGetForm(@Url String url);


    @Headers({"Content-Type: application/x-www-form-urlencoded"})//需要添加头
    @FormUrlEncoded
    @PUT(".")
    Call<ResponseBody> intentDataPut(@FieldMap Map<String, String> params);


    @Headers({"Content-Type: application/x-www-form-urlencoded"})//需要添加头
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = ".", hasBody = true)
    Call<ResponseBody> intentDataDelete(@FieldMap Map<String, String> params);


    interface ResponseOnListener<T> {

        void onSuccess(T bean);

        void onError(String e);
    }
}
