package com.example.maptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.maptest.base.BaseAtivity;

public class MapActivity2 extends BaseAtivity {
    private Button mbtnMap,mbtnmy;
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        dbHelper=new MyDatabaseHelper(this,"StudentStore.db",null,1);

        Button creatDatabase=findViewById(R.id.Home);
        creatDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        mbtnmy=findViewById(R.id.My);//跳转到我的
        mbtnmy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MapActivity2.this,MyActivity.class);
                startActivity(intent);
            }
        });
        mbtnMap=findViewById(R.id.Map);//跳转到地图
        mbtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MapActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}