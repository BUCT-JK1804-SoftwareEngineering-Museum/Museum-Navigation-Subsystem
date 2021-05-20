package com.example.maptest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maptest.adapter.MuseumListAdapter;
import com.example.maptest.base.BaseAtivity;
import com.example.maptest.bean.MuseumListAllBean;
import com.example.maptest.bean.MuseumListBean;
import com.example.maptest.util.net.Api;
import com.example.maptest.util.net.HlIntentDataUtil;
import com.example.maptest.util.net.NetConfig;
import com.example.maptest.util.net.imp.HlAppInterface;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MuseumListActivity extends BaseAtivity {

    @BindView(R.id.rv_content)
    ListView rvContent;
    private String name;
    private ArrayList<MuseumListBean.ListBean> listBeans = new ArrayList<>();
    private MuseumListAdapter museumListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_list);
        ButterKnife.bind(this);//activity绑定museum_list
        Intent intent = getIntent();
        name = intent.getStringExtra("name");//获取前面传来的name
        Log.i("stf", "--name--->" + name);
        initAdapter();
        initData();
    }

    private void initAdapter() {
        museumListAdapter = new MuseumListAdapter(listBeans, this);
        rvContent.setAdapter(museumListAdapter);
        museumListAdapter.setOnItemListener(new MuseumListAdapter.OnItemListener() {
            @Override
            public void itemListener(MuseumListBean.ListBean bean, int pos)
            {//id和name传给museumdetailactivity
                Intent intent = new Intent(MuseumListActivity.this, MuseumDetailActivity.class);
                intent.putExtra("id", bean.getMusId() + "");
                intent.putExtra("name", bean.getMusName() + "");
                startActivityForResult(intent, 201);
            }
        });
    }

    private void initData() {
        JSONObject object = new JSONObject();
        try {
            object.put("name", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = NetConfig.Url.getBaseUrl(Api.selectByName);
        showDialog(NetConfig.DialogUpMsg, false);

        HlIntentDataUtil.postFormHeardEnqueue(MuseumListBean.class, url + "/", object, null, new HlAppInterface.OnHlResultListener<MuseumListBean>() {
            @Override
            public void onSuccess(MuseumListBean bean) {//修改URL地址找对应博物馆名字的信息
                dissDialog();//进度条
                if (bean != null) {
                    if (bean.getList() != null) {
                        List<MuseumListBean.ListBean> list = bean.getList();
                        if(list.size() == 0){
                            Toast.makeText(MuseumListActivity.this, "未查到博物馆信息", Toast.LENGTH_SHORT).show();
                        }else {
                            listBeans.clear();
                            listBeans.addAll(list);
                            museumListAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(MuseumListActivity.this, "未查到博物馆信息", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MuseumListActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String msg) {
                dissDialog();
                Toast.makeText(MuseumListActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {//返回
        super.onBackPressed();
        finish();
    }
}