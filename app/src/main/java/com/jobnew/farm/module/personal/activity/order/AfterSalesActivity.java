package com.jobnew.farm.module.personal.activity.order;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.bazaar.AfterSalesBean;
import com.jobnew.farm.entity.bazaar.OrderBazaarBean;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.ProductDetailsActivity;
import com.jobnew.farm.module.loginAndRegister.activity.LoginActivity;
import com.jobnew.farm.module.personal.activity.OrderEvaluateActivity;
import com.jobnew.farm.module.personal.adapter.AfterSalesAdapter;
import com.jobnew.farm.module.personal.adapter.MyOrderAdapter;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.RegexUtil;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.ClearEditText;
import com.sina.weibo.sdk.api.share.Base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wc on 2017/6/23.
 * Function：我的售后
 * desc：
 */

public class AfterSalesActivity extends BaseRefreshAndLoadActivity {
    private AfterSalesAdapter adapter;
    private List<AfterSalesBean> dataAll;
    private int page = 1;//页数
    private int pageSize=5;//加载条数
    @Override
    protected int getLayout() {
        return R.layout.activity_after_sales;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("我的售后",true);
        initData(false,1);
        recycleOnChick();
    }
    /**
     * recycleView的点击事件
     *
     */
    private void recycleOnChick() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                int item = adapter.getItem(position).getOrderItem().getProductId();
                AppManager.jump(ProductDetailsActivity.class,"id",item);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter1, View view, int position) {
                switch (view.getId()){
                    case R.id.rl_farm:
                        if (MyApplication.isLogin()) {
                            Intent intent = new Intent();
                            intent.putExtra("farmId", adapter.getItem(position).getOrder().getFarm().getId()+ "");
                            intent.putExtra("farmName", adapter.getItem(position).getOrder().getFarm().getName());
                            AppManager.jump(FarmDetailsActivity.class, intent);
                        }else{
                            AppManager.jump(LoginActivity.class);
                        }
                        break;
                    case R.id.img_contact:
                        showMsg("聊天");
                        break;
                    case R.id.txt_application_details:
                        if (adapter.getItem(position).getReturnStatus().equals("canceled")){
                            showMsg("申请已撤销,请不要重复申请");
                        }else{
                            //调转申请详细
                            AppManager.jump(AfterDetailsActivity.class,"id",adapter.getItem(position).getId());
                        }
                        break;
                    case R.id.txt_cancel_application:
                        AlertUtil.show(mContext, "只能申请一次售后\n撤销后您将不能再申请售后！", "取消", "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which==DialogInterface.BUTTON_POSITIVE){
                                    canCelApplication(adapter.getItem(position).getId());
                                }
                            }
                        });
                        break;
                    case R.id.txt_platform_in:
                        showPlatForm(adapter.getItem(position).getId());
                        break;
                }
            }
        });
    }

    /**
     * 撤销申请售后
     * @param id
     */
    private void canCelApplication(int id) {
        TestRepository.getIns().canCelApplication(id+"",new HashMap<String,String>()).subscribe(new DefaultSubscriber<BaseEntity>(this,"申请中...") {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("撤销申请成功");
                initData(false,2);
            }
        });
    }

    AlertDialog dialog;
    Button btnSure;
    Button btnCancel;
    ClearEditText etPhone;
    /**
     * 弹出生气平台介入
     */
    private void showPlatForm(int id) {
        if (dialog == null) {
            View view = View.inflate(mContext, R.layout.dialog_plat_form_in, null);
            btnSure = (Button) view.findViewById(R.id.btn_sure);
            btnCancel = (Button) view.findViewById(R.id.btn_cancel);
            etPhone= (ClearEditText) view.findViewById(R.id.et_phone);
            dialog = new AlertDialog.Builder(mContext)
                    .setView(view)
                    .create();
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDismiss();
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etPhone.getText().toString())){
                    showMsg("请输入电话号码");
                }else{
                    if (!RegexUtil.isTelNum(etPhone.getText().toString())) {
                        showMsg("请输入11位有效手机号!");
                    }else{
                        palatBind(id,etPhone.getText().toString());

                    }
                }

            }
        });
        dialog.show();
    }

    /**
     * 申请平台介入
     * @param id
     * @param phone
     */
    private void palatBind(int id, String phone) {
        Map<String, String> map=new HashMap<>();
        map.put("id",id+"");
        map.put("phone",phone);
        TestRepository.getIns().palatBind(map).subscribe(new DefaultSubscriber<BaseEntity>(this,"提交中...") {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("提交成功,请耐心等待");
                initData(false,2);
            }
        });
        dialogDismiss();
    }

    private void dialogDismiss() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialogDismiss();
    }

    /**
     * 加载数据
     */
    private void initData(boolean isLoad,int a) {
        if (a==1){
            loading();
        }
        if (!isLoad) {
            page = 1;
        } else {
            page++;
        }
        Map<String, String> map=new HashMap<>();
        map.put("pageNo",page+"");
        map.put("pageSize",pageSize+"");
        TestRepository.getIns().getAfterOrder(map).subscribe(new DefaultSubscriber<BaseEntity<List<AfterSalesBean>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<AfterSalesBean>> entity) {
                List<AfterSalesBean> dataSmall = entity.data;
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
    @Override
    public void errorChickData() {
        initData(false,1);
    }
    @Override
    public BaseQuickAdapter getAdapter() {
        dataAll = new ArrayList<>();
        adapter = new AfterSalesAdapter(R.layout.activity_after_sales_item, dataAll,mContext);
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public void onLoadMoreRequested() {
        initData(true,2);
    }
    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        initData(false,2);
    }
}
