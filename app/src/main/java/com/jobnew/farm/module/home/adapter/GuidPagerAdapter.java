package com.jobnew.farm.module.home.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by wangwenlang on 2017/7/4.
 * title:
 * note:
 */

public class GuidPagerAdapter extends PagerAdapter {
    private ArrayList<RelativeLayout> imgs;
    public GuidPagerAdapter(ArrayList<RelativeLayout> imgs){
        this.imgs=imgs;
    }
    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imgs.get(position));
        return imgs.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgs.get(position));
    }
}
