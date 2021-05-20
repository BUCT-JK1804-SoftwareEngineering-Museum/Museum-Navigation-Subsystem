package com.example.maptest.util.net;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class OkGoConfig {

    public static final String HEAD_FORM = "application/x-www-form-urlencoded";
    public static final String HEAD_JSON = "application/json";
    private static final int REQUEST_GET = 0;
    private static final int REQUEST_POST = 1;
    private static final int REQUEST_JSON = 2;
    private String baseUrl;
    public static void initOkGo(Application context) {
        /*公共请求头,header不支持中文，不允许有特殊字符*/
        HttpHeaders headers = new HttpHeaders();
        /*公共请求参数,param支持中文,直接传,不要自己编码*/
        HttpParams params = new HttpParams();
        headers.put("Accept", HEAD_JSON);

        /*配置日志*/
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("HX");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);

        /*
        配置cookie, cookie(session)管理, 以下选其一:
        数据库: 如果cookie不过期，则一直有效
        CookieJarImpl dbCookieJar1 = new CookieJarImpl(new DBCookieStore(this));
        SharedPreference: 如果cookie不过期，则一直有效
        CookieJarImpl spCookieJar = new CookieJarImpl(new SPCookieStore(this));
        内存: app退出后，cookie消失
        CookieJarImpl memCookieJar = new CookieJarImpl(new MemoryCookieStore());
        */
        CookieJarImpl dbCookieJar1 = new CookieJarImpl(new DBCookieStore(context));

        /*
        HTTPS设置，以下几种方案根据需要自己设置
        方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        方法二：自定义信任规则，校验服务端证书
        HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
        方法三：使用预埋证书，校验服务端证书（自签名证书）
        HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca
        .cer"));
        方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
        HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx
        .bks"), "123456", getAssets().open("yyy.cer"));
        */
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();

        /*配置Http请求客户端
        此处暂不做域名匹配校验, 如需要, 配置builder.hostnameVerifier(自定义的域名验证器)*/
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .cookieJar(dbCookieJar1)
                .sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);

        // 其他统一的配置(GitHub文档：https://github.com/jeasonlzy/)
        OkGo.getInstance().init(context)
                //建议设置OkHttpClient，不设置会使用默认的
                .setOkHttpClient(builder.build())
                //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheMode(CacheMode.NO_CACHE)
                //全局统一缓存时间，默认永不过期，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                //全局统一超时重连次数，默  认为三次，那么最差的情况会请求4次(1次原始请求，3次重连请求，不需要可以设置为0)
                .setRetryCount(3)
                //全局公共头
                .addCommonHeaders(headers)
                //全局公共参数
                .addCommonParams(params);
    }



}
