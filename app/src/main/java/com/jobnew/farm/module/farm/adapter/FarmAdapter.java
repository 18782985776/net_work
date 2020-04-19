package com.jobnew.farm.module.farm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.entity.base.FarmEntity;
import com.jobnew.farm.utils.StarLinearLayout;

import java.util.ArrayList;

/**
 * Created by wangwenlang on 2017/5/23.
 * title:
 * note:
 */

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.ViewHolder>{
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    private OnItemClickListener mOnItemClickListener;
    LayoutInflater inflater;
   public ArrayList<FarmEntity> farmEntities;
    Context context;
    public FarmAdapter(Context context,ArrayList<FarmEntity> farmEntities){
        inflater=LayoutInflater.from(context);
        this.farmEntities=farmEntities;
        this.context=context;
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView farmlistview_item_img;
        TextView farmAdreestv;
        TextView storeTv;
        TextView discussTv;
        TextView farm_nametv;
        TextView distanceTv;
        StarLinearLayout star_score;

        public ViewHolder(View itemView) {
            super(itemView);
            farmlistview_item_img= (ImageView) itemView.findViewById(R.id.farmlistview_item_img);
            discussTv= (TextView) itemView.findViewById(R.id.discussTv);
            distanceTv= (TextView) itemView.findViewById(R.id.distanceTv);
            farm_nametv= (TextView) itemView.findViewById(R.id.farm_nametv);
            star_score= (StarLinearLayout) itemView.findViewById(R.id.star_score);
            farmAdreestv= (TextView) itemView.findViewById(R.id.farmAdreestv);
            storeTv= (TextView) itemView.findViewById(R.id.storeTv);
        }

    }
    @Override
    public FarmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder=null;
        return new ViewHolder( inflater.inflate(R.layout.listview_farm_item,parent,false));
    }

    @Override
    public void onBindViewHolder(FarmAdapter.ViewHolder holder, int position) {
        //判断是否设置了监听器
        if(mOnItemClickListener != null){//
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // int position = holder.getLayoutPosition(); // 1
                    Log.d("位置",position+"");
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return farmEntities.size();
    }

}




//    public ArrayList<FarmEntity> arrayList;
//    LayoutInflater inflater;
//    public FarmAdapter(ArrayList arrayList, Context context){
//        inflater=LayoutInflater.from(context);
//        this.arrayList=arrayList;
//    }
//    @Override
//    public int getCount() {
//        return arrayList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return arrayList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder=null;
//        if(convertView==null){
//            holder = new ViewHolder();
//            //根据自定义的Item布局加载布局
//            convertView = inflater.inflate(R.layout.listview_farm_item, null);
//            holder.farmlistview_item_img= (ImageView) convertView.findViewById(R.id.farmlistview_item_img);
//            holder.discussTv= (TextView) convertView.findViewById(R.id.discussTv);
//            holder.distanceTv= (TextView) convertView.findViewById(R.id.distanceTv);
//            holder.farm_nametv= (TextView) convertView.findViewById(R.id.farm_nametv);
//            holder.star_score= (StarLinearLayout) convertView.findViewById(R.id.star_score);
//            holder.farmAdreestv= (TextView) convertView.findViewById(R.id.farmAdreestv);
//            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
//            convertView.setTag(holder);
//        }else {
//            holder= (ViewHolder) convertView.getTag();
//        }
//        //Todo这里设置数据
//
//        return convertView;
//    }
//    private class ViewHolder{
//        public ImageView farmlistview_item_img;
//        public TextView farmAdreestv;
//        public TextView storeTv;
//        public TextView discussTv;
//        public TextView farm_nametv;
//        public TextView distanceTv;
//        public StarLinearLayout star_score;
//    }
