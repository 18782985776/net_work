package com.jobnew.farm.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.TypedValue;

import com.aries.ui.widget.alert.UIAlertView;
import com.jobnew.farm.R;


/**
 * Created by wufengqiao on 2017/5/17
 * Function: UIAlertView封装
 * Desc:
 */
public class AlertUtil {


    public static UIAlertView show(Context context, int message, int right) {
        return show(context, 0, message, 0, right, null);
    }

    public static UIAlertView show(Context context, String message, String right) {
        return show(context, "", message, "", right, null);
    }

    public static UIAlertView show(Context context, String title, String message, String right) {
        return show(context, title, message, "", right, null);
    }

    public static UIAlertView show(Context context, int title, int message, int right) {
        return show(context, title, message, 0, right, null);
    }

    public static UIAlertView show(Context context, String message, String right, DialogInterface.OnClickListener listener) {
        return show(context, "", message, "", right, listener);
    }

    public static UIAlertView show(Context context, int message, int left, int right, DialogInterface.OnClickListener listener) {
        return show(context, 0, message, left, right, listener);
    }

    public static UIAlertView show(Context context, String message, String left, String right, DialogInterface.OnClickListener listener) {
        return show(context, "", message, left, right, listener);
    }

    public static UIAlertView show(Context context, int message, int right, DialogInterface.OnClickListener listener) {
        return show(context, 0, message, 0, right, listener);
    }

    public static UIAlertView show(Context context, int title, int message, int left, int right, DialogInterface.OnClickListener listener) {
        UIAlertView alert = getAlertView(context);
        if (title != 0) {
            alert.setTitle(title);
        }
        if (message != 0) {
            alert.setMessage(message);
        }
        if (left != 0) {
            alert.setNegativeButton(left, listener);
        }
        if (right != 0) {
            alert.setPositiveButton(right, listener);
        }
        alert.show();
        return alert;
    }

    public static UIAlertView show(Context context, String title, String message, String left, String right, DialogInterface.OnClickListener listener) {
        UIAlertView alert = getAlertView(context);
        if (!TextUtils.isEmpty(title)) {
            alert.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            alert.setMessage(message);
        }
        if (!TextUtils.isEmpty(left)) {
            alert.setNegativeButton(left, listener);
        }

        if (!TextUtils.isEmpty(right)) {
            alert.setPositiveButton(right, listener);
        }
        alert.show();
        return alert;
    }

    public static UIAlertView getAlertView(Context context) {
        UIAlertView alert = new UIAlertView(context).builder();
        alert.setTitleTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        alert.setButtonTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        alert.setButtonTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        alert.setMessageTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        alert.setTitleTextColor(context.getResources().getColor(R.color.c_txt_25));
        alert.setMessageTextColor(context.getResources().getColor(R.color.c_txt_25));
        alert.setNeutralButtonTextColor(context.getResources().getColor(R.color.AlertNegativeColor));
        alert.setNegativeButtonTextColor(context.getResources().getColor(R.color.c_txt_25));
        alert.setPositiveButtonTextColor(context.getResources().getColor(R.color.main_color));
        return alert;
    }
}
