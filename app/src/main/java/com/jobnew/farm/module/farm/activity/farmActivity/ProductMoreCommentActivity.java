package com.jobnew.farm.module.farm.activity.farmActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.HomeEntertainment.HomeEntertainmentDetailsEntity;
import com.jobnew.farm.entity.NoteEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.farm.adapter.farmAdapter.MoreCommentAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class ProductMoreCommentActivity extends BaseRefreshAndLoadActivity {

    MoreCommentAdapter moreCommentAdapter;
    ArrayList<NoteEntity.ListBean> arrayList;
    int productId;
    int pageNo=1;
    int pageSize=20;
    boolean isDataOver=false;
    @Override
    protected int getLayout() {
        return R.layout.activity_product_more_comment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("更多评论",true);
        Intent intent = getIntent();
        productId = intent.getIntExtra("productId", 1);
        initData();
        initRecycleView();
    }

    @Override
    public void errorChickData() {
        super.errorChickData();
        initData();
    }

    /****初始化评论数据***/
    private void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        map.put("productId", productId + "");
        loading();
        MyFarmRepository.getIns().queryActivityMessage(map).subscribe(new DefaultSubscriber<BaseEntity<NoteEntity>>(this, false) {
            @Override
            public void _onNext(BaseEntity<NoteEntity> entity) {
                List<NoteEntity.ListBean> list = entity.data.getList();
                if(list.isEmpty()){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(list);
                moreCommentAdapter.notifyDataSetChanged();
                if(list.size()<20){
                    isDataOver=true;
                    moreCommentAdapter.loadMoreEnd(false);
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

    private void initRecycleView() {
        moreCommentAdapter.notifyDataSetChanged();
    }


    @Override
    public BaseQuickAdapter getAdapter() {
        arrayList = new ArrayList();
        moreCommentAdapter=new MoreCommentAdapter(R.layout.item_product_comment, arrayList);
        return moreCommentAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onLoadMoreRequested() {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        map.put("productId", productId + "");
        MyFarmRepository.getIns().queryActivityMessage(map).subscribe(new DefaultSubscriber<BaseEntity<NoteEntity>>(this, false) {
            @Override
            public void _onNext(BaseEntity<NoteEntity> entity) {
                List<NoteEntity.ListBean> list = entity.data.getList();
                arrayList.addAll(list);
                moreCommentAdapter.notifyDataSetChanged();
                if(list.size()<20){
                    isDataOver=true;
                    moreCommentAdapter.loadMoreEnd(false);
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
    public void onRefreshBegin(PtrFrameLayout frame) {
        pageNo=1;
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        map.put("productId", productId + "");
        MyFarmRepository.getIns().queryActivityMessage(map).subscribe(new DefaultSubscriber<BaseEntity<NoteEntity>>(this, false) {
            @Override
            public void _onNext(BaseEntity<NoteEntity> entity) {
                List<NoteEntity.ListBean> list = entity.data.getList();
                if(list.isEmpty()){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(list);
                moreCommentAdapter.notifyDataSetChanged();
                if(list.size()<20){
                    isDataOver=true;
                    moreCommentAdapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
                content();
                frame.refreshComplete();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }
}
