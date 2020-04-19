package com.jobnew.farm.module.home.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.GuidEntity;
import com.jobnew.farm.module.home.adapter.GuidPagerAdapter;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.utils.SPUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuidActivity extends BaseActivity {

    @BindView(R.id.guid_pager)
    ViewPager guidPager;
    ArrayList<RelativeLayout> imgs;
    LayoutInflater inflater;

    @Override
    protected int getLayout() {
        return R.layout.activity_guid;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        inflater = LayoutInflater.from(this);
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        imgs=new ArrayList<>();
        initGuidImage();
        guidPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position== imgs.size()){

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initGuidImage() {
        MyFarmRepository.getIns().getGuidInfo(new HashMap<>()).subscribe(new DefaultSubscriber<GuidEntity>(this,false) {
            @Override
            public void _onNext(GuidEntity entity) {
//                Log.d(TAG, "_onNext:引导 "+entity.data.toString());
                for (int i=0;i<entity.getImgs().size();i++){
                   // ImageView img=new ImageView(GuidActivity.this);
                    imgs.add((RelativeLayout) inflater.inflate(R.layout.layout_guide,null));
                    if(i==entity.getImgs().size()-1){
                        imgs.get(i).getChildAt(1).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                              showMsg("开始浏览");
                            }
                        });
                    }
                }
                int guideVersion = SPUtils.get("guideVersion", 123);
                if(guideVersion==entity.getGuideVersion()){//如果相等
                    for (int i=0;i<entity.getImgs().size();i++){
                       ImageView img= (ImageView) imgs.get(i).getChildAt(0);
                        GlideManager.loadImg(entity.getImgs().get(i).getImgUrl(),img);
                    }
                }else {//如果不相等
                    for (int i=0;i<entity.getImgs().size();i++){
                        ImageView img= (ImageView) imgs.get(i).getChildAt(0);
                        GlideManager.loadImg(entity.getImgs().get(i).getImgUrl(),img);
                    }
                    SPUtils.put("guideVersion",entity.getGuideVersion());
                }
                GuidPagerAdapter adapter=new GuidPagerAdapter(imgs);
                guidPager.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        ButterKnife.bind(this);
    }
}
