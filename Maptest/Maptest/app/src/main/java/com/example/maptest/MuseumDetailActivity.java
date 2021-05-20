package com.example.maptest;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maptest.base.BaseApplication;
import com.example.maptest.base.BaseAtivity;
import com.example.maptest.bean.LoginBean;
import com.example.maptest.bean.MusenumDetailBean;
import com.example.maptest.util.SharedPreConfig;
import com.example.maptest.util.SharedPreUtils;
import com.example.maptest.util.net.Api;
import com.example.maptest.util.net.HlIntentDataUtil;
import com.example.maptest.util.net.NetConfig;
import com.example.maptest.util.net.imp.HlAppInterface;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MuseumDetailActivity extends BaseAtivity {//信息浏览

    @BindView(R.id.upload)
    Button upload;
    @BindView(R.id.vid_name)
    TextView vidName;
    @BindView(R.id.vid_info)
    TextView vidInfo;
    @BindView(R.id.vid_time)
    TextView vidTime;
    @BindView(R.id.mus_name)
    TextView musName;
    @BindView(R.id.btn_play)
    Button btnPlay;
    @BindView(R.id.btn_pause)
    Button btnPause;
    @BindView(R.id.btn_replay)
    Button btnReplay;
    @BindView(R.id.btn_stop)
    Button btnStop;
    private String name;
    private MediaPlayer mediaPlayer;
    private boolean isOk = false;
    private String musId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detail);
        ButterKnife.bind(this);//绑定

        musId = getIntent().getStringExtra("id");//获取前面传来的id和name
        name = getIntent().getStringExtra("name");
        initData(musId);
        setBaseInfo();

    }

    private void setBaseInfo() {//添加博物馆名称
        if (!TextUtils.isEmpty(name)) {
            musName.setText("博物馆名称:\r\n" + name);
        }
    }

    private void initData(String id) {
        JSONObject object = new JSONObject();
        try {
            object.put("musId", id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = NetConfig.Url.getBaseUrl(Api.selectByMusId);
        showDialog(NetConfig.DialogUpMsg, false);

        HlIntentDataUtil.postFormHeardEnqueue(MusenumDetailBean.class, url + "/", object, null, new HlAppInterface.OnHlResultListener<MusenumDetailBean>()
        {//修改URL地址找对应博物馆名字的信息
            @Override
            public void onSuccess(MusenumDetailBean bean) {//把讲解信息列出来
                dissDialog();
                if(bean != null){
                    List<MusenumDetailBean.ListBean> list = bean.getList();
                    if(list != null){
                        if(list.size()>=1){
                            MusenumDetailBean.ListBean listBean = list.get(0);
                            setInfo(listBean);
                        }
                    }
                }
            }

            @Override
            public void onError(String msg) {
                dissDialog();
                Toast.makeText(MuseumDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setInfo(MusenumDetailBean.ListBean listBean) {
        //修改三个textview的text
        vidName.setText("讲解名称:\r\n"+listBean.getVidName());
        vidInfo.setText("讲解介绍\r\n"+listBean.getVidInfo());
        vidTime.setText("上传时间\r\n"+listBean.getVidTime());
        setYinPin(listBean.getVidAddr());
    }

    private void setYinPin(String url) {//加载音频
        if(TextUtils.isEmpty(url)){
            return;
        }
        String playUrl = NetConfig.Url.getBaseUrl()+url.replace("mp9","mp3");
        mediaPlayer = new MediaPlayer();
        mediaPlayer.reset();
        try {
            Log.i("stf","--playUrl--->"+playUrl);
            showDialog("正在加载音频，请稍后");
            mediaPlayer.setDataSource(playUrl);//指定装载uri所代表的文件
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
            dissDialog();
            Toast.makeText(this, "音频加载失败请重试", Toast.LENGTH_SHORT).show();
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                dissDialog();
                Toast.makeText(MuseumDetailActivity.this, "音频加载成功，可以播放", Toast.LENGTH_SHORT).show();
                isOk = true;
            }
        });
    }

    @OnClick({R.id.upload, R.id.btn_play, R.id.btn_pause, R.id.btn_replay, R.id.btn_stop})
    public void onClick(View view) {
        switch (view.getId()) {//上传、播放
            case R.id.upload:
                Intent intent = new Intent(MuseumDetailActivity.this, UploadActivity.class);//跳转上传页面并传递id参数
                intent.putExtra("musId",musId);
                startActivityForResult(intent, 200);
                break;
            case R.id.btn_play:
                if (!isOk) {
                    Toast.makeText(this, "正在加载请稍后", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }catch (Exception e){
                    e.fillInStackTrace();
                }
                break;
            case R.id.btn_pause:
                if (!isOk) {
                    Toast.makeText(this, "正在加载请稍后", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    if (mediaPlayer != null) {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                        }
                    }
                }catch (Exception e){
                    e.fillInStackTrace();
                }

                break;
            case R.id.btn_replay:
                if (!isOk) {
                    Toast.makeText(this, "正在加载请稍后", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    if (mediaPlayer != null) {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                        } else {
                            mediaPlayer.start();
                        }
                    }
                }catch (Exception e){
                    e.fillInStackTrace();
                }

                break;
            case R.id.btn_stop:
                if (!isOk) {
                    Toast.makeText(this, "正在加载请稍后", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    if (mediaPlayer != null) {
                        mediaPlayer.pause();
                    }
                }catch (Exception e){
                    e.fillInStackTrace();
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }catch (Exception e){
            e.fillInStackTrace();
        }

    }

    public void listBtnClick(View view) {//展品传参
        Intent intent = new Intent(MuseumDetailActivity.this, Auditstatus.class);
        intent.putExtra("type","2");
        intent.putExtra("musId",musId);
        startActivityForResult(intent,202);
    }

    public void listBtn2Click(View view) {//藏品传参
        Intent intent = new Intent(MuseumDetailActivity.this, Auditstatus.class);
        intent.putExtra("type","3");
        intent.putExtra("musId",musId);
        startActivityForResult(intent,203);
    }
}