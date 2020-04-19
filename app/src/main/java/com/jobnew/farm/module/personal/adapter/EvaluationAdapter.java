package com.jobnew.farm.module.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jobnew.farm.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xiaosa on 2016/11/1.
 */

public class EvaluationAdapter extends BaseAdapter {
    private List<String> lists;
    private Context mContext;
    private LayoutInflater inflater;
    private int max;

    public EvaluationAdapter(List<String> data, Context context, int max) {
        this.lists = data;
        this.mContext = context;
        this.max = max;
        inflater= LayoutInflater.from(context);
    }
    @Override
    public int getCount(){
        if (lists.size()==max+1){
            return max;
        }
        return lists.size();
    }
    @Override
    public String getItem(int position) {
        return lists.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.activity_order_evaluater_item,null);
            viewholder=new ViewHolder();
            viewholder.mDelete= (ImageButton) convertView.findViewById(R.id.feekback_delter);
            viewholder.mImg= (ImageView) convertView.findViewById(R.id.feekback_img);
            convertView.setTag(viewholder);
        }else{
            viewholder= (ViewHolder) convertView.getTag();
        }
        final String pathString = lists.get(position);
        if (lists.size()-1==position) {
            Glide.with(mContext).load(R.mipmap.ic_camera_help).diskCacheStrategy(DiskCacheStrategy.NONE).into(viewholder.mImg);
        }else{
            Glide.with(mContext).load(pathString).diskCacheStrategy(DiskCacheStrategy.NONE).override(500, 500).into(viewholder.mImg);
        }
        viewholder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lists.size()-1==position) {
                    listen.addPic();
                }else{
                    listen.largeImage(pathString);
                }
            }
        });
        viewholder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listen.removePic(position);
            }
        });
        if (lists.size()-1==position){
            viewholder.mDelete.setVisibility(View.GONE);
        }else{
            viewholder.mDelete.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
    public void deletePic(int position){
        lists.remove(position);
        notifyDataSetChanged();
    }
    private class ViewHolder{
        ImageView mImg;
        ImageButton mDelete;
    }
    //回调点击加载图片的
    public interface onAddPicListen{
        void addPic();
        void removePic(int position);
        void largeImage(String largeImg);
    }
    private onAddPicListen listen;
    public void setOnAddPicListen(onAddPicListen listen){
        this.listen=listen;
    }
    public void refreshPath(List<String> data){
        this.lists.addAll(0,data);
        notifyDataSetChanged();
    }
    public void addPath(String path){
        this.lists.add(0,path);
        notifyDataSetChanged();
    }
    public int getItemCount(){
        List<String> data=new ArrayList<>();
        data=this.lists;
        return data.size()-1;
    }
    public ArrayList<String> getListPaths(){
        ArrayList<String> data=new ArrayList<>();
        for (int i = 0; i <this.lists.size()-1; i++) {
            data.add(this.lists.get(i));
        }
       return data;
    }
}
