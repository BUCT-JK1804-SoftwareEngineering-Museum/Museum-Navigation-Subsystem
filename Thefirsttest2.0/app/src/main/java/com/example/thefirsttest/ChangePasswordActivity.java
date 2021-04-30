package com.example.thefirsttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {

    private Button mBtnsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mBtnsubmit=findViewById(R.id.btn_Sumit);
        mBtnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ChangePasswordActivity.this,"更改成功 重新登录", Toast.LENGTH_SHORT).show();
                //跳转到登录界面
                Intent intent= new Intent(ChangePasswordActivity.this,SetActivity.class);
                startActivity(intent);
            }
        });
    }
}