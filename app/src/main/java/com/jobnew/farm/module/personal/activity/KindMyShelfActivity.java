package com.jobnew.farm.module.personal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.adapter.BazaarThreeAdapter;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.KindMyBean;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.bazaar.BazaarBig;
import com.jobnew.farm.entity.bazaar.BazaarSmall;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.loginAndRegister.activity.LoginActivity;
import com.jobnew.farm.module.personal.adapter.KindMyShelfAdapter;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wc on 2017/6/29.
 * Function：自己种
 * desc：
 */

public class KindMyShelfActivity extends BaseRefreshAndLoadActivity {

    KindMyShelfAdapter adapter;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.img_price)
    ImageView imgPrice;
    @BindView(R.id.txt_location)
    TextView txtLocation;
    private int type = 1;//1 代表价格高 2 代表价格低  3 距离最近
    private String name="";
    private int page = 1;//页数
    private int pageSize=12;//加载条数
    @Override
    protected int getLayout() {
        return R.layout.activity_kind_my_shelf;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("自己种", true);
        name=getIntent().getStringExtra("name");
        changeTitle(1);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                int id = adapter.getItem(position).getFarm().getId();
                String name = adapter.getItem(position).getFarm().getName();
                if (MyApplication.isLogin()) {
                    Intent intent = new Intent();
                    intent.putExtra("farmId", id + "");
                    intent.putExtra("farmName", name+"");
                    AppManager.jump(FarmDetailsActivity.class, intent);
                }else{
                    AppManager.jump(LoginActivity.class);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_price, R.id.txt_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_price:
                if (type == 1) {
                    changeTitle(2);
                } else {
                    changeTitle(1);
                }
                break;
            case R.id.txt_location:
                changeTitle(3);
                break;
        }
    }
    private void changeTitle(int dex) {
        if (dex == 3 && dex == type) return;
        if (dex == 1) {
            txtLocation.setTextColor(getResources().getColor(R.color.c_txt_38));
            txtPrice.setTextColor(getResources().getColor(R.color.main_color));
            imgPrice.setImageResource(R.drawable.farm_icon_screen3);
        } else if (dex == 2) {
            txtLocation.setTextColor(getResources().getColor(R.color.c_txt_38));
            txtPrice.setTextColor(getResources().getColor(R.color.main_color));
            imgPrice.setImageResource(R.drawable.farm_icon_screen4);
        } else {
            txtLocation.setTextColor(getResources().getColor(R.color.main_color));
            txtPrice.setTextColor(getResources().getColor(R.color.c_txt_38));
            imgPrice.setImageResource(R.drawable.farm_icon_screen1);
        }
        type = dex;
        initData(false);
    }

    @Override
    public void errorChickData() {
        initData(false);
    }

    /**
     * 加载数据
     * @param isLoad
     */
    private void initData(boolean isLoad) {
        if (!isLoad) {
            page = 1;
        } else {
            page++;
        }
        Map<String,String> map=new HashMap<>();
        map.put("name",name);
        if (type==3){
            map.put("orderType","position");
        }else{
            map.put("orderType","price");
        }
        if (type==2){
            map.put("orderBy","desc");
        }else{
            map.put("orderBy","asc");
        }
        map.put("longitude", Constant.LONGITUDE+"");
        map.put("latitude",Constant.LATITUDE+"");
        map.put("pageNo",page+"");
        map.put("pageSize",pageSize+"");
        TestRepository.getIns().getKindMy(map).subscribe(new DefaultSubscriber<BaseEntity<List<KindMyBean>>>(this,"获取ing") {
            @Override
            public void _onNext(BaseEntity<List<KindMyBean>> entity) {
                List<KindMyBean> dataSmall = entity.data;
                if (page == 1) {
                    if (dataSmall.size() == 0) {
                        empty();
                    } else {
                        content();
                    }
                }
                if (!isLoad) {
                    dataAll.clear();
                }
                dataAll.addAll(dataSmall);
                adapter.loadMoreComplete();
                ptrLayout.refreshComplete();
                if (dataSmall.size() < pageSize || dataSmall.size() == 0) {
                    adapter.loadMoreEnd(false);
                } else {
                    adapter.setEnableLoadMore(true);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }
    List<KindMyBean> dataAll;
    @Override
    public BaseQuickAdapter getAdapter() {
        dataAll = new ArrayList<>();
        adapter = new KindMyShelfAdapter(R.layout.activity_kind_my_shelf_item, dataAll,mContext);
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public void onLoadMoreRequested() {
        initData(true);
    }


    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        initData(true);
    }
}
