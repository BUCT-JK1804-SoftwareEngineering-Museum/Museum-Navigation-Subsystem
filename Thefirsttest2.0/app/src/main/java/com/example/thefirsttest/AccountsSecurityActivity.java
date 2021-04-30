package com.example.thefirsttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountsSecurityActivity extends AppCompatActivity {
    private Button mBtnchangepassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_security);

        mBtnchangepassword=findViewById(R.id.btn_Changepassword);
        mBtnchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到更改密码演示界面
                Intent intent= new Intent(AccountsSecurityActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}