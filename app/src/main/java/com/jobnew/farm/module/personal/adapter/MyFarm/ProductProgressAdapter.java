package com.jobnew.farm.module.personal.adapter.MyFarm;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.myfarm.ProductProgressEntity;
import com.jobnew.farm.utils.GlideManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wufengqiao on 2017/7/20.
 * function：
 * desc：
 */

public class ProductProgressAdapter extends BaseQuickAdapter<ProductProgressEntity,ProductProgressAdapter.ViewHolder> {

    private SimpleDateFormat format;
    private Context mContext;

    public ProductProgressAdapter(int layoutResId, List<ProductProgressEntity> data, Context context) {
        super(layoutResId, data);
        mContext = context;
        format = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    protected void convert(ViewHolder helper, ProductProgressEntity item) {
        GlideManager.loadImg(item.source.farmImg,helper.getView(R.id.farm_manager_img));
        helper.setText(R.id.farm_manager_tv, item.source.farmName);
        helper.setText(R.id.date_tv,format.format(item.createDate));
        helper.setText(R.id.content_tv, item.artProductName);
        helper.setText(R.id.operater_tv, "执行人：" + item.managerName);
        helper.addDatas(item.images);
    }

    public static class ViewHolder extends BaseViewHolder{

        private ImageAdapter mAdapter;
        private  RecyclerView rvImages;
        private List<String> mList;
        public ViewHolder(View view) {
            super(view);
            rvImages = (RecyclerView) view.findViewById(R.id.rv_images);
            rvImages.setLayoutManager(new GridLayoutManager(view.getContext(),3,LinearLayoutManager.VERTICAL,false));
            mList = new ArrayList<>();
            mAdapter = new ImageAdapter(R.layout.item_images,mList);
            rvImages.setAdapter(mAdapter);
        }
        public void addDatas(List<ProductProgressEntity.ImagesBean> list){
            mList.clear();
            for (ProductProgressEntity.ImagesBean imagesBean : list) {
                mList.add(imagesBean.imgUrl);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

}
