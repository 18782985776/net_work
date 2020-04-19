package com.jobnew.farm.module.home.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.FarmHappy.FarmHappyOrderSpendEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.home.adapter.FarmHappySpendedOrderAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;
import static com.bumptech.glide.load.engine.DiskCacheStrategy.RESULT;

/**
 * Created by wangwenlang on 2017/8/8.
 * title:
 * note:
 */


public class FarmHappyNotSpendingOrderFragment extends BaseRefreshLoadFragment {
    FarmHappySpendedOrderAdapter adapter;
    int pageNo=1;
    int pageSize=20;
    String type="agritmnt";
    String status="consum";//待消费
    boolean isDataOver=false;
    ArrayList<FarmHappyOrderSpendEntity> arrayList;
    String[] permissions={Manifest.permission.CALL_PHONE,};
    Intent intentPhone;
    @Override
    protected void initView(Bundle bundle, View view) {
        initData();
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + arrayList.get(position).getPhone()));
               //
            if(EasyPermissions.hasPermissions(getActivity(),permissions)){
                startActivity(intentPhone);
            }else {
                EasyPermissions.requestPermissions(getActivity(),"必要权限",123,permissions);
            }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123){
            if(resultCode==RESULT_OK){
                startActivity(intentPhone);
            }else {
                showMsg("无操作权限");
            }
        }
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
                List<FarmHappyOrderSpendEntity> data = entity.data;
                if(entity.data==null){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(data);
                adapter.notifyDataSetChanged();
                content();
                if(data.size()<20){
                    isDataOver=true;
                    adapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
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
       if(isDataOver=true) {
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
                if(entity.data==null){
                isDataOver=true;
                    adapter.loadMoreEnd(false);
                    return;
                }
                arrayList.addAll(data);
                adapter.notifyDataSetChanged();
                content();
                if(data.size()<20){
                    isDataOver=true;
                    adapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
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
        arrayList = new ArrayList();
        return adapter=new FarmHappySpendedOrderAdapter(R.layout.item_farm_happy_order,arrayList,2);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_farm_happy_not_spending_order;
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
                List<FarmHappyOrderSpendEntity> data = entity.data;
                if(entity.data==null){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(data);
                adapter.notifyDataSetChanged();
                content();
                if(data.size()<20){
                    isDataOver=true;
                    adapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
                frame.refreshComplete();
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
