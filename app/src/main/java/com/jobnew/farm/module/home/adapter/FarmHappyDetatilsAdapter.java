package com.jobnew.farm.module.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.FarmProductEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.home.activity.CateringDetailsActivity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;

/**
 * Created by wangwenlang on 2017/6/26.
 * title:
 * note:
 */

public class FarmHappyDetatilsAdapter extends RecyclerView.Adapter<FarmHappyDetatilsAdapter.ViewHolder>{
    Context context;
    ArrayList<FarmProductEntity> arrayList;
    LayoutInflater inflater;
    private static String key=null;

    public FarmHappyDetatilsAdapter(Context context, ArrayList<FarmProductEntity> arrayList){
        //  super(R.layout.item_farm_happy_detatils,arrayList);
        this.arrayList=arrayList;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public FarmHappyDetatilsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_farm_happy_detatils,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FarmHappyDetatilsAdapter.ViewHolder holder, int position) {
        FarmProductEntity farmProductEntity = arrayList.get(position);
        GlideManager.loadImg(farmProductEntity.getPImg(),holder.img);
        holder.nametv.setText(farmProductEntity.getName());
        if( farmProductEntity.getUnitName()!=null){
            holder.pricetv.setText("￥"+farmProductEntity.getPrice()+"/"+farmProductEntity.getUnitName());
        }else {
            holder.pricetv.setText("￥"+farmProductEntity.getPrice());
        }
        if(farmProductEntity.getIntro()==null){
            holder.descripetv.setText("钓鱼带走称重计费，渔具现场售卖，无需订购");
        }else {
            holder.descripetv.setText(farmProductEntity.getIntro());
        }

        holder.saletv.setText("月售"+farmProductEntity.getSn());
      //  holder.commentTv.setText("评论"+farmProductEntity.getc);
        holder.commentTv.setText("评论"+0);


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("farmId",farmProductEntity.getId());
                intent.putExtra("farmName",farmProductEntity.getName());
                intent.putExtra("key",key);
                AppManager.jump(CateringDetailsActivity.class,intent);
                Log.d("我日", "onClick: "+"farmId:"+farmProductEntity.getId());
                Log.d("我日", "onClick: "+"farmName:"+farmProductEntity.getName());
                Log.d("我日", "onClick: "+"key:"+key);

            }
        });
    }
    /***设置主营业务，方便订单传参数***/
    public void setMainBuess(String key){
        this.key=key;
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        /*大图**/
        ImageView img;
        TextView nametv;
        TextView pricetv;
        TextView descripetv;
        TextView saletv;
        TextView commentTv;
        public ViewHolder(View itemView) {
            super(itemView);
            layout= (LinearLayout) itemView.findViewById(R.id.item_farm_happy_details);
            img= (ImageView) itemView.findViewById(R.id.farm_happy_img);
            nametv= (TextView) itemView.findViewById(R.id.farm_happy_nametv);
            pricetv= (TextView) itemView.findViewById(R.id.farm_happy_price_tv);
            descripetv= (TextView) itemView.findViewById(R.id.farm_happy_describe_tv);
            saletv= (TextView) itemView.findViewById(R.id.month_sale_num_tv);
            commentTv= (TextView) itemView.findViewById(R.id.commentTv);
        }
    }
}
