package com.jobnew.farm.module.personal.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.LoginEntity;
import com.jobnew.farm.entity.UserPhotoBean;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.personal.adapter.UserHomeAdapter;
import com.jobnew.farm.module.personal.adapter.UserPhotoImageHolderView;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.CircleImageView;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wc on 2017/5/25.
 * Function：个人主页
 * desc：
 */

public class UserHomeActivity extends BaseActivity {
    @BindView(R.id.convenientBanner)
    ConvenientBanner banner;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.img_sex)
    ImageView imgSex;
    @BindView(R.id.txt_city)
    TextView txtCity;
    @BindView(R.id.img_avatar)
    CircleImageView imgAvatar;
    @BindView(R.id.txt_signature)
    TextView txtSignature;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    UserHomeAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_user_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("个人主页", true);
        initBanner();
        initRecyclerView();
    }

    private void initRecyclerView() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            data.add("22");
        }
        adapter = new UserHomeAdapter(R.layout.activity_user_home_item, data, mContext);
        LinearLayoutManager lm = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUser();
        initBannerData();
    }

    /**
     * 初始化个人资料
     */
    private void initUser() {
        LoginEntity.UserEntity user = MyApplication.user.getUser();
        if (!TextUtils.isEmpty(user.getName())) {
            txtName.setText(user.getName());
        }
        if (!TextUtils.isEmpty(user.getAvatar())) {
            Glide.with(mContext).load(user.getAvatar()).asBitmap().placeholder(R.mipmap.mine_icon_headportrait).error(R.mipmap.mine_icon_headportrait).into(imgAvatar);
        }
        if (!TextUtils.isEmpty(user.getSex())) {
            if (user.getSex().equals("2") || user.getSex().equals("女")) {
                imgSex.setBackgroundResource(R.mipmap.mine_woman);
            } else {
                imgSex.setBackgroundResource(R.mipmap.mine_man);
            }
        } else {
            imgSex.setBackgroundResource(R.mipmap.mine_man);
        }
        if (!TextUtils.isEmpty(user.getCity())) {
            txtCity.setText(user.getCity() + "");
        }
        if (!TextUtils.isEmpty(user.getSignature())) {
            txtSignature.setText(user.getSignature() + "");
        }
    }


    List<UserPhotoBean> list;

    /**
     * 初始化banner图
     */
    private void initBanner() {
        list = new ArrayList<>();
        banner.setPages(new CBViewHolderCreator<UserPhotoImageHolderView>() {
            @Override
            public UserPhotoImageHolderView createHolder() {
                return new UserPhotoImageHolderView();
            }
        }, list);
        banner.startTurning(5000);    //设置自动切换（同时设置了切换时间间隔）
        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                AppManager.jump(PhotoAlbumMngActivity.class);
            }
        });
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.jump(PhotoAlbumMngActivity.class);
            }
        });
    }

    /**
     * 初始化banner数据
     */
    private void initBannerData() {
        TestRepository.getIns().QueryUserPhoto(new HashMap<>()).subscribe(new DefaultSubscriber<BaseEntity<List<UserPhotoBean>>>(this, "", false) {
            @Override
            public void _onNext(BaseEntity<List<UserPhotoBean>> entity) {
                List<UserPhotoBean> data = entity.data;
                list.clear();
                if (data.toString().equals("[]")) {
                    list.add(new UserPhotoBean(-1, "add", System.currentTimeMillis()));
                } else {
                    list.addAll(data);
                }
                banner.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.setRightText("编辑");
        titleBar.setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.jump(UserInfoActivity.class);
            }
        });
    }

    @OnClick({R.id.ll_user1, R.id.img_add_dynamic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_user1:
                AppManager.jump(UserInfoActivity.class);
                break;
            case R.id.img_add_dynamic:
                AppManager.jump(ReleaseDynamicActivity.class);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
