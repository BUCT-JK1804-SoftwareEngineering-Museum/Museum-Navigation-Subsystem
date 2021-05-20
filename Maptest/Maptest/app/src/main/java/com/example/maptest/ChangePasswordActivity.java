package com.example.maptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maptest.base.BaseApplication;
import com.example.maptest.base.BaseAtivity;
import com.example.maptest.bean.ChangePwdBean;
import com.example.maptest.bean.LoginBean;
import com.example.maptest.bean.RegisterBean;
import com.example.maptest.util.SharedPreConfig;
import com.example.maptest.util.SharedPreUtils;
import com.example.maptest.util.net.Api;
import com.example.maptest.util.net.HlIntentDataUtil;
import com.example.maptest.util.net.NetConfig;
import com.example.maptest.util.net.OkGoNetUtil;
import com.example.maptest.util.net.imp.HlAppInterface;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.HashMap;

public class ChangePasswordActivity extends BaseAtivity {

    private Button mBtnsubmit;
    private EditText originalPasswordEd ,afterChangeEd,confirmpasswordEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        originalPasswordEd=findViewById(R.id.OriginalPassword);
        afterChangeEd=findViewById(R.id.AfterChange);
        confirmpasswordEd=findViewById(R.id.Confirmpassword);


        mBtnsubmit=findViewById(R.id.btn_Sumit);
        mBtnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMsg();
            }
        });
    }

    private void getMsg() {

        String originalPasswordMsg = originalPasswordEd.getText().toString();
        String afterChangeMsg = afterChangeEd.getText().toString();
        String confirmpassworMsg = confirmpasswordEd.getText().toString();

        if(TextUtils.isEmpty(originalPasswordMsg)){
            Toast.makeText(this, "请输入"+originalPasswordEd.getHint(), Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(afterChangeMsg)){
            Toast.makeText(this, "请输入"+afterChangeEd.getHint(), Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(confirmpassworMsg)){
            Toast.makeText(this, "请输入"+confirmpasswordEd.getHint(), Toast.LENGTH_SHORT).show();
            return;
        }


        if(!confirmpassworMsg.equals(afterChangeMsg)){
            Toast.makeText(this, "两次新密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }



        String url = NetConfig.Url.getBaseUrl(Api.changePassword);//调用接口
        showDialog(NetConfig.DialogUpMsg,false);//显示提示框

        JSONObject object = new JSONObject();
        try {
            object.put("oldPwd", originalPasswordMsg);
            object.put("newPwd", afterChangeMsg);
            object.put("userId", SharedPreUtils.create(BaseApplication.getApplication()).getString(SharedPreConfig.userId));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HlIntentDataUtil.postFormHeardEnqueue(ChangePwdBean.class, url+"/", object, null, new HlAppInterface.OnHlResultListener<ChangePwdBean>() {
            @Override
            public void onSuccess(ChangePwdBean bean) {
                dissDialog();
                if (bean.getSuccess()) {
                    Toast.makeText(ChangePasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    setResult(2);
                    finish();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String msg) {
                dissDialog();
                Toast.makeText(ChangePasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}