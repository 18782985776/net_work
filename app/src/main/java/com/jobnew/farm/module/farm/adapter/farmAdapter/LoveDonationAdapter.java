package com.jobnew.farm.module.farm.adapter.farmAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jobnew.farm.R;
import com.jobnew.farm.entity.base.LoveDonationEntity;

import java.util.ArrayList;

/**
 * Created by wangwenlang on 2017/5/31.
 * title:
 * note:
 */

public class LoveDonationAdapter extends RecyclerView.Adapter<LoveDonationAdapter.LoveViewHolder> {
    LayoutInflater inflater;
    ArrayList<LoveDonationEntity> arrayList;
    Context context;
    class LoveViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        LinearLayout loveItem;
        /**收件人**/
        TextView recipientstv;
        TextView  telTv;
        TextView postCodeTv;
        TextView adressTv;
        TextView describeTv;
        public LoveViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.img_selection);
            telTv= (TextView) itemView.findViewById(R.id.tv_tel);
            postCodeTv= (TextView) itemView.findViewById(R.id.tv_postcode);
            adressTv= (TextView) itemView.findViewById(R.id.tv_address);
            describeTv= (TextView) itemView.findViewById(R.id.love_donation_describe_tv);
            recipientstv= (TextView) itemView.findViewById(R.id.tv_recipients);
            loveItem= (LinearLayout) itemView.findViewById(R.id.item_love);
        }
    }
    public  LoveDonationAdapter(Context context,ArrayList<LoveDonationEntity> arrayList){
        inflater=LayoutInflater.from(context);
        this.arrayList=arrayList;
        this.context=context;
    }
    @Override
    public LoveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LoveViewHolder loveViewHolder=new LoveViewHolder(inflater.inflate(R.layout.love_donation_item,parent,false));
        return loveViewHolder;
    }

    @Override
    public void onBindViewHolder(LoveViewHolder holder, int position) {
//        holder.img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"点击了"+position+"位置",Toast.LENGTH_SHORT).show();
//            }
//        });
        //loveDonationEntity.getArea().getMergerName()+loveDonationEntity.getArea().getName()+loveDonationEntity.getAddress()
        LoveDonationEntity loveDonationEntity = arrayList.get(position);
        holder.recipientstv.setText("收件人："+loveDonationEntity.getContactName());
        holder.telTv.setText("联系电话："+loveDonationEntity.getPhone());
        String adressDetails="地址："+loveDonationEntity.getArea().getMergerName()+loveDonationEntity.getArea().getName()+loveDonationEntity.getAddress();
        holder.adressTv.setText(adressDetails.replace(",",""));
        holder.describeTv.setText(loveDonationEntity.getDescription());
        holder.loveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(myItemListener!=null){
                myItemListener.Chilced( position);
             }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public void setMyItemListener(MyItemListener myItemListener){
        this.myItemListener=myItemListener;
    }
    private MyItemListener myItemListener;
    public interface MyItemListener{
        void Chilced(int position);
    }
}

