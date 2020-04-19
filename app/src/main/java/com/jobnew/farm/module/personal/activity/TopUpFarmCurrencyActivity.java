package com.jobnew.farm.module.personal.activity;

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
import com.jobnew.farm.entity.TopUpBean;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.PayOrderActivity;
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

import java.math.BigDecimal;
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
 * Created by wc on 2017/6/19.
 * Function:网农币充值
 * desc：
 */

public class TopUpFarmCurrencyActivity extends BaseActivity {
    @BindView(R.id.img_ali_pay)
    ImageView imgAliPay;
    @BindView(R.id.img_weixin_pay)
    ImageView imgWeixinPay;
    @BindView(R.id.balance_tv)
    TextView balanceTv;
    @BindView(R.id.img_balance_pay)
    ImageView imgBalancePay;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.txt_count)
    TextView txtCount;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.text6)
    TextView text6;
    @BindView(R.id.text7)
    TextView text7;
    @BindView(R.id.text8)
    TextView text8;
    private int type = 1;//1 代表支付宝  2 代表微信  3 代表 余额支付
    private IWXAPI api;
    @Override
    protected int getLayout() {
        return R.layout.activity_top_up_fram;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("购买网农币", true);
        api = WXAPIFactory.createWXAPI(this, MyApplication.wxappid);
        api.registerApp(MyApplication.wxappid);
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
        } else if (a == 2) {
            imgAliPay.setImageResource(R.mipmap.farm_icon_notselected);
            imgWeixinPay.setImageResource(R.mipmap.farm_icon_selected);
            imgBalancePay.setImageResource(R.mipmap.farm_icon_notselected);
        } else if (a == 3) {
            imgAliPay.setImageResource(R.mipmap.farm_icon_notselected);
            imgWeixinPay.setImageResource(R.mipmap.farm_icon_notselected);
            imgBalancePay.setImageResource(R.mipmap.farm_icon_selected);
        }
        type = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_ali, R.id.rl_weixin, R.id.rl_balance,
            R.id.txt_submit, R.id.img_sub, R.id.img_add,
            R.id.text1, R.id.text2, R.id.text3, R.id.text4,
            R.id.text5, R.id.text6, R.id.text7, R.id.text8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_ali:
                shift(1);
                break;
            case R.id.rl_weixin:
                shift(2);
                break;
            case R.id.rl_balance:
                shift(3);
                break;
            case R.id.txt_submit:
                SPUtils.put(Constant.PAY_TYPE, 5);
                submit();
                break;
            case R.id.img_sub:
                conversion(0);
                int countSub = Integer.parseInt(txtCount.getText().toString());
                if (countSub <= 100) {
                    showMsg("最少充值10元哟~~");
                } else {
                    countSub = countSub - 100;
                }
                setCountAndPrice(countSub);
                break;
            case R.id.img_add:
                conversion(0);
                int countAdd = Integer.parseInt(txtCount.getText().toString());
                if (countAdd > 100000) {
                    showMsg("最多充值一万元哟~~");
                } else {
                    countAdd = countAdd + 100;
                }
                setCountAndPrice(countAdd);
                break;
            case R.id.text1:
                conversion(1);
                break;
            case R.id.text2:
                conversion(2);
                break;
            case R.id.text3:
                conversion(3);
                break;
            case R.id.text4:
                conversion(4);
                break;
            case R.id.text5:
                conversion(5);
                break;
            case R.id.text6:
                conversion(6);
                break;
            case R.id.text7:
                conversion(7);
                break;
            case R.id.text8:
                conversion(8);
                break;
        }
    }

    /**
     * 确认支付
     */
    private void submit() {
        if (type == 1) {
            PayAli();
        } else if (type == 2) {
            if (!isWXAppInstalledAndSupported(api)) {
                showMsg("请先安装微信");
                return;
            }
            boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
            if (!isPaySupported) {
                showMsg("当前微信版本不支持微信支付");
                return;
            }
            goWeiXinPay();
        } else if (type == 3) {
            showMsg("程序员正在拼命滴加班中...");
        }
    }
    private static final int SDK_PAY_FLAG = 1;
    /**
     * 支付宝支付
     */
    private void PayAli() {
        Map<String, String> map = new HashMap<>();
        map.put("title", "网农币充值"+txtCount.getText().toString()+"个");
        map.put("amount", Double.parseDouble(tvTotalPrice.getText().toString())+"");
        map.put("type", "recharge_coin");
        TestRepository.getIns().PayAliChone(map).subscribe(new DefaultSubscriber<BaseEntity>(this, "") {
            @Override
            public void _onNext(BaseEntity entity) {
                Log.e("-----------", entity.data.toString());
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // 构造PayTask 对象
                        PayTask alipay = new PayTask(TopUpFarmCurrencyActivity.this);
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
//                        // Toast.makeText(OrderDetailActivity.this, "支付成功",
//                        // Toast.LENGTH_SHORT).show();
//                        int Type = SPUtils.get(Constant.PAY_TYPE, 0);
//                        int orderType = SPUtils.get(Constant.ORDER_ID, 0);
//                        int typeO = SPUtils.get(Constant.TYPE, 0);
//                        if (Type==2){
//                            Intent intent=new Intent();
//                            intent.putExtra(Constant.ORDER_ID,orderType);
//                            intent.putExtra(Constant.TYPE,typeO);
//                            AppManager.jump(PlantingPlanDetailsActivity.class,intent);
//                            finish();
//                        }else if (Type==0||Type==1){
//                            AppManager.jumpAndFinish(MyFarmActivity.class);
//                        }else if (Type==3){
//                            AppManager.jumpAndFinish(MyFarmHappyOrderActivity.class);
//                        }else if (Type==4){
//                            AppManager.jumpAndFinish(MyOrderActivity.class);
//                        }
                        AppManager.jumpAndFinish(MyWalletActivity.class);
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
    private void goWeiXinPay() {
        Map<String, String> map = new HashMap<>();
        map.put("title", "网农币充值"+txtCount.getText().toString()+"个");
        int mul = (int) Arith.mul(Double.parseDouble(tvTotalPrice.getText().toString()), 100);
        map.put("amount",mul+"");
        map.put("spbill_create_ip", getIp());
//        map.put("attach","");
        map.put("type", "recharge_coin");//  支付订单类型，有如下值：recharge_balance:余额充值,recharge_coin:充值网农比
        TestRepository.getIns().getTopUp(map).subscribe(new DefaultSubscriber<BaseEntity<TopUpBean>>(this,"获取中...") {
            @Override
            public void _onNext(BaseEntity<TopUpBean> entity) {
                TopUpBean data = entity.data;
                payWithWeiXinTwo(data);
            }
        });
    }
    /**
     * 发起微信支付
     **/
    private void payWithWeiXinTwo(TopUpBean info) {
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
    public static String intToIp(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
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
    /**
     * 判斷是否安裝微信
     * @param api
     * @return
     */
    private boolean isWXAppInstalledAndSupported(IWXAPI api) {
        return api.isWXAppInstalled() && api.isWXAppSupportAPI();
    }
    /**
     * 设置值
     *
     * @param count
     */
    private void setCountAndPrice(int count) {
        txtCount.setText(count + "");
        tvTotalPrice.setText(count / 10 + ".00");
    }

    /**
     * 转换
     *
     * @param i
     */
    private void conversion(int i) {
        if (i == 0) {
            setTVBgAndColor(text1, 0);
            setTVBgAndColor(text2, 0);
            setTVBgAndColor(text3, 0);
            setTVBgAndColor(text4, 0);
            setTVBgAndColor(text5, 0);
            setTVBgAndColor(text6, 0);
            setTVBgAndColor(text7, 0);
            setTVBgAndColor(text8, 0);
            return;
        } else if (i == 1) {
            setTVBgAndColor(text1, 1);
            setTVBgAndColor(text2, 0);
            setTVBgAndColor(text3, 0);
            setTVBgAndColor(text4, 0);
            setTVBgAndColor(text5, 0);
            setTVBgAndColor(text6, 0);
            setTVBgAndColor(text7, 0);
            setTVBgAndColor(text8, 0);
            setCountAndPrice(100);
        } else if (i == 2) {
            setTVBgAndColor(text1, 0);
            setTVBgAndColor(text2, 1);
            setTVBgAndColor(text3, 0);
            setTVBgAndColor(text4, 0);
            setTVBgAndColor(text5, 0);
            setTVBgAndColor(text6, 0);
            setTVBgAndColor(text7, 0);
            setTVBgAndColor(text8, 0);
            setCountAndPrice(200);
        } else if (i == 3) {
            setTVBgAndColor(text1, 0);
            setTVBgAndColor(text2, 0);
            setTVBgAndColor(text3, 1);
            setTVBgAndColor(text4, 0);
            setTVBgAndColor(text5, 0);
            setTVBgAndColor(text6, 0);
            setTVBgAndColor(text7, 0);
            setTVBgAndColor(text8, 0);
            setCountAndPrice(500);
        } else if (i == 4) {
            setTVBgAndColor(text1, 0);
            setTVBgAndColor(text2, 0);
            setTVBgAndColor(text3, 0);
            setTVBgAndColor(text4, 1);
            setTVBgAndColor(text5, 0);
            setTVBgAndColor(text6, 0);
            setTVBgAndColor(text7, 0);
            setTVBgAndColor(text8, 0);
            setCountAndPrice(1000);
        } else if (i == 5) {
            setTVBgAndColor(text1, 0);
            setTVBgAndColor(text2, 0);
            setTVBgAndColor(text3, 0);
            setTVBgAndColor(text4, 0);
            setTVBgAndColor(text5, 1);
            setTVBgAndColor(text6, 0);
            setTVBgAndColor(text7, 0);
            setTVBgAndColor(text8, 0);
            setCountAndPrice(2000);
        } else if (i == 6) {
            setTVBgAndColor(text1, 0);
            setTVBgAndColor(text2, 0);
            setTVBgAndColor(text3, 0);
            setTVBgAndColor(text4, 0);
            setTVBgAndColor(text5, 0);
            setTVBgAndColor(text6, 1);
            setTVBgAndColor(text7, 0);
            setTVBgAndColor(text8, 0);
            setCountAndPrice(5000);
        } else if (i == 7) {
            setTVBgAndColor(text1, 0);
            setTVBgAndColor(text2, 0);
            setTVBgAndColor(text3, 0);
            setTVBgAndColor(text4, 0);
            setTVBgAndColor(text5, 0);
            setTVBgAndColor(text6, 0);
            setTVBgAndColor(text7, 1);
            setTVBgAndColor(text8, 0);
            setCountAndPrice(8000);
        } else if (i == 8) {
            setTVBgAndColor(text1, 0);
            setTVBgAndColor(text2, 0);
            setTVBgAndColor(text3, 0);
            setTVBgAndColor(text4, 0);
            setTVBgAndColor(text5, 0);
            setTVBgAndColor(text6, 0);
            setTVBgAndColor(text7, 0);
            setTVBgAndColor(text8, 1);
            setCountAndPrice(10000);
        }
    }

    public void setTVBgAndColor(TextView tv, int a) {
        if (a == 1) {
            tv.setTextColor(getResources().getColor(R.color.main_color));
            tv.setBackgroundResource(R.drawable.bg_color_main);
        } else {
            tv.setTextColor(getResources().getColor(R.color.c_txt_88));
            tv.setBackgroundResource(R.drawable.bg_color_68);
        }
    }
}
