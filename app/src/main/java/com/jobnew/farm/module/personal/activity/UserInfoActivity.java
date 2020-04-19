package com.jobnew.farm.module.personal.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.LoginEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.utils.DateUtils;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.utils.addressPicker.AddressPicker;
import com.jobnew.farm.widget.CircleImageView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by wc on 2017/5/25.
 * Function：编辑个人资料
 * desc：
 */

public class UserInfoActivity extends BaseActivity implements AddressPicker.OnAddressPickerChangListener {
    private static final int REQUEST_CODE_SELECT = 100;
    @BindView(R.id.txt_messageNickname)
    TextView txtMessageNickname;
    @BindView(R.id.txt_messageGender)
    TextView txtMessageGender;
    @BindView(R.id.txt_location)
    TextView txtLocation;
    @BindView(R.id.txt_signature)
    TextView txtSignature;
    String[] mPermissionList = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };
    @BindView(R.id.img_messageHeader)
    CircleImageView imgMessageHeader;
    @BindView(R.id.txt_birthday)
    TextView txtBirthday;

    @Override
    protected int getLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("编辑个人资料", true);
        initImagePicker();
        initUser();
    }

    /**
     * 初始化个人资料
     */
    private void initUser(){
        LoginEntity.UserEntity user = MyApplication.user.getUser();
        if (!TextUtils.isEmpty(user.getName())){
            txtMessageNickname.setText(user.getName());
        }
        if (!TextUtils.isEmpty(user.getAvatar())){
            Glide.with(mContext).load(user.getAvatar()).asBitmap().placeholder(R.mipmap.mine_icon_headportrait).error(R.mipmap.mine_icon_headportrait).into(imgMessageHeader);
        }
        if (!TextUtils.isEmpty(user.getSex())){
            if (user.getSex().equals("2")||user.getSex().equals("女")){
                txtMessageGender.setText("女");
            }else{
                txtMessageGender.setText("男");
            }
        }
        if (!TextUtils.isEmpty(user.getBirthday()+"")){
            txtBirthday.setText(DateUtils.DateToString(user.getBirthday()+"","yyyy-MM-dd"));
        }
        if (!TextUtils.isEmpty(user.getCity())){
            txtLocation.setText(user.getCity()+"");
        }
        if (!TextUtils.isEmpty(user.getSignature())){
            txtSignature.setText(user.getSignature()+"");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_photo, R.id.rl_gender, R.id.rl_nike,
            R.id.rl_address, R.id.rl_date_selection, R.id.ll_signature})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_photo:
                gendDialog(2);
                break;
            case R.id.rl_gender:
                gendDialog(1);
                break;
            case R.id.rl_nike:
                dialogNikeName(txtMessageNickname.getText().toString() + "");
                break;
            case R.id.rl_address:
                showAddressPicker();
                break;
            case R.id.rl_date_selection:
                showDateSelect();
                break;
            case R.id.ll_signature:
                dialogSignature(txtSignature.getText().toString());
                break;
        }
    }

    AlertDialog SignatureDialog;
    Button btnSureS;
    Button btnCancelS;
    EditText etSign;

    /**
     * 修改个性签名
     *
     * @param s
     */
    private void dialogSignature(String s) {
        showSoftInput();
        if (SignatureDialog == null) {
            View view = View.inflate(mContext, R.layout.dialog_signature_change, null);
            btnSureS = (Button) view.findViewById(R.id.btn_sure);
            btnCancelS = (Button) view.findViewById(R.id.btn_cancel);
            etSign = (EditText) view.findViewById(R.id.et_signature);
            SignatureDialog = new AlertDialog.Builder(mContext)
                    .setView(view)
                    .create();
        }
        if (!s.equals("输入你想要说的话，60个字符以内!")) {
            etSign.setText(s);
        }
        etSign.setSelection(etSign.getText().length());
        btnCancelS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDismiss();
            }
        });
        btnSureS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSignature();
            }
        });
        SignatureDialog.show();
    }

    /*修改个性签名*/
    private void changeSignature() {
        if (TextUtils.isEmpty(etSign.getText().toString())) {
            showMsg("没有填写个性签名哟~");
            return;
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("signature", etSign.getText().toString());
            TestRepository.getIns().UserUpdate(map).subscribe(new DefaultSubscriber<BaseEntity>(this, "") {
                @Override
                public void _onNext(BaseEntity entity) {
                    txtSignature.setText(etSign.getText().toString());
                    MyApplication.user.getUser().setSignature(etSign.getText().toString());
                    SPUtils.saveUser(MyApplication.user);
                    showMsg("修改个性签名成功");
                }
            });
        }
        dialogDismiss();
    }

    TimePickerView pvTime;

    /**
     * 日期管理
     */
    private void showDateSelect() {
        if (pvTime == null) {
            Calendar selectedDate = Calendar.getInstance();
            Calendar startDate = Calendar.getInstance();
            startDate.set(1900,0,1);
            Calendar endDate = Calendar.getInstance();
            endDate.set(1970,0,1);
            boolean[] type = new boolean[]{true, true, true, false, false, false};
            pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    changeBirthDay(date);

                }
            })
                    .setType(type)//默认全部显示
                    .setCancelText("取消")//取消按钮文字
                    .setSubmitText("确定")//确认按钮文字
                    .setContentSize(18)//滚轮文字大小
                    .setTitleSize(18)//标题文字大小
                    .setTitleText("日期选择")//标题文字
                    .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                    .isCyclic(false)//是否循环滚动
                    .setTitleColor(getResources().getColor(R.color.c_txt_25))//标题文字颜色
                    .setSubmitColor(getResources().getColor(R.color.main_color))//确定按钮文字颜色
                    .setCancelColor(getResources().getColor(R.color.c_txt_25))//取消按钮文字颜色
                    .setTitleBgColor(0xFFFFFFFF)//标题背景颜色 Night mode
                    .setBgColor(0xFFFFFFFF)//滚轮背景颜色 Night mode
                    .setDate(endDate)// 如果不设置的话，默认是系统时间*/
                    .setRangDate(startDate, selectedDate)//起始终止年月日设定
                    .isDialog(false)//是否显示为对话框样式
                    .gravity(Gravity.CENTER)
                    .isCenterLabel(false)
                    .build();
        }
        pvTime.show();
    }

    /**
     * 修改生日
     * @param date
     */
    private void changeBirthDay(Date date) {
        Map<String, String> map = new HashMap<>();
        map.put("birthday", date.getTime()+ "");
        TestRepository.getIns().UserUpdate(map).subscribe(new DefaultSubscriber<BaseEntity>(this, "") {
            @Override
            public void _onNext(BaseEntity entity) {
                txtBirthday.setText(DateUtils.DateToString(date.getTime()+"","yyyy-MM-dd"));
                MyApplication.user.getUser().setBirthday(date.getTime());
                SPUtils.saveUser(MyApplication.user);
                showMsg("修改生日成功");
            }
        });
    }


    /**
     * 地址管理
     */
    private void showAddressPicker() {
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
        if (dialogAddress==null){
            dialogAddress=new Dialog(mContext,R.style.mydialog);
            dialogAddress.setContentView(view);
            dialogAddress.setCancelable(true);
            dialogAddress.setCanceledOnTouchOutside(true);
            Window window = dialogAddress.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(wlp);
            window.setWindowAnimations(R.style.AnimBottom);
        }
        dialogAddress.show();
    }

    private AddressPicker mAddressPicker;

    AlertDialog nikeNameDialog;
    EditText etName;
    ImageView imgName;
    Button btnSure;
    Button btnCancel;

    private void dialogNikeName(final String nike) {
        showSoftInput();
        if (nikeNameDialog == null) {
            View view = View.inflate(mContext, R.layout.dialog_nikename_change, null);
            etName = (EditText) view.findViewById(R.id.et_name);
            imgName = (ImageView) view.findViewById(R.id.img_name);
            btnSure = (Button) view.findViewById(R.id.btn_sure);
            btnCancel = (Button) view.findViewById(R.id.btn_cancel);
            nikeNameDialog = new AlertDialog.Builder(mContext)
                    .setView(view)
                    .create();
        }
        etName.setText(nike);
        etName.setSelection(etName.getText().length());
        imgName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDismiss();
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et = etName.getText().toString();
                if (!TextUtils.isEmpty(et)) {
                    if (et.length() >= 2) {
                        showMsg("去修改昵称");
                        if (etName.getText().toString().equals(nike)) {
                            showMsg("昵称没有变动哟");
                        } else {
                            changeNikeName(et);
                        }
                        dialogDismiss();
                    } else {
                        showMsg("昵称最少两个汉字哟");
                    }
                } else {
                    showMsg("昵称不能为空");
                }
            }
        });
        nikeNameDialog.show();
    }

    /*改变姓名*/
    private void changeNikeName(final String nikeName) {
        Map<String, String> map = new HashMap<>();
        map.put("name", nikeName);
        TestRepository.getIns().UserUpdate(map).subscribe(new DefaultSubscriber<BaseEntity>(this, "") {
            @Override
            public void _onNext(BaseEntity entity) {
                txtMessageNickname.setText(nikeName);
                MyApplication.user.getUser().setName(nikeName);
                SPUtils.saveUser(MyApplication.user);
                showMsg("修改昵称成功");
            }
        });
    }

    private TextView txt_man, txt_woman, txt_cancel;
    Dialog dialog;

    private void gendDialog(final int index) {
        if (dialog == null) {
            dialog = new Dialog(mContext, R.style.mydialog);
            View rootView = View.inflate(mContext, R.layout.dialog_gender, null);
            txt_man = (TextView) rootView.findViewById(R.id.txt_man);
            txt_woman = (TextView) rootView.findViewById(R.id.txt_woman);
            txt_cancel = (TextView) rootView.findViewById(R.id.txt_cacel);
            txt_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogDismiss();
                }
            });
            dialog.setContentView(rootView);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(wlp);
            window.setWindowAnimations(R.style.AnimBottom);
        }
        if (index == 1) {
            txt_man.setText("男");
            txt_woman.setText("女");
        } else {
            txt_man.setText("拍照");
            txt_woman.setText("本地相册");
        }
        txt_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 1) {
                    changeSex("男");
                } else {
                    PhotoNum = 1;
                    if (EasyPermissions.hasPermissions(mContext, mPermissionList)) {
                        photo(PhotoNum);
                    } else {
                        EasyPermissions.requestPermissions(mContext, "需要开启必要的权限", 123, mPermissionList);
                    }
                }
                dialogDismiss();
            }
        });
        txt_woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 1) {
                    changeSex("女");
                } else {
                    PhotoNum = 2;
                    if (EasyPermissions.hasPermissions(mContext, mPermissionList)) {
                        photo(PhotoNum);
                    } else {
                        EasyPermissions.requestPermissions(mContext, "需要开启必要的权限", 123, mPermissionList);
                    }
                }
                dialogDismiss();
            }
        });
        dialog.show();
    }

    int PhotoNum = 1;

    /**
     * 拍照和相册
     *
     * @param photoNum 1代表拍照 2代表相册
     */
    private void photo(int photoNum) {
        if (photoNum == 1) {
            ImagePicker.getInstance().setSelectLimit(1);
            Intent intent = new Intent(mContext, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else {
            //打开选择,本次允许选择的数量
            ImagePicker.getInstance().setSelectLimit(1);
            Intent intent1 = new Intent(mContext, ImageGridActivity.class);
            startActivityForResult(intent1, REQUEST_CODE_SELECT);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.size() > 0) {
                    File file =  new File(images.get(0).path);
                    upImage(file);
                }
            }
        }
    }

    private void upImage(File file) {
        TestRepository.getIns().upload("avatar",file).subscribe(new DefaultSubscriber<BaseEntity>(this) {
            @Override
            public void _onNext(BaseEntity entity) {
                changeAvatar(entity.data.toString());
                 }
        });
    }

    /**
     * 修改用户头像
     * @param data
     */
    private void changeAvatar(String data) {
        Map<String, String> map=new HashMap<>();
        map.put("avatar",data);
        TestRepository.getIns().UserUpdate(map).subscribe(new DefaultSubscriber<BaseEntity>(this,"") {
            @Override
            public void _onNext(BaseEntity entity) {
                MyApplication.user.getUser().setAvatar(data);
                SPUtils.saveUser(MyApplication.user);
                showMsg("修改头像成功");
                Glide.with(mContext).load(data).asBitmap().error(R.mipmap.mine_icon_headportrait).into(imgMessageHeader);
            }
        });
    }

    /**
     * 图片选择器
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setMultiMode(false);
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(800);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(800);                         //保存文件的高度。单位像素
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        photo(PhotoNum);
    }

    /*改变性别*/
    private void changeSex(final String sex) {
        if (txtMessageGender.getText().toString().equals(sex)) {
            showMsg("当前没有改变性别哟");
        } else {
            Map<String, String> map = new HashMap<>();
            if (sex.equals("男")) {
                map.put("sex", "1");
            } else {
                map.put("sex", "2");
            }
            TestRepository.getIns().UserUpdate(map).subscribe(new DefaultSubscriber<BaseEntity>(this, "") {
                @Override
                public void _onNext(BaseEntity entity) {
                    txtMessageGender.setText(sex);
                    MyApplication.user.getUser().setSex(sex);
                    SPUtils.saveUser(MyApplication.user);
                    showMsg("修改性别成功");
                }
            });
        }
    }

    private void dialogDismiss() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        if (nikeNameDialog != null) {
            nikeNameDialog.dismiss();
            nikeNameDialog = null;
        }
        if (SignatureDialog != null) {
            SignatureDialog.dismiss();
            SignatureDialog = null;
        }
        if (dialogAddress != null) {
            dialogAddress.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialogDismiss();
    }

    @Override
    public void onAddressPickerSelect(String province, String city, String district, String areaId,String provinceId) {
        try {
            if (!txtLocation.getText().equals(city)) {
                Map<String, String> map = new HashMap<>();
                map.put("city", city);
                TestRepository.getIns().UserUpdate(map).subscribe(new DefaultSubscriber<BaseEntity>(this, "") {
                    @Override
                    public void _onNext(BaseEntity entity) {
                        txtLocation.setText(city);
                        MyApplication.user.getUser().setCity(city);
                        SPUtils.saveUser(MyApplication.user);
                        showMsg("修改城市成功");
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        popWindowDismiss();
    }

    @Override
    public void onAddressPickerCancel() {
        popWindowDismiss();
    }

    public void popWindowDismiss() {
        if (dialogAddress != null) {
            dialogAddress.dismiss();
        }
    }

    class GlideImageLoader implements ImageLoader {
        @Override
        public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
            Glide.with(activity)                             //配置上下文
                    .load(Uri.fromFile(new File(path)))//设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    .placeholder(R.mipmap.ic_default)
                    .error(R.mipmap.ic_error)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .into(imageView);
        }
        @Override
        public void clearMemoryCache() {

        }
    }
}

