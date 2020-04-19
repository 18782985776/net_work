package com.jobnew.farm.module.home.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

import com.jobnew.farm.adapter.WheelViewAdapter;

/**
 * Created by wangwenlang on 2017/8/3.
 * title:
 * note:
 */

public class YearWheelAdapter implements WheelViewAdapter {
    @Override
    public int getItemsCount() {
        return 0;
    }

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getEmptyItem(View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }
}
