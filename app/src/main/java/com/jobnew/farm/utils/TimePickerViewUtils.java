package com.jobnew.farm.utils;

import android.content.Context;
import android.view.Gravity;

import com.bigkoo.pickerview.TimePickerView;
import com.jobnew.farm.R;

import java.util.Calendar;

/**
 * Created by wufengqiao on 2017/6/13.
 * function：
 * desc：
 */

public class TimePickerViewUtils {

    private static TimePickerView.Builder pvTime;

    /**
     * 日期管理
     */
    public static TimePickerView.Builder getPvTime(Context context, TimePickerView.OnTimeSelectListener listener) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 1);
        boolean[] type = new boolean[]{true, true, true, false, false, false};
        pvTime = new TimePickerView.Builder(context, listener)
                .setType(type)//默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(18)//标题文字大小
                .setTitleText("日期选择")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(context.getResources().getColor(R.color.c_txt_25))//标题文字颜色
                .setSubmitColor(context.getResources().getColor(R.color.main_color))//确定按钮文字颜色
                .setCancelColor(context.getResources().getColor(R.color.c_txt_25))//取消按钮文字颜色
                .setTitleBgColor(0xFFFFFFFF)//标题背景颜色 Night mode
                .setBgColor(0xFFFFFFFF)//滚轮背景颜色 Night mode
                .setRangDate(startDate, selectedDate)//起始终止年月日设定
                .isDialog(false)//是否显示为对话框样式
                .gravity(Gravity.CENTER)
                .isCenterLabel(false);
        return pvTime;
    }

    public static void showDateSelect(Context context, TimePickerView.OnTimeSelectListener listener) {
        if (pvTime == null) {
            getPvTime(context, listener).build().show();
        }else {
            pvTime.build().show();
        }
    }

    public static void showNewDateSelect(Context context, TimePickerView.OnTimeSelectListener listener) {
        getPvTime(context, listener).build().show();
    }


}
