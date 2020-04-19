package com.jobnew.farm.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.utils.ScreenToolsUtils;

/**
 * Created by wufengqiao on 2017/5/22.
 */

public class TitleBarHelper {
    View mainView;
    private TextView mLeftTv;
    private TextView mTitleTv;
    private TextView mRightTv;
    private ImageView mRightIv;

    public TitleBarHelper(@NonNull View view){
        this.mainView = view;
    }



    /**
     * 设置左边文字
     * @param title
     */
    public void setLeftText(CharSequence title) {
        checkLeftTextView();
        mLeftTv.setVisibility(View.VISIBLE);
        this.mLeftTv.setText(title);
    }

    public void setLeftText(int id) {
        checkLeftTextView();
        mLeftTv.setVisibility(View.VISIBLE);
        this.mLeftTv.setText(id);
    }
    /**
     * @param other    左边textView文字
     * @param listener 监听器
     */
    public void setLeftText(String other, View.OnClickListener listener) {
        if (other.isEmpty()) return;
        setLeftText(other);
        if (listener != null) {
            mLeftTv.setOnClickListener(listener);
        }
    }

    /**
     * @param drawableId    左边图片资源
     * @param listener 监听器
     */
    public void setLeftTextDrawable(int drawableId, View.OnClickListener listener) {
        if (drawableId == 0) return;
        setLeftTextDrawable(drawableId);
        if (listener != null) {
            mLeftTv.setOnClickListener(listener);
        }
    }

    public void setLeftTextSize(int unit, float size) {
        checkLeftTextView();
        this.mLeftTv.setTextSize(unit, size);
    }

    /**
     * 检查左边textView控件
     */
    private void checkLeftTextView(){
        if (mLeftTv == null){
            mLeftTv = (TextView) mainView.findViewById(R.id.tv_title_left);
        }
    };

    /**
     *
     * @param size
     */
    public void setLeftTextSize(float size) {
        checkLeftTextView();
        this.mLeftTv.setTextSize(size);
    }

    public void setLeftTextColor(int id) {
        checkLeftTextView();
        this.mLeftTv.setTextColor(id);
    }

    public void setLeftTextColor(ColorStateList color) {
        checkLeftTextView();
        try {
            this.mLeftTv.setTextColor(color);
        } catch (Exception var3) {
            ;
        }

    }

    public void setLeftTextBackgroundColor(int color) {
        checkLeftTextView();
        this.mLeftTv.setBackgroundColor(color);
    }

    public void setLeftTextBackgroundResource(int id) {
        checkLeftTextView();
        this.mLeftTv.setBackgroundResource(id);
    }

    public void setLeftTextDrawable(int id, int drawablePadding) {
        checkLeftTextView();
        this.mLeftTv.setVisibility(View.VISIBLE);
        this.mLeftTv.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0);
        this.setLeftTextDrawablePadding(drawablePadding);
    }

    /**
     * @param drawablePadding  单位为dp
     */
    public void setLeftTextDrawablePadding(int drawablePadding) {
        checkLeftTextView();
        mLeftTv.setVisibility(View.VISIBLE);
        this.mLeftTv.setCompoundDrawablePadding(ScreenToolsUtils.dp2px(drawablePadding));
    }

    /**
     * 设置title左边图片
     * @param drawable 图片资源
     */
    public void setLeftTextDrawable(Drawable drawable){
        checkLeftTextView();
        mLeftTv.setCompoundDrawables(null,null,drawable,null);
    }

    /**
     * 设置title左边图片
     * @param drawableId
     */
    public void setLeftTextDrawable(int drawableId){
        checkLeftTextView();
        mLeftTv.setVisibility(View.VISIBLE);
        mLeftTv.setCompoundDrawablesWithIntrinsicBounds(0,0,drawableId,0);
    }

    public void setLeftTextPadding(int left, int top, int right, int bottom) {
        checkLeftTextView();
        this.mLeftTv.setPadding(left, top, right, bottom);
    }

    public void setOnLeftTextClickListener(View.OnClickListener l) {
        checkLeftTextView();
        this.mLeftTv.setOnClickListener(l);
    }

    public void setLeftVisible(boolean visible) {
        checkLeftTextView();
        this.mLeftTv.setVisibility(visible ? View.VISIBLE:View.GONE);
    }


    /**
     * 设置标题
     * @param title
     */
    public void setTitleMainText(CharSequence title) {
        checkTitleMainTextView();
        mTitleTv.setVisibility(View.VISIBLE);
        this.mTitleTv.setText(title);
    }


    /**
     *  检查标题控件
     */
    private void checkTitleMainTextView(){
        if (mTitleTv == null){
            mTitleTv = (TextView) mainView.findViewById(R.id.tv_title);
        }
    };
    public void setTitleMainText(int id) {
        checkLeftTextView();
        this.mTitleTv.setVisibility(View.VISIBLE);
        this.mTitleTv.setText(id);
    }

    public void setTitleMainTextSize(float titleMainTextSpValue) {
        checkLeftTextView();
        this.mTitleTv.setTextSize(titleMainTextSpValue);
    }

    public void setTitleMainTextSize(int unit, float titleMainTextSpValue) {
        checkLeftTextView();
        this.mTitleTv.setTextSize(unit, titleMainTextSpValue);
    }

    public void setTitleMainTextColor(int id) {
        checkLeftTextView();
        this.mTitleTv.setTextColor(id);
    }

    public void setTitleMainTextBackgroundColor(int color) {
        checkLeftTextView();
        this.mTitleTv.setBackgroundColor(color);
    }

    public void setTitleMainTextBackgroundResource(int id) {
        checkLeftTextView();
        this.mTitleTv.setBackgroundResource(id);
    }

    public void setTitleMainTextFakeBold(boolean isFakeBold) {
        checkLeftTextView();
        mTitleTv.getPaint().setFakeBoldText(isFakeBold);
    }

    public void setTitleMainTextPadding(int left, int top, int right, int bottom) {
        checkLeftTextView();
        this.mTitleTv.setPadding(left, top, right, bottom);
    }


    /**
     *   检查右侧控件
     */
    private void checkRightTextView(){
        if (mRightTv == null){
            mRightTv = (TextView) mainView.findViewById(R.id.tv_title_right);
        }
    };
    public void setRightText(CharSequence title) {
        checkRightTextView();
        this.mRightTv.setVisibility(View.VISIBLE);
        this.mRightTv.setText(title);
    }

    public void setRightText(int id) {
        checkRightTextView();
        this.mRightTv.setVisibility(View.VISIBLE);
        this.mRightTv.setText(id);
    }
    /**
     * @param other    右边textView文字
     * @param listener 监听器
     */
    public void setRightText(String other, View.OnClickListener listener) {
        if (other.isEmpty()) return;
        setRightText(other);
        if (listener != null) {
            mRightTv.setOnClickListener(listener);
        }
    }

    /**
     * @param drawableId    右边图片资源
     * @param listener 监听器
     */
    public void setRightTextDrawable(int drawableId, View.OnClickListener listener) {
        if (drawableId == 0) return;
        setRightTextDrawable(drawableId);
        if (listener != null) {
            mRightTv.setOnClickListener(listener);
        }
    }


    public void setRightTextSize(int unit, float size) {
        checkRightTextView();
        this.mRightTv.setTextSize(unit, size);
    }

    public void setRightTextSize(float size) {
        checkRightTextView();
        this.mRightTv.setTextSize(size);
    }

    public void setRightTextColor(int id) {
        checkRightTextView();
        this.mRightTv.setTextColor(id);
    }

    public void setRightTextColor(ColorStateList color) {
        checkRightTextView();
        try {
            this.mRightTv.setTextColor(color);
        } catch (Exception var3) {
            ;
        }

    }

    public void setRightTextBackgroundColor(int color) {
        checkRightTextView();
        this.mRightTv.setBackgroundColor(color);
    }

    public void setRightTextBackgroundResource(int id) {
        checkRightTextView();
        this.mRightTv.setBackgroundResource(id);
    }

    public void setRightTextDrawable(int id, int drawablePadding) {
        checkRightTextView();
        this.mRightTv.setVisibility(View.VISIBLE);
        this.mRightTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, id, 0);
        this.setRightTextDrawablePadding(drawablePadding);
    }

    public void setRightTextDrawablePadding(int drawablePadding) {
        checkRightTextView();
        this.mRightTv.setCompoundDrawablePadding(ScreenToolsUtils.dp2px(drawablePadding));
    }

    public void setRightTextDrawable(int id) {
        checkRightTextView();
        this.mRightTv.setVisibility(View.VISIBLE);
        mRightTv.setCompoundDrawablesWithIntrinsicBounds(0,0,id,0);
    }

    public void setRightTextPadding(int left, int top, int right, int bottom) {
        checkRightTextView();
        this.mRightTv.setPadding(left, top, right, bottom);
    }

    public void setOnRightTextClickListener(View.OnClickListener l) {
        checkRightTextView();
        this.mRightTv.setOnClickListener(l);
    }

    public void setRightVisible(boolean visible) {
        checkRightTextView();
        this.mRightTv.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void checkRightImageView(){
        if (mRightIv == null){
            mRightIv = (ImageView) mainView.findViewById(R.id.iv_title_right);
        }
    }
    /**
     * @param drawableId    右边图片资源
     * @param listener 监听器
     */
    public void setRightImageView(int drawableId, View.OnClickListener listener) {
        if (drawableId == 0) return;
        checkRightImageView();
        mRightIv.setVisibility(View.VISIBLE);
        mRightIv.setImageResource(drawableId);
        if (listener != null) {
            mRightIv.setOnClickListener(listener);
        }
    }

    public void setRightImageView(int drawableId) {
        if (drawableId == 0) return;
        checkRightImageView();
        mRightIv.setVisibility(View.VISIBLE);
        mRightIv.setImageResource(drawableId);
    }

    public void setOnRightImageClickListener(View.OnClickListener l) {
        checkRightTextView();
        this.mRightIv.setOnClickListener(l);
    }

    /**
     * 得到右边第二个子空间 imageView
     * @return
     */
    public ImageView getRightImageView(){
        checkRightImageView();
        return mRightIv;
    }
    /**
     * 得到右边第一个子控件TextView
     */
    public TextView getRightTextView(){
        checkRightTextView();
        return mRightTv;
    }

    /**
     * 得到左边第一个子控件TextView
     * @return
     */
    public TextView getLeftTextView(){
        checkLeftTextView();
        return mLeftTv;

    }

}
