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
import com.jobnew.farm.BuildConfig;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.UserPhotoBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wc on 2016/11/1.
 */

public class PhotoAlbumAdapter extends BaseAdapter {
    private List<UserPhotoBean> lists;
    private Context mContext;
    private LayoutInflater inflater;
    private int max;

    public PhotoAlbumAdapter(List<UserPhotoBean> data, Context context, int max) {
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
    public UserPhotoBean getItem(int position) {
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
            convertView=inflater.inflate(R.layout.activity_photo_album_img,null);
            viewholder=new ViewHolder();
            viewholder.mDelete= (ImageButton) convertView.findViewById(R.id.feekback_delter);
            viewholder.mImg= (ImageView) convertView.findViewById(R.id.feekback_img);
            convertView.setTag(viewholder);
        }else{
            viewholder= (ViewHolder) convertView.getTag();
        }
        UserPhotoBean pathString = lists.get(position);
        if (lists.size()-1==position) {
            Glide.with(mContext).load(R.mipmap.ic_camera_help).placeholder(R.mipmap.ic_camera_help).error(R.mipmap.ic_camera_help).diskCacheStrategy(DiskCacheStrategy.NONE).into(viewholder.mImg);
        }else{
            Glide.with(mContext).load(pathString.getUrl()).placeholder(R.mipmap.ic_default).error(R.mipmap.ic_error).diskCacheStrategy(DiskCacheStrategy.NONE).override(500, 500).into(viewholder.mImg);
        }
        viewholder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lists.size()-1==position) {
                    listener.addPic();
                }
            }
        });
        viewholder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deletePic(lists.get(position),position);
            }
        });
        if (isDelete){
            if (lists.size()-1==position){
                viewholder.mDelete.setVisibility(View.GONE);
            }else{
                viewholder.mDelete.setVisibility(View.VISIBLE);
            }
        }else{
            viewholder.mDelete.setVisibility(View.GONE);
        }
        return convertView;
    }

    public void addImagePaths(List<UserPhotoBean> arrPaths) {
        lists.addAll(0,arrPaths);
        notifyDataSetChanged();
    }
    private boolean isDelete=false;
    public void changeDelete() {
        isDelete=!isDelete;
        notifyDataSetChanged();
    }

    public void deletePosition(int position) {
        lists.remove(position);
        notifyDataSetChanged();
    }

    public void addUserPhotoBean(UserPhotoBean userPhotoBean) {
        lists.add(0,userPhotoBean);
        notifyDataSetChanged();
    }

    private class ViewHolder{
        ImageView mImg;
        ImageButton mDelete;
    }
    //回调点击加载图片的
    public interface onAddPicListener{
        void addPic();
        void deletePic(UserPhotoBean stringPath,int position);
    }
    private onAddPicListener listener;
    public void setOnAddPicListener(onAddPicListener listener){
        this.listener=listener;
    }
    public int getItemCount(){
        List<UserPhotoBean> data=new ArrayList<>();
        data=this.lists;
        return data.size()-1;
    }
}
