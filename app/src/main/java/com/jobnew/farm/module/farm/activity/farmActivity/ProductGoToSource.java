package com.jobnew.farm.module.farm.activity.farmActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.SourceEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.farm.adapter.farmAdapter.GoToSourceAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class ProductGoToSource extends BaseRefreshAndLoadActivity {
    GoToSourceAdapter adapter;
    ArrayList<SourceEntity.SourceInfosBean> arrayList;
    LayoutInflater inflater;
    int productId;
    View view;//头部视图
    @Override
    protected int getLayout() {
        return R.layout.activity_product_go_to_source;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("商品溯源", true);
        productId = getIntent().getIntExtra("productId", 1);
        inflater=LayoutInflater.from(this);
        view = inflater.inflate(R.layout.header_go_to_source,null,false);
        arrayList = new ArrayList<>();
        adapter.setNewData(arrayList);
        adapter.addHeaderView(view);
        adapter.notifyDataSetChanged();
        initData();
    }
    /***初始化数据***/
    private void initData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("productId",productId+"");
        loading();
        MyFarmRepository.getIns().querySourceInfo(map).subscribe(new DefaultSubscriber<BaseEntity<SourceEntity>>(this,false) {
            @Override
            public void _onNext(BaseEntity<SourceEntity> entity) {
                SourceEntity sourceEntity = entity.data;
                setViewData(sourceEntity);
                content();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }
    /*****初始化视图数据****/
    private void setViewData(SourceEntity sourceEntity) {
        try {
            TextView tvWord = (TextView) view.findViewById(R.id.word_tv);
            tvWord.setText(sourceEntity.getSn());
            TextView tvProductName = (TextView) view.findViewById(R.id.tv_product_name);
            tvProductName.setText(sourceEntity.getProductName());
            TextView farmNameTv = (TextView) view.findViewById(R.id.farm_name_tv);
            farmNameTv.setText(sourceEntity.getFarmName());
            TextView farmAddressTv = (TextView) view.findViewById(R.id.farm_address_tv);
            farmAddressTv.setText(sourceEntity.getAddress());
            TextView managerNameTv = (TextView) view.findViewById(R.id.manager_name_tv);
            managerNameTv.setText(sourceEntity.getFarmManager());
            List<SourceEntity.SourceInfosBean> sourceInfos = sourceEntity.getSourceInfos();
            adapter.farmurl=sourceEntity.getFarmImgUrl();
            adapter.farmManagerName=sourceEntity.getFarmManager();
//        adapter.setFarmImgUrl(sourceEntity.getFarmImgUrl());
//        adapter.setFarmManager(sourceEntity.getFarmManager());
            arrayList.clear();
            arrayList.addAll(sourceInfos);
            adapter.notifyDataSetChanged();
        }catch (NullPointerException e){
            showMsg("空数据");
        }

    }
    @Override
    public BaseQuickAdapter getAdapter() {
        return adapter=new GoToSourceAdapter(R.layout.item_grow_record,arrayList);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onLoadMoreRequested() {
        adapter.loadMoreEnd(false);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        frame.refreshComplete();
    }
}
