package com.jobnew.farm.module.personal.fragment.MyFarm;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.PersonMyFarmRepository;
import com.jobnew.farm.entity.exclusive.ExclusiveLandEntity;
import com.jobnew.farm.entity.myfarm.MyExclusiveLandEntity;
import com.jobnew.farm.entity.myfarm.PlantingOrderEntity;
import com.jobnew.farm.module.exclusive.ExclusiveLandDetailsActivity;
import com.jobnew.farm.module.personal.activity.MyFarm.LandInformationActivity;
import com.jobnew.farm.module.personal.adapter.MyFarm.MyFarmExclusiveAdapter;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.umeng.socialize.Config.dialog;

/**
 * Created by wufengqiao on 2017/6/13.
 * function：
 * desc：
 */

public class MyFarmExclusiveFragment extends BaseRefreshLoadFragment {

    private List<MyExclusiveLandEntity> mList;
    private MyFarmExclusiveAdapter mAdapter;
    private int pageNo = 1;
    private boolean isRefresh = false;
    private static final int RESULT_PHONE = 201;

    @Override
    protected int getLayout() {
        return R.layout.fragment_refresh_load;
    }

    @Override
    protected void initView(Bundle bundle, View view) {
        loading();
        requestData();

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_record:
                        Intent intent = new Intent();
                        intent.putExtra(Constant.TYPE, 3);
                        intent.putExtra(Constant.ORDER_ID, mList.get(position).orderId);
                        AppManager.jump(LandInformationActivity.class,intent);
                        break;
                    case R.id.iv_phone:
                        showRequestPhone(position);
                        break;
                }
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AppManager.jump(LandInformationActivity.class, LandInformationActivity.TYPE, 3);
            }
        });
    }

    /**
     *
     */
    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("longitude", Constant.LATITUDE + "");
        map.put("latitude", Constant.LATITUDE + "");
        map.put("pageSize", 10 + "");
        map.put("pageNo", pageNo + "");
        PersonMyFarmRepository.getIns().getMyExclusiveLand(map)
                .subscribe(new DefaultSubscriber<List<MyExclusiveLandEntity>>(this) {
                    @Override
                    public void _onNext(List<MyExclusiveLandEntity> list) {
                        if (isRefresh) {
                            mList = list;
                            isRefresh = false;
                            mAdapter.setNewData(mList);
                        } else {
                            mList.addAll(list);
                            mAdapter.loadMoreComplete();
                        }
                        if (list.size() < 10) {
                            mAdapter.loadMoreEnd();
                        }

                        if (mList.size() == 0) {
                            empty();
                        } else {
                            content();
                        }
                    }
                });
    }


    @Override
    public BaseQuickAdapter getAdapter() {
        mList = new ArrayList<>();
        mAdapter = new MyFarmExclusiveAdapter(R.layout.item_my_farm_exclusive, mList);
        return mAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void errorChickData() {
        isRefresh = true;
        loading();
        requestData();
    }

    @Override
    public void onLoadMoreRequested() {
        pageNo++;
        requestData();
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        pageNo = 1;
        isRefresh = true;
    }


    /**
     * 确认打电话
     */
    private void showRequestPhone(int position) {
        AlertUtil.show(getActivity(), "提示", "你是否要拨打电话？", "取消", "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, RESULT_PHONE);
                            return;
                        }
                    }
                    callPhone(position);
                }
            }
        });
    }

    /**
     * 拨打电话
     *
     * @param position
     */
    private void callPhone(int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mList.get(position).farmPhone));
        startActivity(intent);
    }
}
