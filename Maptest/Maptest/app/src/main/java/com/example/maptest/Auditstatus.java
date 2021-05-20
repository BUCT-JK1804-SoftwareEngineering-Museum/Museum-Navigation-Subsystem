package com.example.maptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maptest.adapter.AuditsusListAdapter;
import com.example.maptest.adapter.CangeListAdapter;
import com.example.maptest.adapter.SaveListAdapter;
import com.example.maptest.base.BaseApplication;
import com.example.maptest.base.BaseAtivity;
import com.example.maptest.bean.AuditstusListBean;
import com.example.maptest.bean.CangBean;
import com.example.maptest.bean.SaveListBean;
import com.example.maptest.util.SharedPreConfig;
import com.example.maptest.util.SharedPreUtils;
import com.example.maptest.util.net.Api;
import com.example.maptest.util.net.HlIntentDataUtil;
import com.example.maptest.util.net.NetConfig;
import com.example.maptest.util.net.imp.HlAppInterface;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Auditstatus extends BaseAtivity {
    public ArrayList<AuditstusListBean.ListBean> listBeans = new ArrayList<>();
    public ArrayList<SaveListBean.ListBean> listBeans2 = new ArrayList<>();
    public ArrayList<CangBean.ListBean> listBeans3 = new ArrayList<>();
    @BindView(R.id.rv_content)
    ListView rvContent;
    private AuditsusListAdapter auditsusListAdapter;
    private String musId;
    private SaveListAdapter saveListAdapter;
    private CangeListAdapter cangeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditstatus);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if(type.equals("1")){//投稿管理
            initAdapter();
            initData();
        }else if(type.equals("2")){//展览信息
            musId = intent.getStringExtra("musId");
            initAdapter2();
            initData2();
        }else if(type.equals("3")){//藏品信息
            musId = intent.getStringExtra("musId");
            initAdapter3();
            initData3();
        }
    }
    //三个创建
    private void initAdapter() {
        auditsusListAdapter = new AuditsusListAdapter(listBeans, this);
        rvContent.setAdapter(auditsusListAdapter);
    }
    private void initAdapter2() {
        saveListAdapter = new SaveListAdapter(listBeans2, this);
        rvContent.setAdapter(saveListAdapter);
    }
    private void initAdapter3() {
        cangeListAdapter = new CangeListAdapter(listBeans3, this);
        rvContent.setAdapter(cangeListAdapter);
    }

    private void initData() {

        JSONObject object = new JSONObject();
        try {
            object.put("userId", SharedPreUtils.create(BaseApplication.getApplication()).getString(SharedPreConfig.userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = NetConfig.Url.getBaseUrl(Api.selectByUserId);//找userid对应url
        showDialog(NetConfig.DialogSearchMsg, false);

        HlIntentDataUtil.postFormHeardEnqueue(AuditstusListBean.class, url + "/", object, null, new HlAppInterface.OnHlResultListener<AuditstusListBean>()
        {//查询数据
            @Override
            public void onSuccess(AuditstusListBean bean) {
                dissDialog();
                if (bean != null) {
                    if (bean.getList() != null) {
                        List<AuditstusListBean.ListBean> list = bean.getList();
                        if (list.size() == 0) {
                            Toast.makeText(Auditstatus.this, "未查到相关数据", Toast.LENGTH_SHORT).show();
                        } else {
                            listBeans.clear();
                            listBeans.addAll(list);
                            auditsusListAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(Auditstatus.this, "未查到相关数据", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Auditstatus.this, "查询失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String msg) {
                dissDialog();
                Toast.makeText(Auditstatus.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initData2() {

        JSONObject object = new JSONObject();
        try {
            object.put("musId", musId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = NetConfig.Url.getBaseUrl(Api.exhibition);
        showDialog(NetConfig.DialogSearchMsg, false);

        HlIntentDataUtil.postFormHeardEnqueue(SaveListBean.class, url + "/", object, null, new HlAppInterface.OnHlResultListener<SaveListBean>()
        {//找对应博物馆的exhibition数据
            @Override
            public void onSuccess(SaveListBean bean) {
                dissDialog();
                if (bean != null) {
                    if (bean.getList() != null) {
                        List<SaveListBean.ListBean> list = bean.getList();
                        if (list.size() == 0) {
                            Toast.makeText(Auditstatus.this, "未查到相关数据", Toast.LENGTH_SHORT).show();
                        } else {
                            listBeans2.clear();
                            listBeans2.addAll(list);
                            saveListAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(Auditstatus.this, "未查到相关数据", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Auditstatus.this, "查询失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String msg) {
                dissDialog();
                Toast.makeText(Auditstatus.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initData3() {

        JSONObject object = new JSONObject();
        try {
            object.put("musId", musId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = NetConfig.Url.getBaseUrl(Api.collection);
        showDialog(NetConfig.DialogSearchMsg, false);

        HlIntentDataUtil.postFormHeardEnqueue(CangBean.class, url + "/", object, null, new HlAppInterface.OnHlResultListener<CangBean>() {
            @Override
            public void onSuccess(CangBean bean) {
                dissDialog();
                if (bean != null) {
                    if (bean.getList() != null) {
                        List<CangBean.ListBean> list = bean.getList();
                        if (list.size() == 0) {
                            Toast.makeText(Auditstatus.this, "未查到相关数据", Toast.LENGTH_SHORT).show();
                        } else {
                            listBeans3.clear();
                            listBeans3.addAll(list);
                            cangeListAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(Auditstatus.this, "未查到相关数据", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Auditstatus.this, "查询失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String msg) {
                dissDialog();
                Toast.makeText(Auditstatus.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}