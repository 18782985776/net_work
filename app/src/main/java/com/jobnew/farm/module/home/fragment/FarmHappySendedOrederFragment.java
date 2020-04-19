package com.jobnew.farm.module.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.FarmHappy.FarmHappyOrderSpendEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.home.activity.FarmHappyOrderCommentActivity;
import com.jobnew.farm.module.home.adapter.FarmHappySpendedOrderAdapter;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wangwenlang on 2017/8/8.
 * title:
 * note:
 */

public class FarmHappySendedOrederFragment extends BaseRefreshLoadFragment {
    FarmHappySpendedOrderAdapter madapter;
    int pageNo=1;
    int pageSize=20;
    String type="agritmnt";//agritmnt//
    String status="received";//曾雪强说已收货代表已消费
    boolean isDataOver=false;
    ArrayList<FarmHappyOrderSpendEntity> arrayList;//数据源
    @Override
    protected void initView(Bundle bundle, View view) {
        initData();
        madapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {//已经消费点击
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()){

                    case R.id.txt_delete_orde:
                         deleteOrder(position);
                        break;
                    case R.id.tv_comment:
                        Intent commentIntent=new Intent();
                        FarmHappyOrderSpendEntity farmHappyOrderSpendEntity = arrayList.get(position);
                        commentIntent.putExtra("orderId", farmHappyOrderSpendEntity.getId());//
                        commentIntent.putExtra("orderItemId",farmHappyOrderSpendEntity.getOrderItems().get(0).getId());
                        AppManager.jump(FarmHappyOrderCommentActivity.class,commentIntent);
                        break;

                }
            }
        });
    }
/***删除订单接口****/
    private void deleteOrder(int position) {
        HashMap<String,String> map=new HashMap<>();
        FarmHappyOrderSpendEntity farmHappyOrderSpendEntity = arrayList.get(position);
       ;
        MyFarmRepository.getIns().deleteOrder( farmHappyOrderSpendEntity.getOrderItems().get(0).getOrderId()+"",map)
                .subscribe(new DefaultSubscriber<BaseEntity>(this,false) {
            @Override
            public void _onNext(BaseEntity entity) {
                if(entity.code==200){
                    arrayList.remove(position);
                    madapter.notifyDataSetChanged();
                    madapter.notifyItemRangeChanged(position,1);
                    showMsg("删除成功");
                }
            }

                    @Override
                    public void _onError(Throwable e, String msg) {
                        super._onError(e, msg);
                        showMsg("删除失败");
                    }
                });

    }

    private void initData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        map.put("type",type);
        map.put("status",status);
        loading();
        MyFarmRepository.getIns().queryOrder(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmHappyOrderSpendEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<FarmHappyOrderSpendEntity>> entity) {
                arrayList.clear();
                List<FarmHappyOrderSpendEntity> data = entity.data;
                if(data==null){
                    empty();
                    return;
                }
                if(data.size()==0){
                    empty();
                    return;
                }
                Log.d("消费我", "_onNext: "+data.size());
                arrayList.addAll(data);
                madapter.notifyDataSetChanged();
                if(data.size()<20){
                    isDataOver=true;
                    madapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
                content();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        if(isDataOver=true){
            return;
        }
        HashMap<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        map.put("type",type);
        map.put("status",status);
        MyFarmRepository.getIns().queryOrder(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmHappyOrderSpendEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<FarmHappyOrderSpendEntity>> entity) {
                List<FarmHappyOrderSpendEntity> data = entity.data;
                if(data==null){
                   isDataOver=true;
                    madapter.loadMoreEnd(false);
                    return;
                }
                arrayList.addAll(data);
                madapter.notifyDataSetChanged();
                if(data.size()<20){
                    isDataOver=true;
                    madapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
                content();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        arrayList = new ArrayList<>();
        return madapter=new FarmHappySpendedOrderAdapter(R.layout.item_farm_happy_order,arrayList,1);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_farm_happy_spended_order;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        pageNo=1;
        refreshBegin(frame);
    }

    private void refreshBegin(PtrFrameLayout frame) {
        HashMap<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        map.put("type",type);
        map.put("status",status);
        MyFarmRepository.getIns().queryOrder(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmHappyOrderSpendEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<FarmHappyOrderSpendEntity>> entity) {
                arrayList.clear();
                List<FarmHappyOrderSpendEntity> data = entity.data;
                if(data==null){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(data);
                madapter.notifyDataSetChanged();
                if(data.size()<20){
                    isDataOver=true;
                    madapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
                frame.refreshComplete();
                content();
            }
            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                frame.refreshComplete();
                error(msg);
            }
        });
    }
}
