package com.jobnew.farm.module.farm.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by wangwenlang on 2017/7/17.
 * title:
 * note:
 */

public class ScanPictureAdapter extends PagerAdapter {
  private ArrayList<ImageView> imgs;
    public ScanPictureAdapter(ArrayList<ImageView> imgs){
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
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgs.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imgs.get(position));
        return imgs.get(position);
    }
}
