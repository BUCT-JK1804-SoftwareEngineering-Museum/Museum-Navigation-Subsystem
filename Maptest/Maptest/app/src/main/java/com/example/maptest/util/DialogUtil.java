package com.example.maptest.util;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtil {
    public ProgressDialog showBaseDialog(Context context, String msg, boolean isCancelable) {
        return ProgressDialog.show(context, "", msg, true, isCancelable);
    }
}
