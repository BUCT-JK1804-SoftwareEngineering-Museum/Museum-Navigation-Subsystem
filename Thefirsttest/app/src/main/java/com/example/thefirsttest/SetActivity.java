package com.example.thefirsttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetActivity extends AppCompatActivity {
    private Button mtb_login;
    private Button mtb_register;
    private EditText mET_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        mtb_login=findViewById(R.id.btn_login);
        mtb_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
            }
        });
        mtb_register=findViewById(R.id.btn_register);
        mtb_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
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
    }
}