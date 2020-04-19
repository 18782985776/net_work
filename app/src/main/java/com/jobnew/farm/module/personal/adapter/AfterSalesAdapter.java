package com.jobnew.farm.module.personal.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.bazaar.AfterSalesBean;
import com.jobnew.farm.utils.DateUtils;
import com.jobnew.farm.utils.GlideManager;

import java.util.List;

/**
 * Created by wc on 2017/6/23.
 * Function：
 * desc：
 */

public class AfterSalesAdapter extends BaseQuickAdapter<AfterSalesBean,BaseViewHolder> {
    private Context mContext;
    public AfterSalesAdapter(int layoutResId, List<AfterSalesBean> data, Context mContext) {
        super(layoutResId, data);
        this.mContext=mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, AfterSalesBean item) {
        helper.setText(R.id.txt_farm_name,item.getOrder().getFarm().getName());
        GlideManager.loadRoundImg(item.getOrder().getFarm().getImg(),(ImageView) helper.getView(R.id.img_farm));
        GlideManager.loadImg(item.getOrderItem().getThumbnail(),helper.getView(R.id.img_order));
        helper.setText(R.id.txt_order_item,item.getOrderItem().getName());
        helper.setText(R.id.txt_order_intro,item.getOrderItem().getCname());
        helper.setText(R.id.txt_price,"¥"+item.getOrderItem().getPrice());
        helper.setText(R.id.txt_quantity,item.getOrderItem().getQuantity()+"");
        helper.setText(R.id.order_item_time, "创建时间 : "+DateUtils.toString(item.getCreateDate()+""));
        String yu="";
        if (item.getOrder().getFreight()==0){
            yu="(包邮)";
        }else{
            yu="(含运费 : ￥"+item.getOrder().getFreight()+")";
        }
        helper.setText(R.id.order_item_total,yu);
        helper.setText(R.id.order_item_price,"￥"+item.getRefundAmount());
        helper.setText(R.id.txt_logisticsNo,"货运单号："+item.getOrder().getLogisticsNo());
        helper.setText(R.id.txt_sn,"订单编号："+item.getOrder().getSn());


        TextView typeTxt = helper.getView(R.id.txt_type);
        TextView txt1 = helper.getView(R.id.txt_platform_in);
        TextView txt2 = helper.getView(R.id.txt_cancel_application);
        helper.addOnClickListener(R.id.img_contact);
        helper.addOnClickListener(R.id.rl_farm);
        helper.addOnClickListener(R.id.txt_platform_in);
        helper.addOnClickListener(R.id.txt_cancel_application);
        helper.addOnClickListener(R.id.txt_application_details);
        if (item.getReturnStatus().equals("pending")){
            typeTxt.setText("等待商家处理退货申请");
            typeTxt.setTextColor(mContext.getResources().getColor(R.color.main_color));
            txt1.setVisibility(View.GONE);
            txt2.setVisibility(View.VISIBLE);
            helper.setText(R.id.txt_application_details,"申请详情");
        }else if (item.getReturnStatus().equals("refuse")){
            typeTxt.setText("商家拒绝退货");
            typeTxt.setTextColor(mContext.getResources().getColor(R.color.other_red));
            txt1.setVisibility(View.VISIBLE);
            txt2.setVisibility(View.VISIBLE);
            helper.setText(R.id.txt_application_details,"申请详情");
        }else if (item.getReturnStatus().equals("intervention")){
            typeTxt.setText("平台客服介入中");
            typeTxt.setTextColor(mContext.getResources().getColor(R.color.main_color));
            txt1.setVisibility(View.GONE);
            txt2.setVisibility(View.VISIBLE);
            helper.setText(R.id.txt_application_details,"申请详情");
        }else if (item.getReturnStatus().equals("complete")){
            typeTxt.setText("退货完成");
            typeTxt.setTextColor(mContext.getResources().getColor(R.color.main_color));
            txt1.setVisibility(View.GONE);
            txt2.setVisibility(View.GONE);
            helper.setText(R.id.txt_application_details,"申请详情");
        }else if (item.getReturnStatus().equals("canceled")){
            typeTxt.setText("已撤销");
            typeTxt.setTextColor(mContext.getResources().getColor(R.color.main_color));
            txt1.setVisibility(View.GONE);
            txt2.setVisibility(View.GONE);
            helper.setText(R.id.txt_application_details,"已撤销");
        }
    }
}
