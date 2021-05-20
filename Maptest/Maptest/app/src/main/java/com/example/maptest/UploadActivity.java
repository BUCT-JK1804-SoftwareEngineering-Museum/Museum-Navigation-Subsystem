package com.example.maptest;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maptest.base.BaseApplication;
import com.example.maptest.base.BaseAtivity;
import com.example.maptest.bean.UpVideoBean;
import com.example.maptest.util.CalendarUtil;
import com.example.maptest.util.MediaHelper;
import com.example.maptest.util.PermissionsUtils;
import com.example.maptest.util.PlayHelper;
import com.example.maptest.util.RecordHelper;
import com.example.maptest.util.SharedPreConfig;
import com.example.maptest.util.SharedPreUtils;
import com.example.maptest.util.net.Api;
import com.example.maptest.util.net.NetConfig;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadActivity extends BaseAtivity {

    @BindView(R.id.ed_1)
    EditText ed1;
    @BindView(R.id.ed_2)
    EditText ed2;
    @BindView(R.id.ed_4)
    EditText ed4;
    private String audioPath;
    private boolean startAudio;
    ArrayList<String> recordSaveList = new ArrayList<>();
    private PlayHelper playHelper;
    private String musId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);//绑定
        musId =getIntent().getStringExtra("musId");//获得传来的id
        playHelper = new PlayHelper();
    }

    @Override
    public void onBackPressed() {//返回
        super.onBackPressed();
        finish();
    }

    public void startVoice(View view) {
        PermissionsUtils.checkRecord(UploadActivity.this).subscribe(r -> {//获取手机权限
            if (r) {
                audioPath = MediaHelper.getPath() + MediaHelper.getName("");//添加格式化日期和名字
                startAudio = RecordHelper.getInstance().start(audioPath);
                Toast.makeText(this, "录音开始，请讲解", Toast.LENGTH_SHORT).show();
                recordSaveList.add(audioPath);
            } else {
                Toast.makeText(this, "请授权才能正常工作", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void deleteVoice(View view) {
        if (playHelper != null) {
            playHelper.release();
        }
        MediaHelper.delete(recordSaveList);
        recordSaveList.clear();
        audioPath = "";
        RecordHelper.getInstance().stop();
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
    }

    public void playVoice(View view) {
        if(TextUtils.isEmpty(audioPath)){
            Toast.makeText(this, "录音内容不能为空", Toast.LENGTH_SHORT).show();
        }else {
            playHelper.play(audioPath, new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Toast.makeText(UploadActivity.this, "播放结束", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void upLoad(View view) {
        String nameVoice = ed1.getText().toString();
        String conVoice = ed2.getText().toString();
        String muVoice = ed4.getText().toString();

        if(TextUtils.isEmpty(audioPath)){
            Toast.makeText(this, "录音内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(nameVoice)){
            Toast.makeText(this, "请输入讲解名称", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(conVoice)){
            Toast.makeText(this, "请输入讲解介绍", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(muVoice)){
            Toast.makeText(this, "请输入博物馆名称", Toast.LENGTH_SHORT).show();
            return;
        }
        String ymdhms = CalendarUtil.getInstance().getYMDHMS();//时间
        JSONObject object = new JSONObject();
        try {
            object.put("userId", SharedPreUtils.create(BaseApplication.getApplication()).getString(SharedPreConfig.userId));
            object.put("musId",musId);
            object.put("vidName",nameVoice);
            object.put("vidInfo",conVoice);
            object.put("musName",muVoice);
            object.put("vidTime",ymdhms);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<File> fileArrayList = new ArrayList<>();
        fileArrayList.add(new File(audioPath));


        HttpParams httpParams = new HttpParams();
        httpParams.put("data",object.toString());

        showDialog(NetConfig.DialogUpMsg,false);
        OkGo.<String>post(NetConfig.Url.getBaseUrl(Api.uploadVideo)).params(httpParams).addFileParams("file", fileArrayList).tag(this).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dissDialog();
                String body = response.body();
                if(!TextUtils.isEmpty(body)){
                    UpVideoBean upVideoBean = new Gson().fromJson(body, UpVideoBean.class);
                    if(upVideoBean.isSuccess()){
                        Toast.makeText(UploadActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(UploadActivity.this, upVideoBean.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(UploadActivity.this, "录音上传失败,请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Response<String> response) {//服务器连接失败
                super.onError(response);
                Toast.makeText(UploadActivity.this, "服务器连接失败，请重试", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void endVoice(View view) {
        Toast.makeText(this, "录音已停止", Toast.LENGTH_SHORT).show();
        RecordHelper.getInstance().stop();
    }
}