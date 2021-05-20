package com.example.maptest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maptest.base.BaseAtivity;
import com.example.maptest.util.SharedPreConfig;
import com.example.maptest.util.SharedPreUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyActivity extends BaseAtivity {
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.Personal)
    RelativeLayout Personal;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.bt_1)
    Button bt1;
    @BindView(R.id.evalution)
    RelativeLayout evalution;
    @BindView(R.id.tv_8)
    TextView tv8;
    @BindView(R.id.bt_2)
    Button bt2;
    @BindView(R.id.bt_3)
    Button bt3;
    @BindView(R.id.interpretation)
    RelativeLayout interpretation;
    @BindView(R.id.Set)
    Button Set;
    @BindView(R.id.setting)
    LinearLayout setting;
    private Button btns_set, btns_eva, btns_man, btns_audit;
    private ImageButton img_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);
        btns_set = findViewById(R.id.Set);
        btns_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//跳转到设置页面
                Intent intent = new Intent(MyActivity.this, SetActivity.class);
                startActivityForResult(intent, 100);
            }
        });
        btns_audit = findViewById(R.id.bt_3);
        btns_audit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//跳转审核管理界面
                Intent intent = new Intent(MyActivity.this, Auditstatus.class);
                intent.putExtra("type","1");
                startActivity(intent);
            }
        });
        btns_eva = findViewById(R.id.bt_1);
        btns_eva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//跳转评价管理界面
                Intent intent = new Intent(MyActivity.this, Evaluate.class);
                startActivity(intent);
            }
        });
        btns_man = findViewById(R.id.bt_2);
        btns_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//跳转投稿管理界面
                Intent intent = new Intent(MyActivity.this, Auditstatus.class);
                intent.putExtra("type","1");
                startActivity(intent);
            }
        });
        img_1 = findViewById(R.id.img_1);
        img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//跳转编辑资料界面
//                Intent intent = new Intent(MyActivity.this, Changeimg.class);
//                startActivity(intent);
            }
        });
        setBaseInfo();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            setBaseInfo();
        }
    }

    private void setBaseInfo() {
        String name = SharedPreUtils.create(MyActivity.this).getString(SharedPreConfig.userName, "-1");
        String sex = SharedPreUtils.create(MyActivity.this).getString(SharedPreConfig.userGender, "-1");
        String userLevel = SharedPreUtils.create(MyActivity.this).getString(SharedPreConfig.userLevel, "-1");
        Log.i("stf","--name--->"+name+"--sex-->"+sex+"--userLevel-->"+userLevel);
        if ("-1".equals(name)) {
            tv4.setText("名字");
        } else {
            tv4.setText(name);
        }

        if ("-1".equals(sex)) {
            tv5.setText("性别");
        } else {
            tv5.setText(sex);
        }

        if ("-1".equals(userLevel)) {
            tv6.setText("权限等级");
        } else {
            tv6.setText(userLevel.replace("1","普通用户").replace("2","管理员".replace("3","超级管理员")));
        }
    }
}