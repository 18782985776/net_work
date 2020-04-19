package com.jobnew.farm.module.personal.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.entity.bazaar.OrderBazaarBean;
import com.jobnew.farm.module.personal.activity.AfterReturnActivity;
import com.jobnew.farm.module.personal.activity.OrderEvaluateActivity;
import com.jobnew.farm.module.personal.adapter.OrderMoreEvaAndSafAdapter;
import com.jobnew.farm.widget.AppManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wc on 2017/8/18.
 * Function：跟多评价和售后
 * desc：
 */

public class OrderMoreEvaAndSafActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private int orderId ;//订单id
    List<OrderBazaarBean.OrderItemsEntity> datas;
    private OrderMoreEvaAndSafAdapter adapter;
    @Override
    protected int getLayout() {
        return R.layout.activity_order_eva_saf;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("售后和评价", true);
        Intent intent = getIntent();
        orderId = intent.getIntExtra("orderId",0);
        String data = intent.getStringExtra("data");
        datas = new Gson().fromJson(data,new TypeToken<List<OrderBazaarBean.OrderItemsEntity>>(){}.getType());
        initReyclerView(datas);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter1, View view, int position) {
                switch (view.getId()){
                    case R.id.txt_evaluation:
                        if (adapter.getItem(position).getCommentState().equals("pendingEvaluation")){
                            Intent intent1=new Intent();
                            intent1.putExtra("orderItemId",adapter.getItem(position).getId());
                            intent1.putExtra("orderId",orderId);
                            AppManager.jump(OrderEvaluateActivity.class,intent1);
                            finish();
                        }else{
                            showMsg("已经评价,请不要重复评价");
                        }
                        break;
                    case R.id.txt_after_sales:
                        if (adapter.getItem(position).isCanRefund()){
                            Intent intent1=new Intent();
                            intent1.putExtra("orderItemId",adapter.getItem(position).getId());
                            intent1.putExtra("money",adapter.getItem(position).getPrice());
                            AppManager.jump(AfterReturnActivity.class,intent1);
                            finish();
                        }else{
                            showMsg("正在售后处理中，请不要重复申请");
                        }
                        break;
                }
            }
    });
    }
    private void initReyclerView(List<OrderBazaarBean.OrderItemsEntity> datas) {
        adapter=new OrderMoreEvaAndSafAdapter(R.layout.activity_order_eva_saf_item,datas,mContext);
        LinearLayoutManager lm=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);
    }
}
