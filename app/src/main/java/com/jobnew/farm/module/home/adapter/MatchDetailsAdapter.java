package com.jobnew.farm.module.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jobnew.farm.R;
import com.jobnew.farm.entity.matchFeature.MatchDetailsEntity;
import com.jobnew.farm.utils.GlideManager;

import java.util.ArrayList;

/**
 * Created by wangwenlang on 2017/8/22.
 * title:
 * note:
 */

public class MatchDetailsAdapter extends RecyclerView.Adapter<MatchDetailsAdapter.ViewHolder> {
    private ArrayList<MatchDetailsEntity.ImagesBean> arrayList;
    public MatchDetailsAdapter(ArrayList<MatchDetailsEntity.ImagesBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ietm_match_details, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlideManager.loadImg(arrayList.get(position).getUrl(),holder.itemImg);
    }

    @Override
    public int getItemCount() {
        // return 3;
        return arrayList.size();
    }

    protected class ViewHolder  extends RecyclerView.ViewHolder{
        ImageView itemImg;
        public ViewHolder(View itemView) {
            super(itemView);
            itemImg= (ImageView) itemView.findViewById(R.id.img_item);
        }
    }
}
