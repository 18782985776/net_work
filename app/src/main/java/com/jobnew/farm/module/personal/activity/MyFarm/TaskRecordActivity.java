package com.jobnew.farm.module.personal.activity.MyFarm;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.PersonMyFarmRepository;
import com.jobnew.farm.entity.myfarm.TaskRecordEntity;
import com.jobnew.farm.module.personal.adapter.MyFarm.TaskRecordAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wufengqiao on 2017/6/15.
 * function：
 * desc：
 */

public class TaskRecordActivity extends BaseRefreshAndLoadActivity {

    private List<TaskRecordEntity> mList;
    private TaskRecordAdapter mAdapter;
    private int orderId;
    private int page = 1;
    private int pagesize = 15;
    private boolean isRefresh = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_select_refresh;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("任务记录", true);
        orderId = getIntent().getIntExtra(Constant.ORDER_ID, -1);
        requestData();
    }

    /**
     * 请求网络数据
     */
    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderId + "");
        map.put("pageNo", page + "");
        map.put("pageSize", pagesize + "");
        PersonMyFarmRepository.getIns().getNewTaskInfo(map)
                .subscribe(new DefaultSubscriber<List<TaskRecordEntity>>(this, false) {
                    @Override
                    public void _onNext(List<TaskRecordEntity> list) {
                        if (isRefresh) {
                            ptrLayout.refreshComplete();
                            mList.clear();
                        }
                        mList.addAll(list);
                        if (mList.size() == 0){
                            empty();
                        }
                        if (page > 1) {
                            mAdapter.loadMoreComplete();
                        }
                        if (list.size() < pagesize) {
                            mAdapter.loadMoreEnd();
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        mList = new ArrayList<>();
        mAdapter = new TaskRecordAdapter(R.layout.item_task_record, mList);
        return mAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onLoadMoreRequested() {
        isRefresh = false;
        page++;
        requestData();
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        isRefresh = true;
        page = 1;
        requestData();
    }
}
