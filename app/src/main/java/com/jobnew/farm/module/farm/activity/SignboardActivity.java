package com.jobnew.farm.module.farm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.FarmRepository;
import com.jobnew.farm.entity.plan.SignboardEntity;
import com.jobnew.farm.module.farm.adapter.SignboardAdapter;
import com.jobnew.farm.utils.ScreenToolsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wufengqiao on 2017/5/27.
 */

public class SignboardActivity extends BaseRefreshAndLoadActivity {
    @BindView(R.id.rv_content)
    RecyclerView recyclerView;

    private List<SignboardEntity> mList;
    private SignboardAdapter mAdapter;
    private int farmId;
    private int page;
    private int pageSize = 21;
    private int type;

    @Override
    protected int getLayout() {
        return R.layout.activity_select_refresh;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        farmId = getIntent().getIntExtra(Constant.FARM_ID, -1);
        type = getIntent().getIntExtra(Constant.TYPE,0);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.c_txt_108));
        recyclerView.setPadding(ScreenToolsUtils.dp2px(5),0,ScreenToolsUtils.dp2px(5),0);
        setTitle("标识牌", true);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra(Constant.SIGNBOARD, mList.get(position));
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        loading();
        page = 1;
        requestData(false);
    }

    private void requestData(boolean isRefresh) {
        Map<String, String> map = new HashMap<String, String>();
        if (isRefresh) {
            page = 1;
        }
        map.put("farmId", farmId + "");
        map.put("type", type+"");
        map.put("pageNo", page + "");
        map.put("pageSize", pageSize + "");
        FarmRepository.getIns().getSignboard(map).subscribe(new DefaultSubscriber<List<SignboardEntity>>(this, false) {
            @Override
            public void _onNext(List<SignboardEntity> list) {
                content();
                if (page == 1 && list.size() == 0) {
                    empty();
                }
                if (isRefresh) {
                    mList.clear();
                    ptrLayout.refreshComplete();
                }else {
                    if (list.size()< pageSize){
                        mAdapter.loadMoreEnd(true);
                    }else {
                        mAdapter.loadMoreComplete();
                    }
                }
                mList.addAll(list);
                mAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public BaseQuickAdapter getAdapter() {
        mList = new ArrayList<>();
        mAdapter = new SignboardAdapter(R.layout.item_signboard, mList);
        return mAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        requestData(false);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        requestData(true);
    }
}
