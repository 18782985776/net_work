package com.jobnew.farm.module.farm.adapter.farmAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.entity.FarmProductEntity;
import com.jobnew.farm.module.farm.activity.BreedDetailsActivity;
import com.jobnew.farm.module.farm.activity.LandDetailsActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.ProductDetailsActivity;
import com.jobnew.farm.module.home.activity.CateringDetailsActivity;
import com.jobnew.farm.module.home.activity.EntertainmentDetailsActivity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.utils.SpannableStringUtil;
import com.jobnew.farm.widget.AppManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangwenlang on 2017/5/26.
 * title:
 * note:
 */

public class FarmDetailsRecycleAdapter extends RecyclerView.Adapter<FarmDetailsRecycleAdapter.ViewHolder>{
    int showType;
    double myDistance=0.0;
    static class  ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout farmDeataiItemLayout;//item布局
        ImageView img;
        /**图片右上角靠近图片的textview**/
        TextView farmDetailsNametv;
        /**第一行右边的单价**/
        TextView unitPriceTv;
        /**第二行的描述或者价格**/
        TextView describeTv;
        /**第三行第一个textview**/
        TextView areaTv;//面积
        /**第三行第二个textview**/
        TextView storeTv;
        /**第三行第二个textview**/
        TextView discussTv;
        public ViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.farmdetails_recycle_item_img);
            farmDetailsNametv= (TextView) itemView.findViewById(R.id.farmdetails_farm_nametv);
            unitPriceTv= (TextView) itemView.findViewById(R.id.unit_price_tv);
            describeTv= (TextView) itemView.findViewById(R.id.farm_details_describe_tv);
            areaTv= (TextView) itemView.findViewById(R.id.areaTv);
            storeTv= (TextView) itemView.findViewById(R.id.store_numberTv);
            discussTv= (TextView) itemView.findViewById(R.id.discussTv);
            farmDeataiItemLayout= (LinearLayout) itemView.findViewById(R.id.farm_deatai_item_layout);
        }
    }
    LayoutInflater inflater;
    Context context;
    String farmId;
    SimpleDateFormat simpleDateFormat;
    /**数据源头*根据不同的实体类控制holder子控件的显示和隐藏*/
    ArrayList<FarmProductEntity> arrayList;
    public FarmDetailsRecycleAdapter(ArrayList arrayList, Context context,String farmId){
        inflater=LayoutInflater.from(context);
        this.arrayList=arrayList;
        this.context=context;
        this.farmId=farmId;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }
    @Override
    public FarmDetailsRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.farm_details_recycle_item,parent,false);
        FarmDetailsRecycleAdapter.ViewHolder holder=new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(FarmDetailsRecycleAdapter.ViewHolder holder, int position) {
        //Todo根据数据类型判断显示和隐藏
        //  getEntityType(arrayList);
        switch (showType){
            case 1://土地
                setType1( holder, position);
                break;
            case 2:
                setType2(holder,position);
                break;
            case 3:
                setType3(holder,position);
                break;
            case 4:
                setType4(holder,position);
                break;
            case 5:
                setType5(holder,position);
                break;
            case 6:
                setType6(holder,position);
                break;
        }

    }
    /**设置showType==6，餐饮的holder**/
    private void setType6(ViewHolder holder, int position) {
        FarmProductEntity farmProductEntity = arrayList.get(position);
        GlideManager.loadImg(farmProductEntity.getPImg(),holder.img);//产品图片
        holder.farmDetailsNametv.setText(farmProductEntity.getName());//名字
        SpannableStringUtil.getInstance().setSpanWithColor("￥"+farmProductEntity.getPrice()+"/"+farmProductEntity.getUnitName(),Color.parseColor("#FB491A"),0,new String(farmProductEntity.getPrice()+"").length()+1,holder.unitPriceTv);//价格
        holder.describeTv.setText(farmProductEntity.getIntro());//简介
        holder.areaTv.setText("月售"+22); //曾雪强说暂时没做
        holder.storeTv.setText("评论"+22);//后台现在没弄
        holder.discussTv.setVisibility(View.GONE);
        //差评论数量
        holder.farmDeataiItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("farmId",farmProductEntity.getId());//活动id
                intent.putExtra("farmName",farmProductEntity.getName());//huo
                intent.putExtra("key",farmProductEntity.getKey());
                AppManager.jump(CateringDetailsActivity.class,intent);
            }
        });
    }

    /**设置showType==5，住宿的holder**/
    private void setType5(FarmDetailsRecycleAdapter.ViewHolder holder, int position) {
        FarmProductEntity farmProductEntity = arrayList.get(position);
        GlideManager.loadImg(farmProductEntity.getPImg(),holder.img);//产品图片
        holder.farmDetailsNametv.setText(farmProductEntity.getName());
        SpannableStringUtil.getInstance().setSpanWithColor("￥"+farmProductEntity.getPrice()+"/"+farmProductEntity.getUnitName(),Color.parseColor("#FB491A"),0,new String(farmProductEntity.getPrice()+"").length()+1,holder.unitPriceTv);//价格
        holder.describeTv.setText(farmProductEntity.getIntro());
        holder.areaTv.setText("库存"+farmProductEntity.getStock());
        // holder.storeTv.setText("评论"+farmProductEntity.get);//后台现在没弄
        holder.discussTv.setVisibility(View.GONE);
        //差评论数量
        holder.farmDeataiItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("farmId",farmProductEntity.getId());//活动id
                intent.putExtra("farmName",farmProductEntity.getName());//huo
                intent.putExtra("key",farmProductEntity.getKey());
                AppManager.jump(CateringDetailsActivity.class,intent);
            }
        });
    }

    /**设置showType==4，活动的holder**/
    private void setType4(FarmDetailsRecycleAdapter.ViewHolder holder, int position) {
        FarmProductEntity farmProductEntity = arrayList.get(position);
        GlideManager.loadImg(farmProductEntity.getPImg(),holder.img);//产品图片
        holder.farmDetailsNametv.setText(farmProductEntity.getName());
        SpannableStringUtil.getInstance().setSpanWithColor("￥"+farmProductEntity.getPrice()+"/"+farmProductEntity.getUnitName(),Color.parseColor("#FB491A"),0,new String(farmProductEntity.getPrice()+"").length()+1,holder.unitPriceTv);//价格
        holder.describeTv.setText(farmProductEntity.getIntro());
        holder.areaTv.setText("人数"+(farmProductEntity.getMaxStock()-farmProductEntity.getStock())+"/"+farmProductEntity.getMaxStock());
        if(myDistance!=0.0){
            holder.storeTv.setText("距离 "+(int)myDistance/1000+"km");
        }
        holder.discussTv.setText("日期"+simpleDateFormat.format(new Date(farmProductEntity.getSaleDate()))+
                   "至"+simpleDateFormat.format(new Date(farmProductEntity.getSoldOutDate())));
        holder.discussTv.setVisibility(View.VISIBLE);

        //差起止日期
        holder.farmDeataiItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumpIntent=new Intent();
                jumpIntent.putExtra("id",farmProductEntity.getId());
                AppManager.jump(EntertainmentDetailsActivity.class,jumpIntent);
            }
        });

    }

    /**设置showType==3,集市或者农产品的holder**/
    private void setType3(FarmDetailsRecycleAdapter.ViewHolder holder, int position) {
        // farmdetails_recycle_item_img
        FarmProductEntity farmProductEntity = arrayList.get(position);
        //   holder.img
        GlideManager.loadImg(farmProductEntity.getPImg(),holder.img);
        holder.farmDetailsNametv.setText(farmProductEntity.getName());
        SpannableStringUtil.getInstance().setSpanWithColor("￥"+farmProductEntity.getPrice()+"/"+farmProductEntity.getUnitName(),Color.parseColor("#FB491A"),0,new String(farmProductEntity.getPrice()+"").length()+1,holder.unitPriceTv);//价格
        holder.describeTv.setText(farmProductEntity.getIntro());
        holder.areaTv.setText("商品库存"+farmProductEntity.getStock());
        holder.storeTv.setText("月售"+22);//后台现在没弄
        holder.discussTv.setVisibility(View.GONE);
        holder.farmDeataiItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",farmProductEntity.getId());
                AppManager.jump(ProductDetailsActivity.class,intent);
            }
        });
    }

    /**设置showType==2，表示养殖的holder**/
    private void setType2(FarmDetailsRecycleAdapter.ViewHolder  holder, int position) {
        FarmProductEntity farmProductEntity= (FarmProductEntity) arrayList.get(position);
        SpannableStringUtil.getInstance().setSpanWithColor("￥"+farmProductEntity.getPrice()+"/"+farmProductEntity.getUnitName(),Color.parseColor("#FB491A"),0,new String(farmProductEntity.getPrice()+"").length()+1,holder.unitPriceTv);
        holder.farmDetailsNametv.setText(farmProductEntity.getName());
        GlideManager.loadImg(farmProductEntity.getPImg(),holder.img);//预览图
        holder.describeTv.setText(farmProductEntity.getIntro());
        holder.areaTv.setText("养殖周期 "+farmProductEntity.getCycleTime()+"天");
        holder.storeTv.setText("月售"+22);//这个字段是没有的，王刚说这个字段先不加7.24号
        holder.discussTv.setVisibility(View.GONE);
        holder.farmDeataiItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Constant.PRODUCT_ID,farmProductEntity.getId());
                intent.putExtra(Constant.FARM_ID, farmId);
                AppManager.jump(BreedDetailsActivity.class, intent);
            }
        });
    }
    /**设置showType==1,表示种植的holder**/
    private void setType1(FarmDetailsRecycleAdapter.ViewHolder holder, int position) {

        FarmProductEntity farmProductEntity= (FarmProductEntity) arrayList.get(position);
        holder.farmDetailsNametv.setText(farmProductEntity.getName());
        SpannableStringUtil.getInstance().setSpanWithColor("￥"+farmProductEntity.getPrice()+"/㎡/月",Color.parseColor("#FB491A"),0,new String(farmProductEntity.getPrice()+"").length()+1,holder.unitPriceTv);
        holder.describeTv.setText(farmProductEntity.getIntro());
        holder.areaTv.setText("总面积 "+farmProductEntity.getMaxStock()+"㎡");//现在没有面积这个参数//7.24参数已经加上
        holder.storeTv.setText("库存 "+farmProductEntity.getStock()+"㎡");
        LinearLayout ll= (LinearLayout) holder.storeTv.getParent();
        ll.getChildAt(3).setVisibility(View.GONE);
        // getTextView01设置权重是1
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f);
        holder.storeTv.setLayoutParams(lp);

        //  holder.discussTv.setText("评论 "+farmProductEntity.getCommentCount());
        holder.discussTv.setVisibility(View.GONE);
        GlideManager.loadImg(farmProductEntity.getPImg(),holder.img);//预览图
        holder.farmDeataiItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Constant.PRODUCT_ID, farmProductEntity.getId());
                intent.putExtra(Constant.FARM_ID, farmId);
                AppManager.jump(LandDetailsActivity.class, intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    /**根据实体类判断展示类型**/
//    public int getEntityType(ArrayList<Object> arrayList){
//        if(arrayList.get(0) instanceof FarmLandEntity){
//            return 1;
//        };
//        return 0;
//    }
    public void setShowType(int showType){
        this.showType=showType;
    }
    public void setMyDistance(Double myDistance){
        this.myDistance=myDistance;
    }
}
