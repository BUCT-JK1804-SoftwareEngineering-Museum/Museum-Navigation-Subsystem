package com.example.thefirsttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends AppCompatActivity {
    private Button btns_set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        btns_set=findViewById(R.id.Set);
        btns_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//跳转到设置页面
                Intent intent=new Intent(MyActivity.this,SetActivity.class);
                startActivity(intent);
            }
        });
    }
}