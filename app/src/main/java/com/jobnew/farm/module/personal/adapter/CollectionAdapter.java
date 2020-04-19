package com.jobnew.farm.module.personal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jobnew.farm.R;

import java.util.ArrayList;

/**
 * Created by wangwenlang on 2017/6/15.
 * title:
 * note:
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {
    private ArrayList<Object> arrayList;
    private LayoutInflater inflater;
    public CollectionAdapter(ArrayList<Object> arrayList, Context context){
        this.arrayList=arrayList;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder=null;
        holder=new ViewHolder(inflater.inflate(R.layout.item_collection,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
       // if(arrayList.get(position)==)
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
       private  LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            layout= (LinearLayout) itemView.findViewById(R.id.item_c_layout);
        }

    }
    private ItemListener itemListener;
    public void setItemListener(ItemListener itemListener){
        this.itemListener=itemListener;
    }
    interface ItemListener{
        void Click();
    }
}
