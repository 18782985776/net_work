package com.jobnew.farm.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by wangwenlang on 2017/6/8.
 * title:
 * note:
 */

public class SpannableStringUtil {
    private static SpannableStringUtil instance;
    public static SpannableStringUtil getInstance(){
        if(instance==null){
            instance=new SpannableStringUtil();
        }
        return instance;
    }
    public SpannableString getSpannableString(String s){
        SpannableString spannableString=new SpannableString(s);
        return  spannableString;
    }
    public void setSpanWithColor(String s, int color, int start, int end, TextView tv){
        ForegroundColorSpan span=new ForegroundColorSpan(color);
        SpannableString spannableString = getSpannableString(s);
        spannableString.setSpan(span,start,end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv.setText(spannableString);
    }
    /**sp分割oldString,返回SpannableString,并设置到textview**/
    public  void setSpannableStringToTextView(String oldString,String sp,int color,TextView textView) {
        if(!oldString.contains(sp)){
            FarmToastUtils.show("查询条件有误");
            return;
        }
        if (oldString.equals(sp)) {//如果字符串内容一致
            setSpanWithColor(oldString, color, 0, oldString.length(), textView);
        } else {//字符串内容不一样
            String[] split = oldString.split(sp);
            if (split.length == 2) {
                //说明关键字在返回值的中间
                setSpanWithColor(oldString, color, split[0].length(), split[0].length() + sp.length(), textView);
            } else if (split.length == 1) {
                //这里面说明是关键字在开头或者结尾
                if ((sp + split[0]).equals(oldString)) {//开头
                    setSpanWithColor(oldString, color, 0, sp.length(), textView);
                } else {//结尾
                    setSpanWithColor(oldString, color, split[0].length(), split[0].length() + sp.length(), textView);
                }
            }
        }
    }
}
