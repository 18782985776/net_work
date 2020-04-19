package com.jobnew.farm.module.farm.adapter.farmAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jobnew.farm.R;

import java.util.ArrayList;

    /**
     * Created by wangwenlang on 2017/5/31.
     * title:
     * note:
     */

    public class ShippingAddressManageAdpter extends RecyclerView.Adapter<ShippingAddressManageAdpter.ShipViewHolder> {
        Context context;
        LayoutInflater inflater;
        ArrayList arrayList;
        public ShippingAddressManageAdpter(Context context,ArrayList arrayList){
            this.arrayList=arrayList;
            this.context=context;
            inflater=LayoutInflater.from(context);
        }
        @Override
        public ShippingAddressManageAdpter.ShipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ShipViewHolder shipViewHolder=new ShipViewHolder(inflater.inflate(R.layout.shipping_adrdress_manage_item,parent,false));
            return shipViewHolder;
        }

        @Override
        public void onBindViewHolder(ShippingAddressManageAdpter.ShipViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return arrayList.size();

        }
        class  ShipViewHolder extends RecyclerView.ViewHolder{

            public ShipViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
