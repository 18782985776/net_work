package com.jobnew.farm;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jobnew.farm.base.fragment.BaseFragment;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.ShareUtilsActivity;
import com.jobnew.farm.module.home.activity.MyFarmHappyOrderActivity;
import com.jobnew.farm.module.home.activity.ScanActivity;
import com.jobnew.farm.module.loginAndRegister.activity.LoginActivity;
import com.jobnew.farm.module.personal.activity.AddressManage;
import com.jobnew.farm.module.personal.activity.CustomerServiceActivity;
import com.jobnew.farm.module.personal.activity.HelpActivity;
import com.jobnew.farm.module.personal.activity.InvestmentActivity;
import com.jobnew.farm.module.personal.activity.MyCollectionActivity;
import com.jobnew.farm.module.personal.activity.MyFarm.MyFarmActivity;
import com.jobnew.farm.module.personal.activity.MyWalletActivity;
import com.jobnew.farm.module.personal.activity.SettingActivity;
import com.jobnew.farm.module.personal.activity.ShoppingTrolleyActivity;
import com.jobnew.farm.module.personal.activity.UserHomeActivity;
import com.jobnew.farm.module.personal.activity.order.MyOrderActivity;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.CircleImageView;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by wangcheng on 2017/5/22.
 * title：我的界面
 * note：
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.ll_TopTitle)
    LinearLayout llTopTitle;
    @BindView(R.id.cirImg_photo)
    CircleImageView cirImgPhoto;
    @BindView(R.id.txt_name)
    TextView txtName;
    Unbinder unbinder;
    @BindView(R.id.img_sex)
    ImageView imgSex;
    @BindView(R.id.txt_level)
    TextView txtLevel;
    @BindView(R.id.rl_scanner)
    ImageView rlScanner;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MyApplication.isLogin()) {
            String sex = MyApplication.user.getUser().getSex();
            String name = MyApplication.user.getUser().getName();
            String avatar = MyApplication.user.getUser().getAvatar();
            if (!TextUtils.isEmpty(name)) {
                txtName.setText(name);
            } else {
                txtName.setText("未编辑");
            }
            if (!TextUtils.isEmpty(sex)) {
                if (sex.equals("2") || sex.equals("女")) {
                    imgSex.setBackgroundResource(R.mipmap.mine_woman);
                } else {
                    imgSex.setBackgroundResource(R.mipmap.mine_man);
                }
            } else {
                imgSex.setBackgroundResource(R.mipmap.mine_man);
            }
            if (!TextUtils.isEmpty(avatar)) {
                Glide.with(mContext).load(avatar).asBitmap().placeholder(R.mipmap.mine_icon_headportrait).error(R.mipmap.mine_icon_headportrait).into(cirImgPhoto);
            }
        } else {
            txtName.setText("未登录");
            imgSex.setBackgroundResource(R.mipmap.mine_man);
            cirImgPhoto.setImageResource(R.mipmap.mine_icon_headportrait);
        }
    }
    String[] mPermissionList = new String[]{
            Manifest.permission.CAMERA,
    };

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(Bundle bundle, View view) {
        setTitleBarPadding(llTopTitle);
    }

    @OnClick({R.id.rl_share, R.id.rl_scanner, R.id.rl_user_home
            , R.id.rl_my_address, R.id.rl_setting, R.id.rl_customer
            , R.id.rl_investment, R.id.rl_order, R.id.ll_payment_order,
            R.id.ll_delivery_order, R.id.ll_goods_order, R.id.ll_evaluation_order
            , R.id.rl_my_wallet, R.id.rl_shop, R.id.rl_my_farm, R.id.rl_help,
            R.id.rl_collection,R.id.rl_my_generated})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_share:
                Intent intent=new Intent(getActivity(),ShareUtilsActivity.class);
                intent.putExtra("stringContent","我要分享");
                intent.putExtra("url","https://www.baidu.com/");
                startActivityForResult(intent,8);
                break;
            case R.id.rl_scanner:
                if (MyApplication.isLogin()) {
                    showPopupWindow(rlScanner);
                } else {
                    AppManager.jump(LoginActivity.class);
                }
                break;
            case R.id.rl_collection:
                AppManager.jump(MyCollectionActivity.class);
                break;
            case R.id.rl_shop:
                AppManager.jump(ShoppingTrolleyActivity.class);
                break;
            case R.id.rl_my_farm:
                jump(MyFarmActivity.class);
                break;
            case R.id.rl_help:
                showMsg("帮助反馈");
                Intent intent1=new Intent();
                intent1.putExtra("categorySn","MYINTRODUCE");
                AppManager.jump(HelpActivity.class,intent1);
                break;
            case R.id.rl_my_wallet:

                AppManager.jump(MyWalletActivity.class);
                break;
            case R.id.rl_order://全部订单
                if (MyApplication.isLogin()) {
                    AppManager.jump(MyOrderActivity.class, "key", 0);
                } else {
                    AppManager.jump(LoginActivity.class);
                }
                break;
            case R.id.ll_payment_order://待付款
                if (MyApplication.isLogin()) {
                    AppManager.jump(MyOrderActivity.class, "key", 1);
                } else {
                    AppManager.jump(LoginActivity.class);
                }
                break;
            case R.id.ll_delivery_order://待发货
                if (MyApplication.isLogin()) {
                    AppManager.jump(MyOrderActivity.class, "key", 2);
                } else {
                    AppManager.jump(LoginActivity.class);
                }
                break;
            case R.id.ll_goods_order://待收货
                if (MyApplication.isLogin()) {
                    AppManager.jump(MyOrderActivity.class, "key", 3);
                } else {
                    AppManager.jump(LoginActivity.class);
                }
                break;
            case R.id.ll_evaluation_order://待评价
                if (MyApplication.isLogin()) {
                    AppManager.jump(MyOrderActivity.class, "key", 4);
                } else {
                    AppManager.jump(LoginActivity.class);
                }
                break;
            case R.id.rl_investment:
                jump(InvestmentActivity.class);
                break;
            case R.id.rl_customer:
                AppManager.jump(CustomerServiceActivity.class);
                break;
            case R.id.rl_user_home:
                jump(UserHomeActivity.class);
                break;
            case R.id.rl_my_address:
                jump(AddressManage.class);
                break;
            case R.id.rl_setting:
                jump(SettingActivity.class);
                break;
            case R.id.rl_my_generated:
                jump(MyFarmHappyOrderActivity.class);
                break;
        }
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        AppManager.jump(ScanActivity.class);
    }
    PopupWindow popupWindow;
    /**弹出筛选框*/
    private void showPopupWindow(final View view) {
        if (popupWindow==null){
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.pop_window_mine, null);
            LinearLayout ll_0 = (LinearLayout) inflate.findViewById(R.id.ll_my_qr);
            LinearLayout ll_1 = (LinearLayout) inflate.findViewById(R.id.ll_scan);
            ll_0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMyQrCode();
                    popupWindow.dismiss();
                }
            });
            ll_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    requestPermissions();
                }
            });
            popupWindow=new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
            popupWindow.setTouchable(true);
            // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            // 设置好参数之后再show
        }
        popupWindow.showAsDropDown(view,0,0);
    }

    private void requestPermissions() {
        if (EasyPermissions.hasPermissions(getActivity(), mPermissionList)) {
            AppManager.jump(ScanActivity.class);
        } else {
            EasyPermissions.requestPermissions(this, "需要开启必要的权限", 123, mPermissionList);
        }
    }

    Dialog dialog;
    ImageView imgQr;
    ImageView imgPicP,imgSexP;
    TextView txtNameP,txtLocationP;
    /**
     * 弹出我的二维码
     */
    private void showMyQrCode() {
        if (dialog==null){
            dialog=new Dialog(mContext);
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_my_qr_code, null);
            imgQr= (ImageView) inflate.findViewById(R.id.img_qr);
            imgPicP= (ImageView) inflate.findViewById(R.id.img_title);
            imgSexP= (ImageView) inflate.findViewById(R.id.img_sex);
            txtNameP= (TextView) inflate.findViewById(R.id.txt_name);
            txtLocationP= (TextView) inflate.findViewById(R.id.txt_location);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(inflate);
        }
        String sex = MyApplication.user.getUser().getSex();
        String name = MyApplication.user.getUser().getName();
        String avatar = MyApplication.user.getUser().getAvatar();
        String city = MyApplication.user.getUser().getCity();
        if (!TextUtils.isEmpty(city)) {
            txtLocationP.setText(city);
        } else {
            txtLocationP.setText("成都");
        }
        if (!TextUtils.isEmpty(name)) {
            txtNameP.setText(name);
        } else {
            txtNameP.setText("未编辑");
        }
        if (!TextUtils.isEmpty(sex)) {
            if (sex.equals("2") || sex.equals("女")) {
                imgSexP.setBackgroundResource(R.mipmap.mine_woman);
            } else {
                imgSexP.setBackgroundResource(R.mipmap.mine_man);
            }
        } else {
            imgSexP.setBackgroundResource(R.mipmap.mine_man);
        }
        if (!TextUtils.isEmpty(avatar)) {
            Glide.with(mContext).load(avatar).asBitmap().placeholder(R.mipmap.mine_icon_headportrait).error(R.mipmap.mine_icon_headportrait).into(imgPicP);
        }
        createChineseQRCode();
        dialog.show();
    }
    private void createChineseQRCode() {
        new MyTask(getActivity(),imgQr).execute();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (popupWindow!=null){
            popupWindow=null;
        }
        if (dialog!=null){
            dialog=null;
        }
    }

    /**
     * 判断是否是登录
     *
     * @param clz
     */
    private void jump(Class clz) {
        if (MyApplication.isLogin()) {
            AppManager.jump(clz);
        } else {
            AppManager.jump(LoginActivity.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    static class MyTask extends AsyncTask<Void, Void, Bitmap>{
        private WeakReference<Activity> weakAty;
        private ImageView img;
        public MyTask(Activity activity,ImageView imgQr){
            weakAty = new WeakReference<Activity>(activity);
            this.img=imgQr;
        }
        @Override
        protected Bitmap doInBackground(Void... params) {
            return QRCodeEncoder.syncEncodeQRCode("测试生成的二维码", BGAQRCodeUtil.dp2px(weakAty.get(), 200));
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                img.setImageBitmap(bitmap);
            } else {
                img.setImageResource(R.mipmap.ic_error);
            }
        }
    }
}
