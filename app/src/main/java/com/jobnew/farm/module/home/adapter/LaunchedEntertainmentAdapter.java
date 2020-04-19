package com.jobnew.farm.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jobnew.farm.R;
import com.jobnew.farm.utils.FarmToastUtils;

import java.util.ArrayList;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by wangwenlang on 2017/7/7.
 * title:
 * note:
 */

public class LaunchedEntertainmentAdapter  extends RecyclerView.Adapter<LaunchedEntertainmentAdapter.ViewHolder>{
    ArrayList<String> arrayList;
    Context context;
    LayoutInflater layoutInflater;
    public LaunchedEntertainmentAdapter(ArrayList<String> arrayList,Context context){
        this.arrayList=arrayList;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public LaunchedEntertainmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LaunchedEntertainmentAdapter.ViewHolder holder=null;
        holder=new LaunchedEntertainmentAdapter.ViewHolder(layoutInflater.inflate(R.layout.layout_picture,parent,false)) ;
        return holder;
    }

    @Override
    public void onBindViewHolder(LaunchedEntertainmentAdapter.ViewHolder holder, int position) {
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FarmToastUtils.show("点击位置"+position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder  extends RecyclerView.ViewHolder{
        RelativeLayout ll;
        ImageView bigImg;
        ImageView smallImg;
        public  ViewHolder(View itemView){
            super(itemView);
            ll= (RelativeLayout) itemView.findViewById(R.id.item_add_img);
            bigImg= (ImageView) itemView.findViewById(R.id.img_entertainment);
            smallImg= (ImageView) itemView.findViewById(R.id.small_img);
        }

    }
}
