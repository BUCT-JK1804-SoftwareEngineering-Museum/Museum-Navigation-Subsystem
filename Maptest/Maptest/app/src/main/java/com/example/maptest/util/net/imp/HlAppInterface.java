package com.example.maptest.util.net.imp;

import java.util.ArrayList;
import java.util.Map;

public interface HlAppInterface {

    interface OnHlResultListener<T> {

        void onSuccess(T bean);

        void onError(String msg);
    }

    interface OnHlImgResultListener<T> {

        void onSuccess(ArrayList<T> list);

        void onError(String msg);
    }



    interface OnQueryTokenListenter {
        void sucess(Map<String, String> map);

        void resultNull();
    }

    interface OnGetSpDataListener {
        void result(String msg);
    }


    interface OnGetDialogSelectDataListener {
        void result(ArrayList<String> nameList, ArrayList<String> idList);
    }

    interface OnGetDialogSelectDataListeners {
        void result(String msg);
    }

    interface OnGetDialogSelectMoreDataListeners {
        void result(String id, String msg);
    }

    interface OnGetDialogBusDataListeners {
        void result(String id, String type, String typeName);
    }

    interface OnSelectTimeListener<T> {
        void selectTime(String time);
    }

    interface OnLoadImgListener<T> {
        void isListNull();

        void sucess(T t);

        void error(String msg);
    }

    interface OnLoadMoreImgListener<T> {
        void isListNull();

        void sucess(ArrayList<T> t);

        void error(String msg);
    }


    interface OnInstallTokenListenter {
        void overTime();

        void resultNull();
    }

    interface OnOperateBtnListenter {
        void backBtnListener();

        void signonlyBtnListener();

        void dispatchBtnListener();

        void acceptBtnListenter();

        void moreBtnListenter();

    }

}
