package com.jobnew.farm.module.farm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.ShareUtilsActivity;
import com.jobnew.farm.module.farm.adapter.ScanPictureAdapter;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScanPictureActivity extends BaseActivity {

    @BindView(R.id.scan_pager)
    ViewPager scanPager;

    ArrayList<ImageView> imgs = new ArrayList<>();
    ScanPictureAdapter adapter;
    ArrayList<String> pictures;
    TitleBarHelper mytitleBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_scan_picture;
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mytitleBar=titleBar;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        pictures = intent.getStringArrayListExtra("pictures");
        Log.d(TAG, "initView: " + pictures.size() + "总数");
        for (String imgurl : pictures) {
            ImageView img = new ImageView(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            img.setLayoutParams(params);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(ScanPictureActivity.this).load(imgurl).dontAnimate().placeholder(R.drawable.big_picture_loading).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(img);
            //GlideManager.loadImg(imgurl, img);
            imgs.add(img);
        }
        adapter = new ScanPictureAdapter(imgs);
        scanPager.setAdapter(adapter);
        mytitleBar.setTitleMainText(1 + "/" + pictures.size());

        scanPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Log.d(TAG, "initView: " + pictures.size() + "zis总数");
                mytitleBar.setTitleMainText(position+1 + "/" + pictures.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
