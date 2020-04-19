package com.jobnew.farm.module.personal.activity.MyFarm;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.module.personal.adapter.LandAwayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wc on 2017/7/14.
 * Function：土地赠送
 * desc：
 */

public class LandAwayActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.edt)
    EditText edt;

    @Override
    protected int getLayout() {
        return R.layout.activity_land_away;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("赠送给好友", true);
        initRecyclerView();
    }

    private void initRecyclerView() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            data.add("item");
        }
        LinearLayoutManager lm = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        LandAwayAdapter LAadapter = new LandAwayAdapter(R.layout.activity_land_away_item, data);
        recyclerView.setAdapter(LAadapter);
        LAadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LAadapter.setPotionChick(position);
            }
        });
    }

    @OnClick({R.id.txt_search, R.id.btn_away})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_search:
                if (TextUtils.isEmpty(edt.getText().toString())){
                    showMsg("请输入好友姓名或者电话");
                }else{
                    showMsg("搜索");
                }
                break;
            case R.id.btn_away:
                showMsg("赠送");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
