package com.example.maptest.base;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.maptest.util.DialogUtil;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseAtivity extends FragmentActivity {
    Unbinder unbinder = null;
    DialogUtil dialogUtil = null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.getApplication().getActivityManage().addActivity(this);
        dialogUtil = new DialogUtil();
    }

    public void showDialog(String msg){
        if(dialogUtil == null){
            dialogUtil = new DialogUtil();
        }
        progressDialog = dialogUtil.showBaseDialog(this, msg, true);
    }

    public void showDialog(String msg,boolean isCancelable){
        if(dialogUtil == null){
            dialogUtil = new DialogUtil();
        }
        progressDialog = dialogUtil.showBaseDialog(this, msg, isCancelable);
    }

    public void dissDialog(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        dissDialog();
    }
}
