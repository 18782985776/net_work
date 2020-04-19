package com.jobnew.farm.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.MyApplication;


/**
 * CCreated by wufengqiao on 2017/5/17
 * Function:
 * Desc:
 */

public class FarmToastUtils {
    private static Toast toast;

    /**
     * 短时间显示Toast
     *
     * @param info 显示的内容
     */
    public static void show(String info) {
        if (TextUtils.isEmpty(info)) {
            return;
        }
        if (toast == null) {
            Activity current = AppManager.getCurrentActivity();
            if (current == null)
                toast = Toast.makeText(current, info, Toast.LENGTH_SHORT);
            else
                toast = Toast.makeText(MyApplication.getInstance(), info, Toast.LENGTH_SHORT);
        } else {
            toast.setText(info);
        }
        toast.setGravity(Gravity.CENTER, 20, 20);
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param info 显示的内容
     */
    public static void showLongToast(String info) {
        if (TextUtils.isEmpty(info)) {
            return;
        }
        if (toast == null) {
            Activity current = AppManager.getCurrentActivity();
            if (current == null)
                toast = Toast.makeText(current, info, Toast.LENGTH_LONG);
            else
                toast = Toast.makeText(MyApplication.getInstance(), info, Toast.LENGTH_LONG);
        } else {
            toast.setText(info);
        }
        toast.setGravity(Gravity.CENTER, 20, 20);
        toast.show();
    }
}
