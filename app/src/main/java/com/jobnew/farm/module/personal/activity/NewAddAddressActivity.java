package com.jobnew.farm.module.personal.activity;

import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.data.rxbus.MsgEvent;
import com.jobnew.farm.data.rxbus.RxBus;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.utils.RegexUtil;
import com.jobnew.farm.utils.addressPicker.AddressPicker;
import com.jobnew.farm.widget.ClearEditText;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wc on 2017/5/26.
 * Function：添加新地址界面
 * desc：
 */

public class NewAddAddressActivity extends BaseActivity implements AddressPicker.OnAddressPickerChangListener {
    @BindView(R.id.txt_address)
    TextView txtAddress;
    @BindView(R.id.et_user_name)
    ClearEditText etUserName;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
//    @BindView(R.id.et_code)
//    EditText etCode;
    @BindView(R.id.cb_agree)
    CheckBox cbAgree;
    @BindView(R.id.et_detailed_address)
    EditText etDetailedAddress;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        setTitle("添加收货地址", true);
        titleBar.setRightText("保存");
        titleBar.setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDate()) {
                    goSave();
                }
            }
        });
    }
    private String zipCode="";
    private String ProvinceId="";
    /**
     * 保存地址
     */
    private void goSave() {
        Map<String, String> map=new HashMap<>();
        map.put("contactName",etUserName.getText().toString());
        map.put("phone",etPhone.getText().toString());
//        map.put("province",province);
//        map.put("city",city);
        map.put("provinceId",ProvinceId);
        map.put("address",etDetailedAddress.getText().toString());
        map.put("areaId",zipCode);
        map.put("isDefault",cbAgree.isChecked()+"");
        TestRepository.getIns().AddAddress(map).subscribe(new DefaultSubscriber<BaseEntity>(this,"保存中...") {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("保存地址成功");
                RxBus.getInstance().post(new MsgEvent(123,123,"刷新"));
                finish();
            }
        });
    }
    String name;
    String phone;
    String address;
//    String code;
    String detailedAddress;
    /**
     * 检查地址是否合理
     */
    private boolean checkDate() {
        name = etUserName.getText().toString();
        phone = etPhone.getText().toString();
        address = txtAddress.getText().toString();
//        code=etCode.getText().toString();
        detailedAddress = etDetailedAddress.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showMsg("请填写收货人姓名");
            return false;
        }
        if (!TextUtils.isEmpty(name) && name.length() < 2) {
            showMsg("收货人姓名不少于2个字");
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            showMsg("请输入手机号！");
            return false;
        }
        if (!RegexUtil.isTelNum(phone)) {
            showMsg("请输入11位有效手机号!");
            return false;
        }
//        if (!RegexUtil.isCode(code)) {
//            showMsg("请输入正确的邮政编码!");
//            return false;
//        }
        if (TextUtils.isEmpty(address)) {
            showMsg("请选择地址！");
            return false;
        }
        if (TextUtils.isEmpty(detailedAddress)) {
            showMsg("请输入详细地址！");
            return false;
        }
        if (!TextUtils.isEmpty(detailedAddress)&&detailedAddress.length()<5) {
            showMsg("详细地址不少于5个字！");
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rl_address)
    public void onViewClicked() {
        showAddressPicker();
    }
    private AddressPicker mAddressPicker;
    /**
     * 地址管理
     */
    private void showAddressPicker() {
        hideSoftInput();
        if (mAddressPicker == null) {
            mAddressPicker = new AddressPicker(mContext);
            mAddressPicker.setListener(this);
        }
        showPopWindowFromBottom(mAddressPicker);
    }
//    private PopupWindow mPopWindow;
    private Dialog dialogAddress;
    /**
     * 初始化popupWindow
     *
     * @param view
     */
    public void showPopWindowFromBottom(View view) {
//        if (mPopWindow != null && mPopWindow.isShowing()) return;
//        mPopWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
//        mPopWindow.setOutsideTouchable(true);//点击外部收起
//        mPopWindow.setFocusable(true);//设置焦点生效
//        mPopWindow.setAnimationStyle(R.style.AnimBottom);
//        mPopWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 0);
//        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                mPopWindow = null;
//            }
//        });
        hideSoftInput();
        if (dialogAddress==null){
            dialogAddress=new Dialog(mContext,R.style.mydialog);
            dialogAddress.setContentView(view);
            dialogAddress.setCancelable(true);
            dialogAddress.setCanceledOnTouchOutside(true);
            Window window = dialogAddress.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity=Gravity.BOTTOM;
            attributes.width=WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(attributes);
            window.setWindowAnimations(R.style.AnimBottom);
        }
        dialogAddress.show();
    }
    private String province="";
    private String city="";
    private String district="";
    @Override
    public void onAddressPickerSelect(String province, String city, String district, String areaId,String provinceId) {
        try {
            this.province=province;
            this.city=city;
            this.district=district;
            txtAddress.setText(province+" "+city+" "+district);
            zipCode=areaId;
            ProvinceId=provinceId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        popWindowDismiss();
    }
    public void popWindowDismiss() {
//        if (mPopWindow != null) mPopWindow.dismiss();
        if (dialogAddress!=null){
            dialogAddress.dismiss();
        }
    }

    @Override
    public void onAddressPickerCancel() {
        popWindowDismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        popWindowDismiss();
    }
}
