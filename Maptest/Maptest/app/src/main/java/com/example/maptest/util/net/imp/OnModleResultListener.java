package com.example.maptest.util.net.imp;

import com.lzy.okgo.model.Response;

public interface OnModleResultListener<T> {
    void onSuccess(T bean);

    void onError(String e);

    void onConnectFail(String msg, Response<String> response);


}
