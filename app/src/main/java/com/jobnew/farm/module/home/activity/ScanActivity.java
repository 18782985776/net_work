package com.jobnew.farm.module.home.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.FarmToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * Created by wangcheng on 2017/4/20.
 * title：扫一扫
 * note：
 */

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {
    @BindView(R.id.qr_zx)
    ZXingView qrZx;
    MyReceiver receiver;
    @BindView(R.id.rl_titleTop)
    RelativeLayout rlTitleTop;
    @BindView(R.id.txt_qita)
    TextView txtQita;

    @Override
    protected int getLayout() {
        return R.layout.activity_scan;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBarPadding(rlTitleTop);
        initQrZx();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);//home键
        mFilter.addAction(Intent.ACTION_SCREEN_ON);  //开屏
        mFilter.addAction(Intent.ACTION_SCREEN_OFF);//锁屏
        mFilter.addAction(Intent.ACTION_USER_PRESENT);//解锁
        receiver = new MyReceiver();
        registerReceiver(receiver, mFilter);
    }

    private void initQrZx() {
        qrZx.setDelegate(this);
        qrZx.showScanRect();
        qrZx.startSpotDelay(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        qrZx.setVisibility(View.GONE);
        QrType(result);
    }

    /**
     * 判断类型
     *
     * @param result
     */
    private void QrType(String result) {
        try {
            JSONObject json = new JSONObject(result);
            String data = json.getString("data");
            String qrType = json.getString("qrType");
            if (!TextUtils.isEmpty(qrType)) {
                if (qrType.equals("tv_login")) {
                    AlertUtil.show(mContext, "确认登录网农公社TV版", "取消", "确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == DialogInterface.BUTTON_POSITIVE) {
                                initQrLogin(data);
                            } else {
                                finish();
                            }
                        }
                    });
                }
            } else {
                txtQita.setText(result);
                txtQita.setVisibility(View.VISIBLE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            txtQita.setText(result);
            txtQita.setVisibility(View.VISIBLE);
            return;
        }
    }

    /**
     * 登录Tv版
     *
     * @param data
     */
    private void initQrLogin(String data) {
        HashMap<String, String> map = new HashMap<>();
        map.put("qrCodeData", data);
        TestRepository.getIns().getTVLogin(map).subscribe(new DefaultSubscriber<BaseEntity>(this, false) {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg(entity.msg);
                finish();
            }
        });
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        FarmToastUtils.show("打开相机出错");
        finish();
    }

    @Override
    protected void onStop() {
        if (qrZx != null) {
            qrZx.stopCamera();
        }
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (qrZx != null) {
            qrZx.onDestroy();
        }
        unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
            case R.id.rl_back:
                finish();
                break;
        }
    }


    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                //锁屏
                finish();
            } else if (action.equals(Intent.ACTION_SCREEN_ON)) {
//                listener.getCode(2);
            }
        }
    }
}

