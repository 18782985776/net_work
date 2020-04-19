package com.jobnew.farm.module.personal.activity;

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
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.base.HelpEntity;
import com.jobnew.farm.module.personal.adapter.MyHelpAdapter;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;
import com.marno.easystatelibrary.EasyStatusView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class HelpActivity extends BaseRefreshAndLoadActivity {
    MyHelpAdapter myHelpAdapter;
    ArrayList<HelpEntity> arrayList;
    Unbinder unbinder;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.ptr_layout)
    PtrFrameLayout ptrLayout;
    @BindView(R.id.esv_main)
    EasyStatusView esvMain;
    @BindView(R.id.tv_save)
    TextView tvSave;
    int pageNo = 1;
    int pageSize = 20;
    boolean isDataOver = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_help;
    }

    String categorySn;

    @Override
    protected void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        categorySn = intent.getStringExtra(Constant.CATEGORY_SN);//AFTERSALE表示售后跳转，MYINTRODUCE表示从帮助说明跳转
        initData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 初始化问答数据
     **/
    private void initData() {
        arrayList = new ArrayList<HelpEntity>();
        HashMap map = new HashMap();
        map.put("categorySn", categorySn);
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        MyFarmRepository.getIns().getHelpInfo(map).subscribe(new DefaultSubscriber<BaseEntity<List<HelpEntity>>>(this, false) {
            @Override
            public void _onNext(BaseEntity<List<HelpEntity>> list) {
                if (list.data.size() < pageSize) {
                    isDataOver = true;
                }
                arrayList.clear();
                arrayList.addAll(list.data);
                myHelpAdapter.setNewData(arrayList);
                myHelpAdapter.notifyDataSetChanged();
                pageNo++;
            }
        });

    }

    private void setTitleBar() {
        if ("LEASECLAUSE".equals(categorySn)) {
            tvSave.setVisibility(View.VISIBLE);
            tvSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setResult(RESULT_OK);
                    finish();
                }
            });
            setTitle("土地租赁条例", true);
        } else {
            setTitle("帮助说明", true);
            mTitleBar.setRightText("意见反馈", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FarmToastUtils.show("意见反馈");
                    AppManager.jump(FeedBackActivity.class);
                }
            });
            mTitleBar.getRightTextView().setTextSize(14);
        }
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        myHelpAdapter = new MyHelpAdapter(arrayList, R.layout.item_help);
        return myHelpAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onLoadMoreRequested() {//加载更多
        if (isDataOver) {
            myHelpAdapter.loadMoreEnd(false);//加载完毕，false表示不隐藏脚步试图
            return;
        }
        HashMap map = new HashMap();
        map.put("categorySn", categorySn);
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        MyFarmRepository.getIns().getHelpInfo(map)
                .subscribe(new DefaultSubscriber<BaseEntity<List<HelpEntity>>>(this, false) {
                    @Override
                    public void _onNext(BaseEntity<List<HelpEntity>> list) {
                        if (list.data.size() < pageSize) {
                            isDataOver = true;
                        }
                        arrayList.addAll(list.data);
                        myHelpAdapter.addData(arrayList);
                        myHelpAdapter.notifyDataSetChanged();
                        pageNo++;
                    }
                });

    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {//下拉刷新
        pageNo = 0;
        HashMap map = new HashMap();
        map.put("categorySn", categorySn);
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        MyFarmRepository.getIns().getHelpInfo(map)
                .subscribe(new DefaultSubscriber<BaseEntity<List<HelpEntity>>>(this, false) {
                    @Override
                    public void _onNext(BaseEntity<List<HelpEntity>> list) {
                        if (list.data.size() < pageSize) {
                            isDataOver = true;
                        }
                        arrayList.clear();
                        arrayList.addAll(list.data);
                        myHelpAdapter.setNewData(arrayList);
                        myHelpAdapter.notifyDataSetChanged();
                        frame.refreshComplete();
                        pageNo++;
                    }
                });
    }
}
