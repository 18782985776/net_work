package com.jobnew.farm.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jobnew.farm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwenlang on 2017/5/23.
 * title:
 * note:
 */


public class StarLinearLayout extends LinearLayout implements View.OnClickListener {
    private List<ImageView> stars = new ArrayList<ImageView>();
    private int mMargin = 5;
    private boolean isEdit;

    public StarLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.star);

            mMargin = (int) a.getInteger(R.styleable.star_margin, 5);
            isEdit = a.getBoolean(R.styleable.star_isEdit, false);
            mScore = a.getFloat(R.styleable.star_score, 0);
            a.recycle();
        }
        init();
        setScore(mScore);
    }

    public StarLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public StarLinearLayout(Context context) {
        this(context, null);
    }

    private void init() {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        params.rightMargin = mMargin;
        for (int i = 0; i < 5; i++) {
            ImageView star = new ImageView(getContext());
            star.setImageResource(R.mipmap.farm_icon_grade1);
            stars.add(star);
            addView(star, params);
            star.setOnClickListener(this);
        }
    }

    private float mScore = 0;

    public void setScore(float score) {
        if (score < 0 || score > 5) score = 0;
        mScore = score;
        setStar(((int) (10 * score)) / 5);
    }

    public float getScore() {
        return mScore;
    }

    private void setStar(int level) {
        int i = 0;
        for (i = 0; i < level / 2; i++) {//设置整数段的选中有色星号
            stars.get(i).setImageResource(R.mipmap.farm_icon_grade1);
        }
        if (level % 2 > 0) {//设置小数端的有色星号部分
            stars.get(i).setImageResource(R.mipmap.ic_star_select_half);
            i++;
        }
        for (; i < stars.size(); i++) {//设置没有颜色星号部分
            stars.get(i).setImageResource(R.mipmap.farm_icon_grade2);
        }
    }

    @Override
    public void onClick(View v) {
        if (stars.contains(v)) {
            if (!isEdit) return;
            int index = stars.indexOf(v);
            setScore(index + 1);
            changeLis.Change(index + 1);
        }
    }


    ChangeListener changeLis;

    // 为每个接口设置监听器
    public void setChangeListener(ChangeListener change) {
        this.changeLis = change;
    }

    public interface ChangeListener{

        void Change(int level);

    }
}