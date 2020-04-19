package com.jobnew.farm.module.farm.activity.farmActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.PayResult;
import com.jobnew.farm.entity.PayWeiXinInfo;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.home.activity.MyFarmHappyOrderActivity;
import com.jobnew.farm.module.personal.activity.MyFarm.MyFarmActivity;
import com.jobnew.farm.module.personal.activity.MyFarm.PlantingPlanDetailsActivity;
import com.jobnew.farm.module.personal.activity.order.MyOrderActivity;
import com.jobnew.farm.utils.Arith;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.AppManager;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单支付界面
 */
public class PayOrderActivity extends BaseActivity {


    @BindView(R.id.img_ali_pay)
    ImageView imgAliPay;
    @BindView(R.id.img_weixin_pay)
    ImageView imgWeixinPay;
    @BindView(R.id.img_balance_pay)
    ImageView imgBalancePay;
    @BindView(R.id.img_farm_pay)
    ImageView imgFarmPay;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    private int type = 1;//1 代表支付宝  2 代表微信  3代表 网农币 4 代表 余额支付
    private IWXAPI api;
    private static final int SDK_PAY_FLAG = 1;
    @Override
    protected int getLayout() {
        return R.layout.activity_pay_order;
    }

    String out_trade_no = "";
    int total_fee = 0;
    String body = "";
    String payOrderType="";
    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("支付订单", true);
        Intent intent = getIntent();
        out_trade_no = intent.getStringExtra(Constant.ORDER_SN);
        total_fee = (int) Arith.mul(intent.getDoubleExtra(Constant.TOTAL_PRICE, 0.00), 100);
        body = intent.getStringExtra(Constant.ORDER_BODY);
        payOrderType = intent.getStringExtra(Constant.TYPE);
        tvTotalPrice.setText(intent.getDoubleExtra(Constant.TOTAL_PRICE, 0.00) + "");
        // 微信
        api = WXAPIFactory.createWXAPI(this, MyApplication.wxappid);
        api.registerApp(MyApplication.wxappid);
    }

    @OnClick({R.id.rl_ali, R.id.rl_weixin,
            R.id.rl_balance, R.id.rl_fram, R.id.txt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_ali:
                shift(1);
                break;
            case R.id.rl_weixin:
                shift(2);
                break;
            case R.id.rl_fram:
                shift(3);
                break;
            case R.id.rl_balance:
                shift(4);
                break;
            case R.id.txt_submit:
                payType();
                break;
        }
    }

    /**
     * 支付类型
     */
    private void payType() {
        switch (type) {
            case 1:
                PayAli();
                break;
            case 2:
                if (!isWXAppInstalledAndSupported(api)) {
                    showMsg("请先安装微信");
                    return;
                }
                boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
                if (!isPaySupported) {
                    showMsg("当前微信版本不支持微信支付");
                    return;
                }
                payWeiXin();
                break;
            case 3:
                showMsg("程序员正在拼命滴加班中...");
                break;
            case 4:
                showMsg("程序员正在拼命滴加班中...");
                break;
        }
    }



    /**
     * 去支付宝支付
     */
    private void  PayAli() {
        Map<String, String> map = new HashMap<>();
        map.put("orderSn", out_trade_no);

        map.put("payOrderType", payOrderType);
        TestRepository.getIns().PayAli(map).subscribe(new DefaultSubscriber<BaseEntity>(this, "") {
            @Override
            public void _onNext(BaseEntity entity) {
                Log.e("-----------", entity.data.toString());
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // 构造PayTask 对象
                        PayTask alipay = new PayTask(PayOrderActivity.this);
                        // 调用支付接口，获取支付结果
                        Map<String, String> result = alipay.payV2(entity.data.toString(), true);
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // Toast.makeText(OrderDetailActivity.this, "支付成功",
                        // Toast.LENGTH_SHORT).show();
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
                        }
                        showMsg("支付成功");
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            showMsg("支付结果确认中");
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            showMsg("支付失败");
                        }
                    }
                }
            }
        }
    };
        /**
         * 去微信支付
         */
        private void payWeiXin() {
            Map<String, String> map = new HashMap<>();
            map.put("body", body);
            map.put("out_trade_no", out_trade_no);
            map.put("total_fee", total_fee + "");
            map.put("spbill_create_ip", getIp());
            TestRepository.getIns().PayWinXin(map).subscribe(new DefaultSubscriber<BaseEntity<PayWeiXinInfo>>(this, "") {
                @Override
                public void _onNext(BaseEntity<PayWeiXinInfo> entity) {
                    PayWeiXinInfo data = entity.data;
                    payWithWeiXinTwo(data);
                }
            });
        }

        /**
         * 发起微信支付
         **/
        private void payWithWeiXinTwo(PayWeiXinInfo info) {
            if (info == null)
                return;
            try {
                PayReq req = new PayReq();
                req.appId = info.getAppid() + "";
                Log.i("msg", "==appid==" + info.getAppid() + "");
                req.partnerId = info.getPartnerid();
                Log.i("msg", "==partnerid==" + info.getPartnerid());
                req.prepayId = info.getPrepayid();
                Log.i("msg", "==prepayId==" + info.getPrepayid());
                req.nonceStr = info.getNoncestr();
                Log.i("msg", "==noncestr==" + info.getNoncestr());
                req.timeStamp = info.getTimestamp();
                Log.i("msg", "==timestamp==" + info.getTimestamp());
                req.packageValue = info.getPackageValue();
                Log.i("msg", "==packageValue==" + info.getPackageValue());
                req.sign = info.getSign();
                Log.i("msg", "==sign==" + info.getSign());
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                // 微信
                api = WXAPIFactory.createWXAPI(this, MyApplication.wxappid);
                api.registerApp(MyApplication.wxappid);
                api.sendReq(req);
                Log.i("msg", "=======发起weixin支付");
            } catch (Exception e) {
                Log.e("PAY_GET", "异常：" + e.getMessage());
                showMsg("异常：" + e.getMessage());
            }
        }

        /**
         * 獲取當前ip
         */
        private String getIp() {
            String ip = "";
            ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mobileNetworkInfo.isConnected()) {
                ip = getLocalIpAddress();
                System.out.println("本地ip-----" + ip);
            } else if (wifiNetworkInfo.isConnected()) {
                WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                int ipAddress = wifiInfo.getIpAddress();
                ip = intToIp(ipAddress);
                System.out.println("wifi_ip地址为------" + ip);
            }
            return ip;
        }

        public String getLocalIpAddress() {
            try {
                String ipv4 = "";
                ArrayList<NetworkInterface> nilist = Collections.list(NetworkInterface.getNetworkInterfaces());
                for (NetworkInterface ni : nilist) {
                    ArrayList<InetAddress> ialist = Collections.list(ni.getInetAddresses());
                    for (InetAddress address : ialist) {
                        if (!address.isLoopbackAddress() && address instanceof Inet4Address) {
                            return ipv4;
                        }
                    }
                }
            } catch (SocketException ex) {
                Log.e("localip", ex.toString());
            }
            return null;
        }

        public static String intToIp(int ipInt) {
            StringBuilder sb = new StringBuilder();
            sb.append(ipInt & 0xFF).append(".");
            sb.append((ipInt >> 8) & 0xFF).append(".");
            sb.append((ipInt >> 16) & 0xFF).append(".");
            sb.append((ipInt >> 24) & 0xFF);
            return sb.toString();
        }

        /**
         * 判斷是否安裝微信
         * @param api
         * @return
         */
        private boolean isWXAppInstalledAndSupported(IWXAPI api) {
            return api.isWXAppInstalled() && api.isWXAppSupportAPI();
        }

        /**
         * 转换
         *
         * @param a
         */
        private void shift(int a) {
            if (type == a) return;
            if (a == 1) {
                imgAliPay.setImageResource(R.mipmap.farm_icon_selected);
                imgWeixinPay.setImageResource(R.mipmap.farm_icon_notselected);
                imgBalancePay.setImageResource(R.mipmap.farm_icon_notselected);
                imgFarmPay.setImageResource(R.mipmap.farm_icon_notselected);
            } else if (a == 2) {
                imgAliPay.setImageResource(R.mipmap.farm_icon_notselected);
                imgWeixinPay.setImageResource(R.mipmap.farm_icon_selected);
                imgBalancePay.setImageResource(R.mipmap.farm_icon_notselected);
                imgFarmPay.setImageResource(R.mipmap.farm_icon_notselected);
            } else if (a == 4) {
                imgAliPay.setImageResource(R.mipmap.farm_icon_notselected);
                imgWeixinPay.setImageResource(R.mipmap.farm_icon_notselected);
                imgBalancePay.setImageResource(R.mipmap.farm_icon_selected);
                imgFarmPay.setImageResource(R.mipmap.farm_icon_notselected);
            } else if (a == 3) {
                imgAliPay.setImageResource(R.mipmap.farm_icon_notselected);
                imgWeixinPay.setImageResource(R.mipmap.farm_icon_notselected);
                imgBalancePay.setImageResource(R.mipmap.farm_icon_notselected);
                imgFarmPay.setImageResource(R.mipmap.farm_icon_selected);
            }
            type = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // TODO: add setContentView(...) invocation
            ButterKnife.bind(this);
        }
    }
