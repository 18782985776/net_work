package com.jobnew.farm.module.personal.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.aries.ui.widget.alert.UIAlertView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.AddressBean;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.data.rxbus.MsgEvent;
import com.jobnew.farm.data.rxbus.RxBus;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.personal.adapter.AddressManageAdapter;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by wc on 2017/5/26.
 * Function：地址管理
 * desc：
 */

public class AddressManage extends BaseActivity {
    @BindView(R.id.recyclerView_adressManage)
    RecyclerView recyclerViewAdressManage;
    private AddressManageAdapter adapter;
    private int requestCode;

    @Override
    protected int getLayout() {
        return R.layout.activity_address_manage;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        registerRxBus();
        requestCode = getIntent().getIntExtra(Constant.ADDRESS_MANAGE, -1);
        initList();
    }
    Disposable subscribe;
    /**
     * 注册rxbus
     */
    private void registerRxBus() {
        Flowable<MsgEvent> f = RxBus.getInstance().register(MsgEvent.class);
        subscribe = f.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MsgEvent>() {
            @Override
            public void accept(@NonNull MsgEvent msgEvent) throws Exception {
                if (msgEvent.getRequest() == 123) {
                    refreshAddress();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe!=null&&!subscribe.isDisposed()){
            subscribe.dispose();
        }
    }
    List<AddressBean> list;
    private void initList() {
        list = new ArrayList<>();
        adapter = new AddressManageAdapter(R.layout.activity_adressmanage_item,list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerViewAdressManage.setLayoutManager(layoutManager);
        recyclerViewAdressManage.setAdapter(adapter);
        refreshAddress();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (requestCode == 104){
                    Intent intent = new Intent().putExtra(Constant.ADDRESS_MANAGE, list.get(position));
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    AppManager.jump(EditAddressActivity.class, "address", new Gson().toJson(list.get(position)));
                }
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.txt_is_default:
                        String alert="";
                        if (list.get(position).isIsDefault()){
                            alert="确定要取消当前地址为默认地址吗?";
                        }else{
                            alert="确定要设置当前地址为默认地址吗?";
                        }
                        AlertUtil.show(mContext, alert, "取消", "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which==DialogInterface.BUTTON_POSITIVE){
                                    SetDefaultAddress(!list.get(position).isIsDefault(),list.get(position).getId());
                                }
                            }
                        });
                        break;
                    case R.id.edit_address:
                        AppManager.jump(EditAddressActivity.class,"address",new Gson().toJson(list.get(position)));
                        break;
                    case R.id.del_address:
                        AlertUtil.show(mContext, "确定要删除当前地址吗?", "取消", "删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which==DialogInterface.BUTTON_POSITIVE) {
                                    deleteAddress(list.get(position).getId());
                                }
                            }
                        });
                        break;
                }
            }
        });
    }
    /**
     * 刷新地址
     */
    private void refreshAddress() {
        TestRepository.getIns().QueryAddress(new HashMap<>())
                .subscribe(new DefaultSubscriber<BaseEntity<List<AddressBean>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<AddressBean>> entity) {
                list.clear();
                list.addAll(entity.data);
                adapter.notifyDataSetChanged();
                if (entity.data==null||entity.data.toString().equals("[]")){
                    empty();
                }else{
                    content();
                }
            }
        });
    }

    @Override
    public void errorChickData() {
        refreshAddress();
    }

    /**
     * 删除当前地址为默认地址
     * @param defaultX
     * @param id
     */
    private void SetDefaultAddress(boolean defaultX, int id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("isDefault",defaultX+"");
        TestRepository.getIns().IsDefaultAddress(id+"",map).subscribe(new DefaultSubscriber<BaseEntity>(this,"") {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("设置成功");
                refreshAddress();
            }
        });
    }
    /**
     * 删除当前地址
     * @param id
     */
    private void deleteAddress(int id) {
        TestRepository.getIns().DeleteAddress(id+"",new HashMap<>()).subscribe(new DefaultSubscriber<BaseEntity>(this,"") {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("删除成功");
                refreshAddress();
            }
        });
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        setTitle("地址管理", true);
        titleBar.setRightText("添加");
        titleBar.setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.jump(NewAddAddressActivity.class);
            }
        });
    }
}
