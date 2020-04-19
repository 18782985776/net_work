package com.jobnew.farm.module.personal.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.jobnew.farm.BuildConfig;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.UserPhotoBean;

/**
 * Created by wangcheng on 2017/5/8.
 * title：轮播图类
 * note：
 */

public  class UserPhotoImageHolderView implements Holder<UserPhotoBean> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, UserPhotoBean data) {
        imageView.setImageResource(R.mipmap.ic_launcher);
        if (data.getId()==-1){
            Glide.with(context).load(R.mipmap.mine_position).placeholder(R.mipmap.mine_position).error(R.mipmap.mine_position).into(imageView);
        }else {
            Glide.with(context).load(data.getUrl()).placeholder(R.mipmap.ic_default).error(R.mipmap.ic_error).into(imageView);
        }
    }
}
