package com.jobnew.farm.module.farm.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.plan.OrderItemEntity;
import com.jobnew.farm.utils.GlideManager;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wufengqiao on 2017/6/28.
 * function：
 * desc：
 */

public class ConfirmOrderAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<OrderItemEntity> mList;
    private final DecimalFormat mDecimalFormat;


    public ConfirmOrderAdapter(Context context, List<OrderItemEntity> list) {
        mContext = context;
        mList = list;
        mDecimalFormat = new DecimalFormat("0.00");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 0:
            case 1:
            case 4:
                view = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_confirm_order_main, parent, false);
                viewHolder = new MainViewHolder(view);
                break;
            case 2:
            case 3:
                view = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_confirm_order_other, parent, false);
                viewHolder = new OtherViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OrderItemEntity entity = mList.get(position);
        switch (holder.getItemViewType()) {
            case 0:
                MainViewHolder mainViewHolder = (MainViewHolder) holder;
                GlideManager.loadImg(mList.get(position).thumbnail, mainViewHolder.ivProduct);
                mainViewHolder.tvProductName.setText(entity.name);
                mainViewHolder.tvHireTimeName.setText("租赁时长:");
                mainViewHolder.tvHireTime.setText(entity.rate + "天");
                mainViewHolder.tvCountName.setText("租赁面积");
                mainViewHolder.tvCount.setText(5 + entity.quantity + "㎡");
                mainViewHolder.tvUnitPrice.setText("¥" + mDecimalFormat.format(entity.price * entity.quantity * entity.rate));
                break;
            case 1:
                MainViewHolder mainViewHolder1 = (MainViewHolder) holder;
                GlideManager.loadImg(mList.get(position).thumbnail, mainViewHolder1.ivProduct);
                mainViewHolder1.tvProductName.setText(entity.name);
                mainViewHolder1.tvHireTimeName.setVisibility(View.GONE);
                mainViewHolder1.tvHireTime.setVisibility(View.GONE);
                mainViewHolder1.tvCountName.setText("种植数量");
                mainViewHolder1.tvCount.setText(entity.quantity + "");
                mainViewHolder1.tvUnitPrice.setText("¥" + mDecimalFormat.format(entity.price * entity.quantity));
                break;
            case 4:
                MainViewHolder mainViewHolder4 = (MainViewHolder) holder;
                GlideManager.loadImg(mList.get(position).thumbnail, mainViewHolder4.ivProduct);
                mainViewHolder4.tvProductName.setText(entity.name);
                mainViewHolder4.tvHireTimeName.setText(entity.cname);
                mainViewHolder4.tvHireTime.setVisibility(View.GONE);
                mainViewHolder4.tvCountName.setVisibility(View.GONE);
                mainViewHolder4.tvCount.setVisibility(View.GONE);
                mainViewHolder4.tvUnitPrice.setText("¥" + mDecimalFormat.format(entity.price * entity.quantity));
                break;
            case 2:
                OtherViewHolder otherViewHolder = (OtherViewHolder) holder;
                otherViewHolder.stvOther.setLeftString(entity.name);
                otherViewHolder.stvOther.setRightString("¥" + mDecimalFormat.format(entity.price * entity.quantity));
                break;
            case 3:
                OtherViewHolder addressViewHolder = (OtherViewHolder) holder;
                addressViewHolder.stvOther.setLeftString(entity.name);
                addressViewHolder.stvOther.setLeftTVColor(Color.parseColor("#888888"));
                break;
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).itemType;
    }


    public static class MainViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_product)
        ImageView ivProduct;
        @BindView(R.id.tv_product_name)
        TextView tvProductName;
        @BindView(R.id.tv_hire_time_name)
        TextView tvHireTimeName;
        @BindView(R.id.tv_hire_time)
        TextView tvHireTime;
        @BindView(R.id.tv_count_name)
        TextView tvCountName;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_unit_price)
        TextView tvUnitPrice;
        @BindView(R.id.tv_unit)
        TextView tvUnit;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class OtherViewHolder extends RecyclerView.ViewHolder {

        SuperTextView stvOther;

        public OtherViewHolder(View itemView) {
            super(itemView);
            stvOther = (SuperTextView) itemView.findViewById(R.id.stv_other);
        }
    }


}
