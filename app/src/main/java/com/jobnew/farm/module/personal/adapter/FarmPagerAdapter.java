package com.jobnew.farm.module.personal.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 *
 */

public class FarmPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private List<Fragment> fragments;

    public FarmPagerAdapter(FragmentManager fm, List<Fragment>fragments, String[]mStrings) {
        super(fm);
        this.titles = mStrings;
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
