package com.example.maptest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;

import java.lang.String;
import java.util.HashMap;
import java.util.List;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.maptest.base.BaseAtivity;
import com.example.maptest.bean.MuseumListAllBean;
import com.example.maptest.util.FixedThreadPoolUtil;
import com.example.maptest.util.LogUtils;
import com.example.maptest.util.net.Api;
import com.example.maptest.util.net.NetConfig;
import com.example.maptest.util.net.OkGoNetUtil;
import com.lzy.okgo.model.Response;

/*
    百度地图应用，包含定位信息和地图显示
    一般需要打开定位服务，选择高精度定位模式，有网络连接
    需要在清单文件里使用百度云服务（参见清单文件service标签）
    需要创建应用（模块）的Key，并写入清单文件（参见清单文件meta标签）
*/
public class MainActivity extends BaseAtivity {
    private MapView mMapView = null;
    private Button button_site;
    LocationClient mLocClient;
    private EditText myEditText_site;
    private MyLocationConfiguration.LocationMode mCurrentNode;

    private BaiduMap mBaiduMap;
    HashMap<String, String> flag = new HashMap<String, String>();
    private boolean isFirstLoc = true;//是否第一次定位
    private String thenear;//最近博物馆
    private double distance = 0;//距离
    //定位经纬度
    private double latitude_1;
    private double longitude_1;

    private HashMap<String, Marker> Mks = new HashMap<String, Marker>();
    //private Marker mMarkerA,mMarkerB;
    private HashMap<String, InfoWindow> Iws = new HashMap<String, InfoWindow>();
    //private InfoWindow mInfoWindow;
    private HashMap<String, View> Cvs = new HashMap<String, View>();
    //地图名字hashmap
    private HashMap<String, String> Mid2Name = new HashMap<String, String>();

    //地图位置标签
    private HashMap<String, LatLng> LLs = new HashMap<String, LatLng>();
    private HashMap<String, MarkerOptions> MOs = new HashMap<String, MarkerOptions>();
    public String mapKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果没有定位权限，动态请求用户允许使用该权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        requestLocation();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "没有定位权限！", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    requestLocation();
                }
        }
    }

    private void requestLocation() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap
                .setMyLocationConfiguration(new MyLocationConfiguration(mCurrentNode, true, null));
        mBaiduMap.setMyLocationEnabled(true);//开启定位图层
        //定位初始化
        mLocClient = new LocationClient(this);
        MyLocationListener myListener = new MyLocationListener();
        //Log.e("tag","2当前位置经度为"+String.valueOf(latitude_1));
        //Log.e("tag","2当前位置维度为"+String.valueOf(longitude_1));
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);//打开gps
        option.setCoorType("bd09ll");//设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
        initOverlay();

        Mid2Name.put("mMarket_1", "故宫博物馆");
        Mid2Name.put("mMarket_2", "中国科学技术馆");
        Mid2Name.put("mMarket_3", "中国地质博物馆");
        Mid2Name.put("mMarket_4", "中国人民革命军事博物馆");
        Mid2Name.put("mMarket_5", "中国航天博物馆");
        Mid2Name.put("mMarket_6", "北京鲁迅博物馆");
        Mid2Name.put("mMarket_7", "首都博物馆");
        Mid2Name.put("mMarket_8", "北京自然博物馆");
        Mid2Name.put("mMarket_9", "中国人民抗日战争纪念馆");
        Mid2Name.put("mMarket_10", "北京天文馆");
        Mid2Name.put("mMarket_11", "周口店猿人遗址博物馆");
        Mid2Name.put("mMarket_12", "中国国家博物馆");
        Mid2Name.put("mMarket_13", "中国农业博物馆");
        Mid2Name.put("mMarket_14", "文化部恭王府博物馆");
        Mid2Name.put("mMarket_15", "天津博物馆");
        Mid2Name.put("mMarket_16", "天津自然博物馆");
        Mid2Name.put("mMarket_17", "周恩来邓颖超纪念馆");
        Mid2Name.put("mMarket_18", "河北博物院");
        Mid2Name.put("mMarket_19", "西柏坡纪念馆");
        Mid2Name.put("mMarket_20", "邯郸市博物馆");
        Mid2Name.put("mMarket_21", "山西博物院");
        Mid2Name.put("mMarket_22", "中国煤炭博物馆");
        Mid2Name.put("mMarket_23", "八路军太行纪念馆");
        Mid2Name.put("mMarket_24", "内蒙古博物院");
        Mid2Name.put("mMarket_25", "鄂尔多斯博物馆");
        Mid2Name.put("mMarket_26", "辽宁省博物馆");
        Mid2Name.put("mMarket_27", "“九·一八”历史博物馆");
        Mid2Name.put("mMarket_28", "抗美援朝纪念馆");
        Mid2Name.put("mMarket_29", "旅顺博物馆");
        Mid2Name.put("mMarket_30", "沈阳故宫博物院");
        Mid2Name.put("mMarket_31", "大连现代博物馆");
        Mid2Name.put("mMarket_32", "吉林省自然博物馆");
        Mid2Name.put("mMarket_33", "吉林省博物院");
        Mid2Name.put("mMarket_34", "伪满皇宫博物院");
        Mid2Name.put("mMarket_35", "东北烈士纪念馆");
        Mid2Name.put("mMarket_36", "铁人王进喜纪念馆");
        Mid2Name.put("mMarket_37", "瑷珲历史陈列馆");
        Mid2Name.put("mMarket_38", "黑龙江省博物馆");
        Mid2Name.put("mMarket_39", "大庆博物馆");
        Mid2Name.put("mMarket_40", "上海博物馆");
        Mid2Name.put("mMarket_41", "上海鲁迅纪念馆");
        Mid2Name.put("mMarket_42", "中共一大会址纪念馆");
        Mid2Name.put("mMarket_43", "上海科技馆");
        Mid2Name.put("mMarket_44", "陈云纪念馆");
        Mid2Name.put("mMarket_45", "南京博物院");
        Mid2Name.put("mMarket_46", "侵华日军南京大屠杀遇难同胞纪念馆");
        Mid2Name.put("mMarket_47", "南通博物苑");
        Mid2Name.put("mMarket_48", "苏州博物馆");
        Mid2Name.put("mMarket_49", "扬州博物馆");
        Mid2Name.put("mMarket_50", "常州博物馆");
        Mid2Name.put("mMarket_51", "南京市博物总馆");
        Mid2Name.put("mMarket_52", "浙江省博物馆");
        Mid2Name.put("mMarket_53", "浙江自然博物馆");
        Mid2Name.put("mMarket_54", "中国丝绸博物馆");
        Mid2Name.put("mMarket_55", "宁波博物馆");
        Mid2Name.put("mMarket_56", "杭州博物馆");
        Mid2Name.put("mMarket_57", "温州博物馆");
        Mid2Name.put("mMarket_58", "安徽省博物馆");
        Mid2Name.put("mMarket_59", "安徽中国徽州文化博物馆");
        Mid2Name.put("mMarket_60", "福建博物院");
        Mid2Name.put("mMarket_61", "古田会议纪念馆");
        Mid2Name.put("mMarket_62", "泉州海外交通史博物馆");
        Mid2Name.put("mMarket_63", "华侨博物院");
        Mid2Name.put("mMarket_64", "中国闽台缘博物馆");
        Mid2Name.put("mMarket_65", "中央苏区（闽西）历史博物馆");
        Mid2Name.put("mMarket_66", "井冈山革命博物馆");
        Mid2Name.put("mMarket_67", "江西省博物馆");
        Mid2Name.put("mMarket_68", "瑞金中央革命根据地纪念馆");
        Mid2Name.put("mMarket_69", "南昌八一起义纪念馆");
        Mid2Name.put("mMarket_70", "安源路矿工人运动纪念馆");
        Mid2Name.put("mMarket_71", "中国海军博物馆");
        Mid2Name.put("mMarket_72", "青岛市博物馆");
        Mid2Name.put("mMarket_73", "中国甲午战争博物馆");
        Mid2Name.put("mMarket_74", "青州博物馆");
        Mid2Name.put("mMarket_75", "山东博物馆");
        Mid2Name.put("mMarket_76", "烟台市博物馆");
        Mid2Name.put("mMarket_77", "潍坊市博物馆");
        Mid2Name.put("mMarket_78", "河南博物院");
        Mid2Name.put("mMarket_79", "郑州博物馆");
        Mid2Name.put("mMarket_80", "洛阳博物馆");
        Mid2Name.put("mMarket_81", "南阳汉画馆");
        Mid2Name.put("mMarket_82", "开封市博物馆");
        Mid2Name.put("mMarket_83", "鄂豫皖苏区首府革命博物馆");
        Mid2Name.put("mMarket_84", "湖北省博物馆");
        Mid2Name.put("mMarket_85", "荆州博物馆");
        Mid2Name.put("mMarket_86", "武汉博物馆");
        Mid2Name.put("mMarket_87", "辛亥革命武昌起义纪念馆");
        Mid2Name.put("mMarket_88", "武汉市中山舰博物馆");
        Mid2Name.put("mMarket_89", "湖南省博物馆");
        Mid2Name.put("mMarket_90", "韶山毛泽东故居纪念馆");
        Mid2Name.put("mMarket_91", "刘少奇故居纪念馆");
        Mid2Name.put("mMarket_92", "长沙简牍博物馆");
        Mid2Name.put("mMarket_93", "广东省博物馆");
        Mid2Name.put("mMarket_94", "西汉南越王博物馆");
        Mid2Name.put("mMarket_95", "孙中山故居纪念馆");
        Mid2Name.put("mMarket_96", "深圳博物馆");
        Mid2Name.put("mMarket_97", "广州博物馆");
        Mid2Name.put("mMarket_98", "广东民间工艺博物馆");
        Mid2Name.put("mMarket_99", "广西壮族自治区博物馆");
        Mid2Name.put("mMarket_100", "广西民族博物馆");
        Mid2Name.put("mMarket_101", "海南省博物馆");
        Mid2Name.put("mMarket_102", "自贡恐龙博物馆");
        Mid2Name.put("mMarket_103", "三星堆博物馆");
        Mid2Name.put("mMarket_104", "成都武侯祠博物馆");
        Mid2Name.put("mMarket_105", "邓小平故居陈列馆");
        Mid2Name.put("mMarket_106", "成都杜甫草堂博物馆");
        Mid2Name.put("mMarket_107", "四川博物院");
        Mid2Name.put("mMarket_108", "成都金沙遗址博物馆");
        Mid2Name.put("mMarket_109", "自贡市盐业历史博物馆");
        Mid2Name.put("mMarket_110", "遵义会议纪念馆");
        Mid2Name.put("mMarket_111", "云南省博物馆");
        Mid2Name.put("mMarket_112", "云南民族博物馆");
        Mid2Name.put("mMarket_113", "重庆中国三峡博物馆");
        Mid2Name.put("mMarket_114", "重庆红岩革命历史博物馆");
        Mid2Name.put("mMarket_115", "重庆自然博物馆");
        Mid2Name.put("mMarket_116", "西藏博物馆");
        Mid2Name.put("mMarket_117", "陕西历史博物馆");
        Mid2Name.put("mMarket_118", "秦始皇兵马俑博物馆");
        Mid2Name.put("mMarket_119", "延安革命纪念馆");
        Mid2Name.put("mMarket_120", "汉阳陵博物馆");
        Mid2Name.put("mMarket_121", "西安碑林博物馆");
        Mid2Name.put("mMarket_122", "西安半坡博物馆");
        Mid2Name.put("mMarket_123", "西安博物院");
        Mid2Name.put("mMarket_124", "宝鸡青铜器博物院");
        Mid2Name.put("mMarket_125", "西安大唐西市博物馆");
        Mid2Name.put("mMarket_126", "甘肃省博物馆");
        Mid2Name.put("mMarket_127", "天水市博物馆");
        Mid2Name.put("mMarket_128", "敦煌研究院");
        Mid2Name.put("mMarket_129", "固原博物馆");
        Mid2Name.put("mMarket_130", "宁夏博物馆");
        Mid2Name.put("mMarket_131", "青海省博物馆");
        Mid2Name.put("mMarket_132", "新疆维吾尔自治区博物馆");
        Mid2Name.put("mMarket_133", "吐鲁番博物馆");

        //动态修改名字
        for (String key : Mid2Name.keySet()) {
            View museumView = LayoutInflater.from(MainActivity.this).inflate(R.layout.bwg_1, null);
            TextView current = museumView.findViewById(R.id.TV_12);
            current.setText(Mid2Name.get(key));
            Cvs.put(key, museumView);
        }

        mBaiduMap.setOnMarkerClickListener((marker) -> {

            InfoWindow.OnInfoWindowClickListener listener = null;
            mBaiduMap.hideInfoWindow();
            Log.i("stf", "--setOnMarkerClickListener--->");
            for (String key : Mks.keySet()) {
                if (marker == Mks.get(key)) {// 点击的marker 显示出来
                    Log.i("stf", "--marker--->" + Mid2Name.get(key));
                    LatLng ll = marker.getPosition();
                    InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(Cvs.get(key)), ll, -100, new InfoWindow.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick() {
                            Intent intent = new Intent(MainActivity.this, MuseumListActivity.class);
                            intent.putExtra("name", Mid2Name.get(key));
                            startActivityForResult(intent, 201);
                        }
                    });
                    mBaiduMap.showInfoWindow(mInfoWindow, false);
                    break;
                }
            }


//            InfoWindow.OnInfoWindowClickListener listener = null;
//            for (String key : Mks.keySet()) {
//                if (marker == Mks.get(key)) {
//                    Log.i("stf","--marker-marker-->"+marker);
//                    if (!flag.containsKey(key) || flag.get(key) == "close") {
//                        LatLng ll = marker.getPosition();
//
//                        InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(Cvs.get(key)), ll, -47, listener);
//
//                        if (!Iws.containsKey(key)) {
//                            Iws.put(key, mInfoWindow);
//                        } else {
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                Iws.replace(key, mInfoWindow);
//                            }
//                        }
//
//                        mBaiduMap.showInfoWindow(mInfoWindow, false);
//                        if (!flag.containsKey(key)) {
//                            flag.put(key, "open");
//                        } else {
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                flag.replace(key, "close", "open");
//                            }
//                        }
//                        break;
//                    } else {
//                        mBaiduMap.hideInfoWindow(Iws.get(key));
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                            flag.replace(key, "open", "close");
//                        }
//                        break;
//                    }
//                }
//            }
            return true;
        });

        //定位地址
        myEditText_site = findViewById(R.id.city);
        button_site = findViewById(R.id.search);
        button_site.setOnClickListener(v -> {
            String site_str = myEditText_site.getText().toString();
            for (String key : Mid2Name.keySet()) {
                if (Mid2Name.get(key).equals(site_str)) {
                    LatLng ListL_1 = LLs.get(key);
                    getLocationByLL(ListL_1);
                    serMark(key,ListL_1);
                    break;
                }
            }

            //隐藏前面地址输入区域
            //myLinearLayout2.setVisibility(View.GONE);
            //隐藏输入法键盘
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
    }

    private void serMark(String key, LatLng latLng) {
        thenear = Mid2Name.get(key);
        TextView current_1 = findViewById(R.id.thenearname);
        current_1.setText(thenear);

//        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                return false;
//            }
//        });
        InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(Cvs.get(key)), latLng, -100, new InfoWindow.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick() {
                Intent intent = new Intent(MainActivity.this, MuseumListActivity.class);
                intent.putExtra("name", Mid2Name.get(key));
                startActivityForResult(intent, 201);
            }
        });
        mBaiduMap.showInfoWindow(mInfoWindow, false);
//        LatLng latLng = LLs.get(mapKey);// 最近的经纬度
//        InfoWindow mInfoWindow = new InfoWindow(Cvs.get(key), latLng, -100);
//        Cvs.get(mapKey).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!TextUtils.isEmpty(mapKey)) {
//                    Intent intent = new Intent(MainActivity.this, MuseumListActivity.class);
//                    intent.putExtra("name", Mid2Name.get(mapKey));
//                    startActivityForResult(intent, 201);
//                }
//            }
//        });

//        mBaiduMap.showInfoWindow(mInfoWindow, false);
    }

    /*
     *根据经纬度前往
     */
    public void getLocationByLL(LatLng l1) {
        new FixedThreadPoolUtil().queryData(new FixedThreadPoolUtil.Work() {
            @Override
            public void onInOhter() {
                //地理坐标的数据结构
                //描述地图状态将要发生的变化,通过当前经纬度来使地图显示到该位置
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(l1);
                mBaiduMap.setMapStatus(msu);
            }

            @Override
            public String onInMain() {
                return null;
            }
        });

    }

    public double getdistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    private void initOverlay() {
        //添加博物馆标签
        LLs.put("mMarket_1", new LatLng(39.924091, 116.403414));
        LLs.put("mMarket_2", new LatLng(40.012384, 116.40504));
        LLs.put("mMarket_3", new LatLng(39.929518, 116.378653));
        LLs.put("mMarket_4", new LatLng(39.914976, 116.330207));
        LLs.put("mMarket_5", new LatLng(40.192095, 116.372885));
        LLs.put("mMarket_6", new LatLng(39.931656, 116.365314));
        LLs.put("mMarket_7", new LatLng(39.912174, 116.348822));
        LLs.put("mMarket_8", new LatLng(39.889525, 116.406116));
        LLs.put("mMarket_9", new LatLng(39.857753, 116.232474));
        LLs.put("mMarket_10", new LatLng(39.943273, 116.343208));
        LLs.put("mMarket_11", new LatLng(39.693589, 115.943241));
        LLs.put("mMarket_12", new LatLng(39.91146, 116.408016));
        LLs.put("mMarket_13", new LatLng(39.947016, 116.47137));
        LLs.put("mMarket_14", new LatLng(39.942181, 116.392805));
        LLs.put("mMarket_15", new LatLng(39.091039, 117.218282));
        LLs.put("mMarket_16", new LatLng(39.091766, 117.215291));
        LLs.put("mMarket_17", new LatLng(39.100099, 117.17134));
        LLs.put("mMarket_18", new LatLng(38.046443, 114.529277));
        LLs.put("mMarket_19", new LatLng(38.345586, 113.95144));
        LLs.put("mMarket_20", new LatLng(36.616094, 114.529465));
        LLs.put("mMarket_21", new LatLng(37.871448, 112.537791));
        LLs.put("mMarket_22", new LatLng(37.863006, 112.534993));
        LLs.put("mMarket_23", new LatLng(36.839016, 112.862735));
        LLs.put("mMarket_24", new LatLng(40.845845, 111.745156));
        LLs.put("mMarket_25", new LatLng(39.607385, 109.791447));
        LLs.put("mMarket_26", new LatLng(41.684248, 123.466951));
        LLs.put("mMarket_27", new LatLng(41.842745, 123.473965));
        LLs.put("mMarket_28", new LatLng(41.861163, 123.456283));
        LLs.put("mMarket_29", new LatLng(38.814166, 121.241609));
        LLs.put("mMarket_30", new LatLng(41.803282, 123.46221));
        LLs.put("mMarket_31", new LatLng(38.894559, 121.592569));
        LLs.put("mMarket_32", new LatLng(43.835539, 125.43943));
        LLs.put("mMarket_33", new LatLng(43.774571, 125.439227));
        LLs.put("mMarket_34", new LatLng(43.91146, 125.355281));
        LLs.put("mMarket_35", new LatLng(45.771657, 126.64905));
        LLs.put("mMarket_36", new LatLng(46.615292, 124.893237));
        LLs.put("mMarket_37", new LatLng(49.98463, 127.499443));
        LLs.put("mMarket_38", new LatLng(45.763371, 126.647534));
        LLs.put("mMarket_39", new LatLng(46.590133, 125.160197));
        LLs.put("mMarket_40", new LatLng(31.234143, 121.48223));
        LLs.put("mMarket_41", new LatLng(31.275505, 121.490442));
        LLs.put("mMarket_42", new LatLng(31.226086, 121.481667));
        LLs.put("mMarket_43", new LatLng(31.224356, 121.547823));
        LLs.put("mMarket_44", new LatLng(31.012765, 121.050436));
        LLs.put("mMarket_45", new LatLng(32.046772, 118.83165));
        LLs.put("mMarket_46", new LatLng(32.041149, 118.749884));
        LLs.put("mMarket_47", new LatLng(32.017272, 120.876309));
        LLs.put("mMarket_48", new LatLng(31.329059, 120.634317));
        LLs.put("mMarket_49", new LatLng(32.397431, 119.378544));
        LLs.put("mMarket_50", new LatLng(31.814896, 119.978141));
        LLs.put("mMarket_51", new LatLng(32.040078, 118.782203));
        LLs.put("mMarket_52", new LatLng(30.257342, 120.149943));
        LLs.put("mMarket_53", new LatLng(30.282674, 120.171317));
        LLs.put("mMarket_54", new LatLng(30.228906, 120.157949));
        LLs.put("mMarket_55", new LatLng(29.821188, 121.551803));
        LLs.put("mMarket_56", new LatLng(30.244426, 120.173093));
        LLs.put("mMarket_57", new LatLng(27.994791, 120.704546));
        LLs.put("mMarket_58", new LatLng(31.808263, 117.226663));
        LLs.put("mMarket_59", new LatLng(29.7234, 118.281156));
        LLs.put("mMarket_60", new LatLng(26.100197, 119.293236));
        LLs.put("mMarket_61", new LatLng(25.227348, 116.829662));
        LLs.put("mMarket_62", new LatLng(24.915806, 118.617926));
        LLs.put("mMarket_63", new LatLng(24.448392, 118.096458));
        LLs.put("mMarket_64", new LatLng(24.941018, 118.596435));
        LLs.put("mMarket_65", new LatLng(25.108544, 117.030617));
        LLs.put("mMarket_66", new LatLng(26.573406, 114.172166));
        LLs.put("mMarket_67", new LatLng(28.711549, 115.888431));
        LLs.put("mMarket_68", new LatLng(25.88044, 116.021135));
        LLs.put("mMarket_69", new LatLng(28.680744, 115.895908));
        LLs.put("mMarket_70", new LatLng(27.609583, 113.903491));
        LLs.put("mMarket_71", new LatLng(36.061096, 120.337085));
        LLs.put("mMarket_72", new LatLng(36.107914, 120.479533));
        LLs.put("mMarket_73", new LatLng(37.505406, 122.193762));
        LLs.put("mMarket_74", new LatLng(36.690088, 118.469183));
        LLs.put("mMarket_75", new LatLng(36.664652, 117.102287));
        LLs.put("mMarket_76", new LatLng(37.542843, 121.39963));
        LLs.put("mMarket_77", new LatLng(36.718352, 119.165131));
        LLs.put("mMarket_78", new LatLng(34.794165, 113.678452));
        LLs.put("mMarket_79", new LatLng(34.751534, 113.63384));
        LLs.put("mMarket_80", new LatLng(34.64963, 112.457992));
        LLs.put("mMarket_81", new LatLng(32.976556, 112.512775));
        LLs.put("mMarket_82", new LatLng(34.809781, 114.260221));
        LLs.put("mMarket_83", new LatLng(31.622785, 114.886061));
        LLs.put("mMarket_84", new LatLng(30.567828, 114.371879));
        LLs.put("mMarket_85", new LatLng(30.359302, 112.186803));
        LLs.put("mMarket_86", new LatLng(30.617735, 114.263081));
        LLs.put("mMarket_87", new LatLng(30.547866, 114.312911));
        LLs.put("mMarket_88", new LatLng(30.351522, 114.139869));
        LLs.put("mMarket_89", new LatLng(28.217827, 113.000003));
        LLs.put("mMarket_90", new LatLng(27.909605, 112.500642));
        LLs.put("mMarket_91", new LatLng(28.041642, 112.653727));
        LLs.put("mMarket_92", new LatLng(28.190617, 112.989772));
        LLs.put("mMarket_93", new LatLng(23.120486, 113.332975));
        LLs.put("mMarket_94", new LatLng(23.144033, 113.267474));
        LLs.put("mMarket_95", new LatLng(22.449309, 113.534598));
        LLs.put("mMarket_96", new LatLng(22.54928, 114.06827));
        LLs.put("mMarket_97", new LatLng(23.144241, 113.272097));
        LLs.put("mMarket_98", new LatLng(23.133185, 113.252021));
        LLs.put("mMarket_99", new LatLng(22.818472, 108.341755));
        LLs.put("mMarket_100", new LatLng(22.780319, 108.408402));
        LLs.put("mMarket_101", new LatLng(20.021773, 110.385478));
        LLs.put("mMarket_102", new LatLng(29.401369, 104.836234));
        LLs.put("mMarket_103", new LatLng(31.007167, 104.225198));
        LLs.put("mMarket_104", new LatLng(30.652582, 104.054572));
        LLs.put("mMarket_105", new LatLng(30.522298, 106.640775));
        LLs.put("mMarket_106", new LatLng(30.666397, 104.034848));
        LLs.put("mMarket_107", new LatLng(30.667087, 104.040528));
        LLs.put("mMarket_108", new LatLng(30.687743, 104.019116));
        LLs.put("mMarket_109", new LatLng(29.355016, 104.777752));
        LLs.put("mMarket_110", new LatLng(27.694643, 106.927126));
        LLs.put("mMarket_111", new LatLng(24.955361, 102.760017));
        LLs.put("mMarket_112", new LatLng(24.970827, 102.675693));
        LLs.put("mMarket_113", new LatLng(29.568347, 106.556883));
        LLs.put("mMarket_114", new LatLng(29.578462, 106.448726));
        LLs.put("mMarket_115", new LatLng(29.82499, 106.414221));
        LLs.put("mMarket_116", new LatLng(29.654906, 91.104815));
        LLs.put("mMarket_117", new LatLng(34.230523, 108.961495));
        LLs.put("mMarket_118", new LatLng(34.389417, 109.2851));
        LLs.put("mMarket_119", new LatLng(36.614936, 109.484034));
        LLs.put("mMarket_120", new LatLng(34.447437, 108.959324));
        LLs.put("mMarket_121", new LatLng(34.26019, 108.959139));
        LLs.put("mMarket_122", new LatLng(34.279207, 109.05967));
        LLs.put("mMarket_123", new LatLng(34.243512, 108.947004));
        LLs.put("mMarket_124", new LatLng(34.353483, 107.201749));
        LLs.put("mMarket_125", new LatLng(34.253175, 108.91452));
        LLs.put("mMarket_126", new LatLng(36.072733, 103.781754));
        LLs.put("mMarket_127", new LatLng(34.586439, 105.713197));
        LLs.put("mMarket_128", new LatLng(36.068208, 103.85432));
        LLs.put("mMarket_129", new LatLng(36.011249, 106.282598));
        LLs.put("mMarket_130", new LatLng(38.490741, 106.241638));
        LLs.put("mMarket_131", new LatLng(36.636538, 101.762535));
        LLs.put("mMarket_132", new LatLng(43.825756, 87.590787));
        LLs.put("mMarket_133", new LatLng(42.948874, 89.197715));
        BitmapDescriptor bd_1 = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_mark);
        for (String key : LLs.keySet()) {
            MOs.put(key, new MarkerOptions().position(LLs.get(key)).icon(bd_1)
                    .zIndex(9).draggable(true));
            Mks.put(key, (Marker) (mBaiduMap.addOverlay(MOs.get(key))));
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(13.0f);
                latitude_1 = location.getLatitude();    //获取纬度信息
                longitude_1 = location.getLongitude();  //获取经度
                double lnear = LLs.get("mMarket_1").latitude;
                double longnear = LLs.get("mMarket_1").longitude;
                //Log.e("tag","当前位置经度为"+String.valueOf(latitude_1));
                //Log.e("tag","当前位置维度为"+String.valueOf(longitude_1));
                //Log.e("tag","故宫博物馆的经度为"+String.valueOf(lnear));
                //Log.e("tag","故宫博物馆的维度为"+String.valueOf(longnear));
                distance = getdistance(latitude_1, longitude_1, lnear, longnear);
                //Log.e("tag","距离为"+String.valueOf(distance));
                //最近推荐
                for (String key : Mid2Name.keySet()) {
                    double nlnear = LLs.get(key).latitude;
                    double nlongnear = LLs.get(key).longitude;
                    double ndistance = getdistance(latitude_1, longitude_1, nlnear, nlongnear);
                    if (ndistance < distance) {
                        thenear = Mid2Name.get(key);
                        distance = ndistance;
                        mapKey = key;
                    }
                }
                //Log.e("tag",String.valueOf(distance));
                TextView current_1 = findViewById(R.id.thenearname);
                current_1.setText(thenear);
                LatLng latLng = LLs.get(mapKey);// 最近的经纬度
                InfoWindow mInfoWindow = new InfoWindow(Cvs.get(mapKey), latLng, -100);
                Cvs.get(mapKey).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!TextUtils.isEmpty(mapKey)) {
                            Intent intent = new Intent(MainActivity.this, MuseumListActivity.class);
                            intent.putExtra("name", Mid2Name.get(mapKey));
                            startActivityForResult(intent, 201);
                        }
                    }
                });

                mBaiduMap.showInfoWindow(mInfoWindow, false);

                //Log.e("tag","1当前位置经度为"+String.valueOf(latitude_1));
                //Log.e("tag","1当前位置维度为"+String.valueOf(longitude_1));
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void OnReceivePoi(BDLocation poiLocation) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Mks.clear();
        Iws.clear();
        Cvs.clear();
        LLs.clear();
        MOs.clear();
        mLocClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mMapView = null;
    }
}