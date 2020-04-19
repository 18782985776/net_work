package com.jobnew.farm.module.personal.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.AddressBean;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.data.rxbus.MsgEvent;
import com.jobnew.farm.data.rxbus.RxBus;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.utils.AlertUtil;
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
 * Function：编辑地址
 * desc：
 */

public class EditAddressActivity extends BaseActivity implements AddressPicker.OnAddressPickerChangListener {
    @BindView(R.id.edt_name)
    ClearEditText edtName;
    @BindView(R.id.edt_phone)
    ClearEditText edtPhone;
//    @BindView(R.id.edt_code)
//    EditText edtCode;
    @BindView(R.id.txt_select_address)
    TextView txtSelectAddress;
    @BindView(R.id.et_detailed_address)
    EditText etDetailedAddress;
    private String zipCode="";
    private String provinceId="";
    @Override
    protected int getLayout() {
        return R.layout.activity_edit_address;
    }
    int id;
    AddressBean addressBean;
    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String address = intent.getStringExtra("address");
        if (!TextUtils.isEmpty(address)) {
            addressBean = new Gson().fromJson(address, AddressBean.class);
            edtName.setText(addressBean.getContactName());
            edtName.setSelection(addressBean.getContactName().length());
            edtPhone.setText(addressBean.getPhone());
            edtPhone.setSelection(addressBean.getPhone().length());
//            edtCode.setText(addressBean.getZipCode());

            zipCode = addressBean.getArea().getId();
            provinceId=addressBean.getProvinceId()+"";
            String[] split = addressBean.getArea().getMergerName().split(",");
            txtSelectAddress.setText(split[0]+" "+split[1]+" "+addressBean.getArea().getName());
            etDetailedAddress.setText(addressBean.getAddress());
            etDetailedAddress.setSelection(addressBean.getAddress().length());
            id = addressBean.getId();
            this.province=split[0];
            this.city=split[1];
            this.district=addressBean.getArea().getName();
        }
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        setTitle("修改收货地址", true);
        titleBar.setRightText("保存");
        titleBar.setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckDate()){
                    goChange();
                }
            }
        });
    }

    /**
     * 改变地址
     */
    private void goChange() {
        Map<String, String> params=new HashMap<>();
        params.put("id",id+"");
        params.put("contactName",edtName.getText().toString());
        params.put("phone",edtPhone.getText().toString());
//        params.put("province",province);
//        params.put("city",city);
//        params.put("area",district);
        params.put("isDefault",addressBean.isIsDefault()+"");
        params.put("areaId",zipCode);
        params.put("provinceId",provinceId);
        params.put("address",etDetailedAddress.getText().toString());
        TestRepository.getIns().ChangeAddress(params).subscribe(new DefaultSubscriber<BaseEntity>(this,"修改中...") {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("保存地址成功");
                RxBus.getInstance().post(new MsgEvent(123,123,"刷新"));
                finish();
            }
        });
    }
    /**
     * 检查是否符合保存的条件
     * @return
     */
    private boolean CheckDate() {
        String s = edtName.getText().toString();
        String s1 = edtPhone.getText().toString();
        String s2 = txtSelectAddress.getText().toString();
        String s3 = etDetailedAddress.getText().toString();
//        String s4 = edtCode.getText().toString();
        if (TextUtils.isEmpty(s)) {
            showMsg("请填写收货人姓名");
            return false;
        }
        if (!TextUtils.isEmpty(s) && s.length() < 2) {
            showMsg("收货人姓名不少于2个字");
            return false;
        }
        if (TextUtils.isEmpty(s1)) {
            showMsg("请输入手机号！");
            return false;
        }
        if (!RegexUtil.isTelNum(s1)) {
            showMsg("请输入11位有效手机号!");
            return false;
        }
//        if (TextUtils.isEmpty(s4)) {
//            showMsg("请输入邮政编码！");
//            return false;
//        }
//        if (!RegexUtil.isCode(s4)) {
//            showMsg("请输入正确的邮政编码!");
//            return false;
//        }
        if (TextUtils.isEmpty(s2)) {
            showMsg("请选择地址！");
            return false;
        }
        if (TextUtils.isEmpty(s3)) {
            showMsg("请输入详细地址！");
            return false;
        }
        if (!TextUtils.isEmpty(s3)&&s3.length()<5) {
            showMsg("详细地址不少于5个字！");
            return false;
        }
        String[] split = addressBean.getArea().getMergerName().split(",");
        if (s.equals(addressBean.getContactName())&&s1.equals(addressBean.getPhone())&&s2.equals(split[0]+" "+split[1]+" "+addressBean.getArea().getName())&&s3.equals(addressBean.getAddress())){
            showMsg("没有修改任何内容哟");
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

    @OnClick({R.id.provincial_city_layout, R.id.ll_deleteAdress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.provincial_city_layout:
                showAddressPicker();
                break;
            case R.id.ll_deleteAdress:
                AlertUtil.show(mContext, "确定要删除当前地址吗?", "取消", "删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==DialogInterface.BUTTON_POSITIVE) {
                            deleteAddress(id);
                        }
                    }
                });
                break;
        }
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
     * 删除当前地址
     * @param id
     */
    private void deleteAddress(int id) {
        TestRepository.getIns().DeleteAddress(id+"",new HashMap<>()).subscribe(new DefaultSubscriber<BaseEntity>(this,"") {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("删除成功");
                RxBus.getInstance().post(new MsgEvent(123,123,"刷新"));
                finish();
            }
        });
    }
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
    public void popWindowDismiss() {
//        if (mPopWindow != null) mPopWindow.dismiss();
        if (dialogAddress!=null){
            dialogAddress.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        popWindowDismiss();
    }
    String province;
    String city;
    String district;
    @Override
    public void onAddressPickerSelect(String province, String city, String district, String areaId,String provinceIds) {
        try {
            txtSelectAddress.setText(province + " " + city + " " + district);
            this.province=province;
            this.city=city;
            this.district=district;
            zipCode=areaId;
            provinceId=provinceIds;
        } catch (Exception e) {
            e.printStackTrace();
        }
        popWindowDismiss();
    }

    @Override
    public void onAddressPickerCancel() {
        popWindowDismiss();
    }
}
