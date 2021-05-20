package com.example.maptest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baidu.mapapi.map.MapViewLayoutParams;
import com.example.maptest.base.BaseApplication;
import com.example.maptest.base.BaseAtivity;
import com.example.maptest.bean.RegisterBean;
import com.example.maptest.util.SharedPreConfig;
import com.example.maptest.util.SharedPreUtils;
import com.example.maptest.util.net.Api;
import com.example.maptest.util.net.NetConfig;
import com.example.maptest.util.net.OkGoNetUtil;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseAtivity {
    EditText usernameEd;
    EditText passwordEd;
    EditText userEd;
    private Button mBtnRegister;
    private RadioGroup radioGroup, radioGroupSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mBtnRegister = findViewById(R.id.btn_Aregister);
        usernameEd = findViewById(R.id.username_ed);
        passwordEd = findViewById(R.id.password_ed);
        userEd = findViewById(R.id.user_ed);
        radioGroup = findViewById(R.id.rg);
        radioGroupSex = findViewById(R.id.rg1);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMsg();
            }
        });
    }


    private void getMsg() {
        String mUserPhone = usernameEd.getText().toString();
        String mUserPassword = passwordEd.getText().toString();
        String mUserName = userEd.getText().toString();
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        int checkedRadioButtonIdSex = radioGroupSex.getCheckedRadioButtonId();
        int flag = -1;
        int flagSex = -1;

        if (checkedRadioButtonIdSex == R.id.sex_rb) {
            flagSex = 1;
        }

        if (checkedRadioButtonIdSex == R.id.sex_rb2) {
            flagSex = 2;
        }

        if (checkedRadioButtonId == R.id.rb_rb) {
            flag = 1;
        }

        if (checkedRadioButtonId == R.id.rb_rb2) {
            flag = 2;
        }

        if (checkedRadioButtonId == R.id.rb_rb3) {
            flag = 3;
        }

        if (flagSex == -1) {
            Toast.makeText(this, "请输入性别", Toast.LENGTH_SHORT).show();
            return;
        }

//        if (flag == -1) {
//            Toast.makeText(this, "请输入选择角色", Toast.LENGTH_SHORT).show();
//            return;
//        }

        if (TextUtils.isEmpty(mUserPhone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(mUserPassword)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(mUserName)) {
            Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> hashMap = new HashMap<>();
        String sex=  flagSex+"";
        hashMap.put("userName", mUserName);
        hashMap.put("userPassword", mUserPassword);
        hashMap.put("userPhone", mUserPhone);
        hashMap.put("userLevel", "1");
        hashMap.put("userPallow", "1");
        hashMap.put("userGender",sex.replace("1","男").replace("2","女"));
        String url = NetConfig.Url.getBaseUrl(Api.register);
        showDialog(NetConfig.DialogUpMsg, false);

        OkGoNetUtil.postParamBean(RegisterBean.class, url, hashMap, new OkGoNetUtil.OnResultListener<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean bean, String json) {
                dissDialog();
                if (bean.isSuccess()) {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String e) {
                dissDialog();
                Toast.makeText(RegisterActivity.this, e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onConnectFail(String msg, Response<String> response) {
                dissDialog();
                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOutApp(String json) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
