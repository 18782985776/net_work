package com.jobnew.farm.module.personal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.ShoppingCar.ShoppingProductEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.ProductDetailsActivity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wufengqiao on 2017/8/18.
 * function：
 * desc：
 */

public class ShoppingTrolleyAdapter extends BaseQuickAdapter<ShoppingProductEntity, ShoppingTrolleyAdapter.ViewHolder> {

    private Context context;
    private OnSelectChangeListener selectChangeListener;
    private OnItemDeleteListener onItemDeleteListener;


    public ShoppingTrolleyAdapter(int layoutResId, List<ShoppingProductEntity> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder helper, ShoppingProductEntity item) {

        helper.setText(R.id.tv_edit, item.isEditStatus ? "完成" : "编辑");
        helper.setText(R.id.tv_farm_name, item.farmName);
        GlideManager.loadRoundImg(item.farmLogo, helper.getView(R.id.iv_farm));
        helper.setNewData(item.list);
        helper.addOnClickListener(R.id.tv_edit);

        helper.getView(R.id.iv_farm_all_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.isSelect = !item.isSelect;
                item.setSelectAll(item.isSelect);
                helper.getView(R.id.iv_farm_all_select).setSelected(item.isSelect);
                helper.adapter.notifyDataSetChanged();
                selectChangeListener.onSelectChange(item.isSelect);
            }
        });
        helper.setSelectChangeListener(new OnSelectChangeListener() {
            @Override
            public void onSelectChange(boolean selected) {
                if (selected) {
                    if (checkAllSelect(item.list)) {
                        helper.getView(R.id.iv_farm_all_select).setSelected(true);
                        item.isSelect = true;
                    }
                } else {
                    helper.getView(R.id.iv_farm_all_select).setSelected(false);
                    item.isSelect = false;
                }
                selectChangeListener.onSelectChange(selected);
            }
        });
        helper.getView(R.id.iv_farm_all_select).setSelected(item.isSelect);
        helper.setOnItemDeleteListener(new OnItemDeleteListener() {
            @Override
            public void onItemDeleterListener(int position, int parentPostion, BaseQuickAdapter adapter) {
                onItemDeleteListener.onItemDeleterListener(position, helper.getLayoutPosition(), adapter);
            }
        });
    }

//    /**
//     * 通知编辑状态改变
//     */
//    public void notifyEditStatusChange() {
//        isEditStatusRefresh = true;
//        notifyDataSetChanged();
//        isEditStatusRefresh = false;
//    }
//
//    public void notifyItemEditStatusChange(int position) {
//        isEditStatusRefresh = true;
//        notifyItemChanged(position);
//        isEditStatusRefresh = false;
//    }

    //检查同一个农场下商品是否都同时选择
    public boolean checkAllSelect(List<ShoppingProductEntity> list) {
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isSelect) {
                return false;
            }
        }
        return true;
    }

//    /**
//     * 通知选择状态改变
//     */
//    public void notifySelectChange() {
//        isSelectStatus = true;
//        notifyDataSetChanged();
//        isSelectStatus = false;
//    }

    /**
     * 设置选择状态改变 监听
     *
     * @param listener
     */
    public void setSelectChangeListener(OnSelectChangeListener listener) {
        selectChangeListener = listener;
    }

    /**
     * 选择状态改变 监听 接口
     */
    public interface OnSelectChangeListener {
        void onSelectChange(boolean selected);
    }

    public interface OnItemDeleteListener {
        void onItemDeleterListener(int position, int parentPostion, BaseQuickAdapter adapter);

    }

    public void setOnItemDeleteListener(OnItemDeleteListener listener) {
        onItemDeleteListener = listener;
    }

    /**
     * ViewHolder类
     */

    public class ViewHolder extends BaseViewHolder {

        public RecyclerView recyclerView;
        public shoppingTrolleyProductAdapter adapter;
        public List<ShoppingProductEntity> list;
        private OnSelectChangeListener mSelectListener;
        private OnItemDeleteListener mOnItemDeleteListener;

        public ViewHolder(View view) {
            super(view);
            recyclerView = (RecyclerView) view.findViewById(R.id.rv_product);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            list = new ArrayList();
            adapter = new shoppingTrolleyProductAdapter(R.layout.item_shopping_trolley_product, list);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()) {
                        case R.id.iv_select:
                            List<ShoppingProductEntity> list = adapter.getData();
                            list.get(position).isSelect = !list.get(position).isSelect;
                            view.setSelected(list.get(position).isSelect);
                            mSelectListener.onSelectChange(list.get(position).isSelect);
                            break;
                        case R.id.iv_delete:
                            mOnItemDeleteListener.onItemDeleterListener(position, 0, adapter);
                            break;
                    }
                }
            });

        }


        public void setNewData(List<ShoppingProductEntity> list) {
            adapter.setNewData(list);
        }

        /**
         * 设置选择改变监听
         *
         * @param listener
         */
        public void setSelectChangeListener(OnSelectChangeListener listener) {
            mSelectListener = listener;
        }

        public void setOnItemDeleteListener(OnItemDeleteListener listener) {
            mOnItemDeleteListener = listener;
        }
    }


}
