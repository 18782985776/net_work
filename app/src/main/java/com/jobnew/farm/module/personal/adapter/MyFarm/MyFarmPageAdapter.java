package com.jobnew.farm.module.personal.adapter.MyFarm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wufengqiao on 2017/6/13.
 * function：
 * desc：
 */

public class MyFarmPageAdapter extends FragmentPagerAdapter {
    List<Fragment> mList;
    String[] strings;
    public MyFarmPageAdapter(FragmentManager fm, List<Fragment> list, String[] strings) {
        super(fm);
        mList = list;
        this.strings = strings;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }

    @Override
    public int getCount() {
        return strings.length;
    }
}
