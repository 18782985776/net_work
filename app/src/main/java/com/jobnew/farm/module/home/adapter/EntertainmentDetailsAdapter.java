package com.jobnew.farm.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.entity.NoteEntity;
import com.jobnew.farm.utils.GlideManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wangwenlang on 2017/7/7.
 * title:
 * note:
 */

public class EntertainmentDetailsAdapter  extends RecyclerView.Adapter<EntertainmentDetailsAdapter.ViewHolder>{
    ArrayList<NoteEntity.ListBean > arrayList;
    Context context;
    SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    LayoutInflater inflater;
    public EntertainmentDetailsAdapter(ArrayList<NoteEntity.ListBean> arrayList, Context context){
        this.arrayList=arrayList;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public EntertainmentDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder=null;
        holder=new ViewHolder(inflater.inflate(R.layout.my_message_layout,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(EntertainmentDetailsAdapter.ViewHolder holder, int position) {
        try {
            GlideManager.loadRoundImg(arrayList.get(position).getUser().getAvatar(),holder.headImg);
            holder.tvUserName.setText(arrayList.get(position).getUser().getName());
            holder.tvDate.setText(sf.format(new Date(arrayList.get(position).getCreateDate())));
            holder.tvMessage.setText(arrayList.get(position).getContent());
        }catch (NullPointerException  e){
            Log.d("空指针", "onBindViewHolder: "+e.toString());
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView  headImg;
        TextView tvUserName;
        TextView tvDate;
        TextView tvMessage;
        public ViewHolder(View itemView) {
            super(itemView);
            headImg= (ImageView) itemView.findViewById(R.id.head_img_message);
            tvUserName= (TextView) itemView.findViewById(R.id.tv_user_name);
            tvDate= (TextView) itemView.findViewById(R.id.tv_date);
            tvMessage= (TextView) itemView.findViewById(R.id.tv_message);
        }
    }
}
