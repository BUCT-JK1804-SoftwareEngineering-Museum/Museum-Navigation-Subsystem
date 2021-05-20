package com.example.maptest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maptest.base.BaseApplication;
import com.example.maptest.base.BaseAtivity;
import com.example.maptest.bean.LoginBean;
import com.example.maptest.util.SharedPreConfig;
import com.example.maptest.util.SharedPreUtils;
import com.example.maptest.util.net.Api;
import com.example.maptest.util.net.HlIntentDataUtil;
import com.example.maptest.util.net.NetConfig;
import com.example.maptest.util.net.OkGoConfig;
import com.example.maptest.util.net.OkGoNetUtil;
import com.example.maptest.util.net.imp.HlAppInterface;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SetActivity extends BaseAtivity {
    private Button mtb_login,btn_logou;
    private Button mtb_register;
    private EditText mET_username,passwordEd;
    private Button mBtn_accountsecurity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        passwordEd=findViewById(R.id.password_ed);
        mtb_login=findViewById(R.id.btn_login_1);
        btn_logou=findViewById(R.id.btn_logou);
        btn_logou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseApplication.getApplication().getActivityManage().finishAll();
            }
        });
        mtb_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoginInfo();
            }
        });

        mtb_register=findViewById(R.id.btn_register_1);
        mtb_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SetActivity.this,RegisterActivity.class);
                startActivityForResult(intent,100);
            }
        });


        mET_username=findViewById(R.id.username);
        mET_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("username",s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBtn_accountsecurity=findViewById(R.id.btn_AccountSecurity_1);
        mBtn_accountsecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到账户与安全演示界面
                Intent intent= new Intent(SetActivity.this,AccountsSecurityActivity.class);
                startActivityForResult(intent,101);
            }
        });

    }

    private void getLoginInfo() {
        String userName = mET_username.getText().toString();
        String password = passwordEd.getText().toString();
        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this, "请输入"+mET_username.getHint(), Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "请输入"+passwordEd.getHint(), Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject object = new JSONObject();
        try {
            object.put("username", userName);
            object.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = NetConfig.Url.getBaseUrl(Api.appLogin);
        showDialog(NetConfig.DialogUpMsg,false);

        HlIntentDataUtil.postFormHeardEnqueue(LoginBean.class, url+"/", object, null, new HlAppInterface.OnHlResultListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                dissDialog();
                if (bean.getSuccess()) {
                    SharedPreUtils.create(BaseApplication.getApplication()).putString(SharedPreConfig.userId,bean.getData().getUserId()+"");
                    SharedPreUtils.create(BaseApplication.getApplication()).putString(SharedPreConfig.userGender,bean.getData().getUserGender()+"");
                    SharedPreUtils.create(BaseApplication.getApplication()).putString(SharedPreConfig.userLevel,bean.getData().getUserLevel()+"");
                    SharedPreUtils.create(BaseApplication.getApplication()).putString(SharedPreConfig.userPallow,bean.getData().getUserPallow()+"");
                    SharedPreUtils.create(BaseApplication.getApplication()).putString(SharedPreConfig.userName,bean.getData().getUserName());
                    SharedPreUtils.create(BaseApplication.getApplication()).putString(SharedPreConfig.userPic,bean.getData().getUserPic());
                    Intent intent= new Intent(SetActivity.this,MapActivity2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SetActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String msg) {
                dissDialog();
                Toast.makeText(SetActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}