package com.jobnew.farm.wxapi;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.module.home.activity.MyFarmHappyOrderActivity;
import com.jobnew.farm.module.personal.activity.MyFarm.MyFarmActivity;
import com.jobnew.farm.module.personal.activity.MyFarm.PlantingPlanDetailsActivity;
import com.jobnew.farm.module.personal.activity.MyWalletActivity;
import com.jobnew.farm.module.personal.activity.order.MyOrderActivity;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.AppManager;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by wc on 2017/7/4.
 * Function：
 * desc：
 */

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler{
    private static final String TAG = "WXPayEntryActivity";

    private IWXAPI api;
    @Override
    protected int getLayout() {
        return R.layout.pay_result;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        // 注册微信api
        api= WXAPIFactory.createWXAPI(this, MyApplication.wxappid);
        api.handleIntent(getIntent(),this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent,this);
    }


    @Override
    public void onReq(BaseReq baseReq) {
        Log.d("TAG", "errCode = " + baseReq.getType());
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int code = baseResp.errCode;
            switch (code) {
                case 0:
                    int Type = SPUtils.get(Constant.PAY_TYPE, 0);
                    int orderType = SPUtils.get(Constant.ORDER_ID, 0);
                    int typeO = SPUtils.get(Constant.TYPE, 0);
                    if (Type==2){
                        Intent intent=new Intent();
                        intent.putExtra(Constant.ORDER_ID,orderType);
                        intent.putExtra(Constant.TYPE,typeO);
                        AppManager.jump(PlantingPlanDetailsActivity.class,intent);
                        finish();
                    }else if (Type==0||Type==1){
                        AppManager.jumpAndFinish(MyFarmActivity.class);
                    }else if (Type==3){
                        AppManager.jumpAndFinish(MyFarmHappyOrderActivity.class);
                    }else if (Type==4){
                        AppManager.jumpAndFinish(MyOrderActivity.class);
                    }else if (Type==5){
                        AppManager.jumpAndFinish(MyWalletActivity.class);
                    }
                    showMsg("支付成功");
                    break;
                case -1:
                    showMsg("支付失败");
                    finish();
                    break;
                case -2:
                    showMsg("支付取消");
                    finish();
                    break;
                default:
                    showMsg("支付失败");
                    finish();
                    break;
            }
        }
    }
}
