package com.jobnew.farm.module.personal.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.bazaar.OrderBazaarBean;
import com.jobnew.farm.module.farm.activity.farmActivity.ProductDetailsActivity;
import com.jobnew.farm.utils.DateUtils;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.AppManager;

import java.util.List;

/**
 * Created by wc on 2017/6/12.
 * Function：
 * desc：
 */

public class MyOrderAdapter extends BaseQuickAdapter<OrderBazaarBean,BaseViewHolder> {
    private int tag;
    public List<OrderBazaarBean> data;
    private Context mContext;
    public MyOrderAdapter(int layoutResId, List<OrderBazaarBean> data,int tag,Context mContext) {
        super(layoutResId, data);
        this.tag=tag;
        this.mContext=mContext;
    }
    @Override
    protected void convert(BaseViewHolder helper, OrderBazaarBean item) {
//    状态, 有如下值：pendingPayment-等待付款,pendingReview-等待审核,pendingShipment-等待发货,growing-种植,养殖中,
// shipped-已发货,received-已收货,completed-已完成,failed-已失败,canceled-已取消,denied-已拒绝
        if (item.getStatus().equals("pendingPayment")){
            setVisibility(helper,1);
        }else if (item.getStatus().equals("pendingShipment")){
            setVisibility(helper,2);
        }else if (item.getStatus().equals("shipped")){
            setVisibility(helper,3);
        }else if (item.getStatus().equals("completed")||item.getStatus().equals("received")){
            setVisibility(helper,4);
        }else if (item.getStatus().equals("canceled")){
            setVisibility(helper,5);
        }
        helper.setText(R.id.txt_farm_name,item.getFarm().getName());
        GlideManager.loadRoundImg(item.getFarm().getImg(),(ImageView) helper.getView(R.id.img_farm));
        LinearLayout parent = helper.getView(R.id.ll_parent);
        TextView tvAfterSales=helper.getView(R.id.txt_after_sales);//售后退换
        TextView tvEvaluation=helper.getView(R.id.txt_evaluation);//评价
        parent.removeAllViews();
        List<OrderBazaarBean.OrderItemsEntity> orderItems = item.getOrderItems();
        boolean isEvaluation=false;
        boolean isRefund=false;
        for (int i = 0; i <orderItems.size() ; i++) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_my_order_child,null);
            TextView tvName= (TextView) inflate.findViewById(R.id.txt_name);
            tvName.setText(orderItems.get(i).getName());
            TextView tvIntro= (TextView) inflate.findViewById(R.id.txt_intro);
            tvIntro.setText(orderItems.get(i).getCname());
            TextView tvPrice= (TextView) inflate.findViewById(R.id.txt_price);
            tvPrice.setText("¥ "+orderItems.get(i).getPrice());
            TextView tvNum= (TextView) inflate.findViewById(R.id.txt_unm);
            tvNum.setText(orderItems.get(i).getQuantity()+"");
            ImageView img= (ImageView) inflate.findViewById(R.id.img_title_img);
            GlideManager.loadImg(orderItems.get(i).getThumbnail(),img);

            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.ll);
            int finalI = i;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int item =orderItems.get(finalI).getProductId();
                    AppManager.jump(ProductDetailsActivity.class,"id",item);
                }
            });
            parent.addView(inflate);
            //判断评价显示隐藏
            if (orderItems.get(i).getCommentState().equals("pendingEvaluation")){
                isEvaluation=true;
            }
            if (orderItems.get(i).isCanRefund()){
                isRefund=true;
            }
        }
        //判断评价显示隐藏
        if (isEvaluation){
            tvEvaluation.setVisibility(View.VISIBLE);
        }else{
            tvEvaluation.setVisibility(View.GONE);
        }
        //判断申请售后是否显示隐藏
        if (isRefund){
            tvAfterSales.setVisibility(View.VISIBLE);
        }else{
            tvAfterSales.setVisibility(View.GONE);
        }
        helper.setText(R.id.order_item_time,"创建时间 : "+ DateUtils.DateToString(item.getCreateDate()+"","yyyy-MM-dd"));
        String yu="";
        if (item.getFreight()==0){
            yu="(包邮)";
        }else{
            yu="(含运费 : ￥"+item.getFreight()+")";
        }
        helper.setText(R.id.order_item_total,yu);
        helper.setText(R.id.order_item_price,"￥"+item.getAmount());
        helper.addOnClickListener(R.id.rl_farm);
        helper.addOnClickListener(R.id.txt_delete_order_one);
        helper.addOnClickListener(R.id.txt_payment);
        helper.addOnClickListener(R.id.txt_check_logistics_one);
        helper.addOnClickListener(R.id.txt_check_logistics_two);
        helper.addOnClickListener(R.id.txt_confirm_goods);
        helper.addOnClickListener(R.id.txt_check_logistics_three);
        helper.addOnClickListener(R.id.txt_delete_order_two);
        helper.addOnClickListener(R.id.txt_evaluation);
        helper.addOnClickListener(R.id.txt_delete_order_three);
        helper.addOnClickListener(R.id.txt_after_sales);
    }
    private void setVisibility(BaseViewHolder helper, int tag) {
        LinearLayout l1=helper.getView(R.id.ll_1);
        LinearLayout l2=helper.getView(R.id.ll_2);
        LinearLayout l3=helper.getView(R.id.ll_3);
        LinearLayout l4=helper.getView(R.id.ll_4);
        LinearLayout l5=helper.getView(R.id.ll_5);
        TextView txt=helper.getView(R.id.txt_type);
        switch (tag){
            case 1:
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                txt.setText("待付款");
                break;
            case 2:
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                txt.setText("待发货");
                break;
            case 3:
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.VISIBLE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                txt.setText("待收货");
                break;
            case 4:
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.VISIBLE);
                l5.setVisibility(View.GONE);
                txt.setText("交易成功");
                break;
            case 5:
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.VISIBLE);
                txt.setText("交易关闭");
                break;
        }
    }
}
