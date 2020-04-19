package com.jobnew.farm.module.farm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.FarmRepository;
import com.jobnew.farm.entity.plan.ProductProcessEntity;
import com.jobnew.farm.module.farm.adapter.ProductProcessAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wufengqiao on 2017/6/1.
 * function : 选择产品加工
 */

public class ProductProcessActivity extends BaseRefreshAndLoadActivity {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private ProductProcessAdapter mAdapter;
    private List<ProductProcessEntity> mList;
    private int productId;
    private int page;
    private int pageSize = 10;
    private int number;
    private List<ProductProcessEntity> processEntityList;

    @Override
    protected int getLayout() {
        return R.layout.activity_select_refresh;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("产品加工", true);
        productId = getIntent().getIntExtra(Constant.PRODUCT_ID, -1);
        number = getIntent().getIntExtra(Constant.NUMBER, 0);
        processEntityList = getIntent().getParcelableArrayListExtra(Constant.PRODUCT_PROCESS);
        mAdapter.setNumber(number);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mList.get(position).isSelect = !view.isSelected();
                mAdapter.notifyItemChanged(position);
            }
        });
    }

    @Override
    protected void loadData() {
        loading();
        page = 1;
        requestData(false);
    }

    private void requestData(boolean isRefresh) {
        if (isRefresh) {
            page = 1;
        }
        Map<String, String> map = new HashMap<>();
        map.put("productId", productId + "");
        map.put("pageNo", page + "");
        map.put("pageSize", pageSize + "");
        FarmRepository.getIns().getProductProcess(map).subscribe(new DefaultSubscriber<List<ProductProcessEntity>>(this, false) {
            @Override
            public void _onNext(List<ProductProcessEntity> list) {
                content();
                if (page == 1 && list.size() == 0) {
                    empty();
                    return;
                }
                if (isRefresh) {
                    mList.clear();
                    ptrLayout.refreshComplete();
                } else {
                    if (page == 1) {
                        tvSave.setVisibility(View.VISIBLE);
                    }
                    if (list.size() < pageSize) {
                        mAdapter.loadMoreEnd(true);
                    } else {
                        mAdapter.loadMoreComplete();
                    }
                }
                setSelect(list);
                mList.addAll(list);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 设置默认勾选项
     * @param list
     */
    private void setSelect(List<ProductProcessEntity> list) {
        if (processEntityList != null && processEntityList.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < processEntityList.size(); j++) {
                    if (list.get(i).id == processEntityList.get(j).id) {
                        list.get(i).isSelect = true;
                    }
                }
            }
        }
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        mList = new ArrayList<>();
        mAdapter = new ProductProcessAdapter(R.layout.item_product_process, mList);
        return mAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
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


    @OnClick(R.id.tv_save)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                Intent intent = new Intent();
                ArrayList<ProductProcessEntity> list = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isSelect) {
                        list.add(mList.get(i));
                    }
                }
                intent.putParcelableArrayListExtra(Constant.PRODUCT_PROCESS, list);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
