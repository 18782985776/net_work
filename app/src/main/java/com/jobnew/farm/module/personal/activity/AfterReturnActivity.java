package com.jobnew.farm.module.personal.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.personal.adapter.AfterReturnAdapter;
import com.jobnew.farm.module.personal.adapter.EvaluationAdapter;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.ImgCompresUtil;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.ClearEditText;
import com.jobnew.farm.widget.ExpandGridView;
import com.jobnew.farm.widget.TitleBarHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by wc on 2017/6/26.
 * Function：售后退换
 * desc：
 */

public class AfterReturnActivity extends BaseActivity {
    @BindView(R.id.tv_return_cargo)
    TextView tvReturnCargo;
    @BindView(R.id.tv_return_money)
    TextView tvReturnMoney;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.img_received)
    ImageView imgReceived;
    @BindView(R.id.img_received_no)
    ImageView imgReceivedNo;
    @BindView(R.id.feed_gridview)
    ExpandGridView gridView;
    @BindView(R.id.txt_type_title)
    TextView txtTypeTitle;
    @BindView(R.id.txt_type)
    TextView txtType;
    @BindView(R.id.txt_instructions)
    TextView txtInstructions;
    @BindView(R.id.et_instructions)
    EditText etInstructions;
    @BindView(R.id.rl_why)
    RelativeLayout rlWhy;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.et_money)
    ClearEditText etMoney;
    private int type = 1;//1 代表退货 2 代表退款
    private boolean isReceived = true; //是否已经收到货
    String[] mPermissionList = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    int PhotoNum = 1;
    private int orderItemId;
    double money;

    @Override
    protected int getLayout() {
        return R.layout.activity_after_return;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("售后退换", true);
        orderItemId = getIntent().getIntExtra("orderItemId", 0);
        money = getIntent().getDoubleExtra("money", 0);
        tvMoney.setText(money + "");
        etMoney.setText(money + "");
        tvReturnCargo.setSelected(true);
        tvReturnMoney.setSelected(false);
        initGridView();
        initImagePicker();
    }

    /**
     * 图片选择器
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setMultiMode(true);
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(8);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(400);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(800);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);                         //保存文件的高度。单位像素
    }

    private void initGridView() {
        pathData = new ArrayList<>();
        pathData.add("addpic");
        gridAdapter = new EvaluationAdapter(pathData, mContext, 8);
        gridAdapter.setOnAddPicListen(new EvaluationAdapter.onAddPicListen() {
            @Override
            public void addPic() {
                gendDialog(2);
            }

            @Override
            public void removePic(int position) {
                AlertUtil.show(mContext, "确定要删除选中的照片吗?", "取消", "删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            gridAdapter.deletePic(position);
                        }
                    }
                });
            }

            @Override
            public void largeImage(String largeImg) {

            }
        });
        gridView.setAdapter(gridAdapter);
    }

    private List<String> pathData;
    private EvaluationAdapter gridAdapter;

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
//                    changeSex("男");
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
//                    changeSex("女");
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

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        photo(PhotoNum);
    }

    private static final int REQUEST_CODE_SELECT = 100;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.size() > 0) {
                    for (int i = 0; i < images.size(); i++) {
                        String compressPath = ImgCompresUtil.getInstance(mContext).compressImage(images.get(i).path, 800, 400, 380);
                        gridAdapter.addPath(compressPath);
                    }
                }
            }
        }
    }

    /**
     * 拍照和相册
     *
     * @param photoNum 1代表拍照 2代表相册
     */
    private void photo(int photoNum) {
        if (gridAdapter.getItemCount() == 8) {
            showMsg("最多选择8张照片");
            return;
        }
        if (photoNum == 1) {
            ImagePicker.getInstance().setSelectLimit(8 - gridAdapter.getItemCount());
            Intent intent = new Intent(mContext, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else {
            //打开选择,本次允许选择的数量
            ImagePicker.getInstance().setSelectLimit(8 - gridAdapter.getItemCount());
            Intent intent1 = new Intent(mContext, ImageGridActivity.class);
            startActivityForResult(intent1, REQUEST_CODE_SELECT);
        }
    }

    private void dialogDismiss() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        if (popWindow != null) {
            popWindow.dismiss();
            popWindow = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialogDismiss();
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.setRightText("帮助");
        titleBar.setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.jump(HelpActivity.class, "categorySn", "AFTERSALE");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_return_cargo, R.id.tv_return_money, R.id.txt_submit
            , R.id.ll_received, R.id.ll_received_no, R.id.rl_why})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_return_cargo:
                changeType(1);
                break;
            case R.id.tv_return_money:
                changeType(2);
                break;
            case R.id.ll_received:
                changeReceived(true);
                break;
            case R.id.ll_received_no:
                changeReceived(false);
                break;
            case R.id.rl_why:
                showPop();
                break;
            case R.id.txt_submit:
                checkData();
                break;
        }
    }

    /**
     * 检查数据  1 代表退货  2 代表退款
     */
    private void checkData() {
        if (txtType.getText().toString().equals("请选择退货原因")||txtType.getText().toString().equals("请选择退款原因")){
            if (type==1){
                showMsg("请选择退货原因");
            }else{
                showMsg("请选择退款原因");
            }
            return;
        }
        if (TextUtils.isEmpty(etMoney.getText().toString())){
            showMsg("请填写退款金额");
            return;
        }else{
            double aDouble = Double.parseDouble(etMoney.getText().toString());
            if (money<aDouble){
                showMsg("退款金额不能大于付款金额");
                return;
            }
        }
        if (TextUtils.isEmpty(etInstructions.getText().toString())){
            if (type==1){
                showMsg("请填写退货说明");
            }else{
                showMsg("请填写退款说明");
            }
            return;
        }else{
            if (etInstructions.getText().length()<2){
                if (type==1){
                    showMsg("退货说明不少于2个字哟");
                }else{
                    showMsg("退款说明不少于2个字哟");
                }
                return;
            }
        }
        if (gridAdapter.getListPaths().size()==0){
            submit();
        }else{
            dataImg=new ArrayList<>();
            imgUnm=0;
            for (int i = 0; i <gridAdapter.getListPaths().size() ; i++) {
                String compressPath = ImgCompresUtil.getInstance(mContext).compressImage(gridAdapter.getListPaths().get(i),800, 400, 380);
                upImage(new File(compressPath));
            }
        }
    }
    private List<String> dataImg;
    private int imgUnm;
    /**
     * 上传图片
     * @param file
     */
    private void upImage(File file) {
        imgUnm++;
        TestRepository.getIns().upload("photo",file).subscribe(new DefaultSubscriber<BaseEntity>(this,"上传图片...") {
            @Override
            public void _onNext(BaseEntity entity) {
                Log.e("--------photo",entity.data.toString());
                dataImg.add(entity.data.toString());
                if (imgUnm==gridAdapter.getListPaths().size()){
                    if(dataImg.size()==gridAdapter.getListPaths().size()){
                        submit();
                    }else{
                        showMsg("上传图片失败,请重新上传");
                    }
                }
            }
        });
    }
    /**
     * 提交没有图片滴时候请求
     */
    private void submit() {
        Map<String, String> map=new HashMap<>();
        map.put("orderItemId",orderItemId+"");
        map.put("returnReason",txtType.getText().toString());
        map.put("returnInfo",etInstructions.getText().toString());
        map.put("refundAmount",etMoney.getText().toString());
        if (gridAdapter.getListPaths().size()==0){
            map.put("returnImg","[]");
        }else{
            String s = new Gson().toJson(dataImg);
            map.put("returnImg",s.toString());
        }
        if (type==1){
            map.put("type","returngoods");
        }else{
            map.put("type","refund");
        }
        if (type==2&&!isReceived){
            map.put("orderStatus","0");
        }else{
            map.put("orderStatus","1");
        }
        TestRepository.getIns().getRefund(map).subscribe(new DefaultSubscriber<BaseEntity>(this,"提交中") {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("售后申请已提交，请耐心等待售后反馈");
                finish();
            }
        });
    }
    private PopupWindow popWindow;
    private List<String> data;
    private RecyclerView recyclerView;
    private AfterReturnAdapter adapter;

    /**
     * 弹出下来选择框
     */
    private void showPop() {
        String typeString = "";
        if (type == 1) {
            typeString = "退货原因";
        } else {
            if (isReceived) {
                typeString = "退款原因_已收到货";
            } else {
                typeString = "退款原因_未收到货";
            }
        }
        data = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            data.add(typeString + "-----" + (i + 1));
//        }
        if (type==2&&!isReceived){
            data.add("空包裹") ;
            data.add("未按约定时间发货") ;
            data.add("快递/物流一直未送到") ;
            data.add("其他") ;
        }else{
            data.add("商品与描述不符") ;
            data.add("质量问题") ;
            data.add("少发/漏发") ;
            data.add("包装/商品破损") ;
            data.add("未按约定时间发货") ;
            data.add("卖家发错货") ;
            data.add("其他") ;
        }
        if (popWindow == null) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.activity_after_return_item, null);
            recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
            popWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            popWindow.setBackgroundDrawable(new BitmapDrawable());
            popWindow.setTouchable(true); // 设置popupwindow可点击
            popWindow.setOutsideTouchable(true); // 设置popupwindow外部可点击
            popWindow.setFocusable(true); // 获取焦点
        }
        adapter = new AfterReturnAdapter(R.layout.activity_after_return_item_item, data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        popWindow.showAsDropDown(rlWhy);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dialogDismiss();
                txtType.setText(adapter.getItem(position) + "");
            }
        });
    }

    /**
     * 是否已经收到货
     *
     * @param isR
     */
    private void changeReceived(boolean isR) {
        if (isReceived == isR) return;
        if (isR) {
            imgReceived.setImageResource(R.mipmap.login_icon_checked);
            imgReceivedNo.setImageResource(R.mipmap.ic_cb_nocheck);
        } else {
            imgReceivedNo.setImageResource(R.mipmap.login_icon_checked);
            imgReceived.setImageResource(R.mipmap.ic_cb_nocheck);
        }
        txtType.setText("请选择退款原因");
        isReceived = isR;
    }

    /**
     * 改变布局
     *
     * @param dex
     */
    private void changeType(int dex) {
        if (dex == type) return;
        if (dex == 1) {
            tvReturnCargo.setSelected(true);
            tvReturnMoney.setSelected(false);
            llType.setVisibility(View.GONE);
            txtTypeTitle.setText("退货原因");
            txtType.setText("请选择退货原因");
            txtInstructions.setText("退货说明");
            etInstructions.setHint("输入您的退货理由，200个字以内");
        } else {
            tvReturnCargo.setSelected(false);
            tvReturnMoney.setSelected(true);
            llType.setVisibility(View.VISIBLE);
            txtTypeTitle.setText("退款原因");
            txtType.setText("请选择退款原因");
            txtInstructions.setText("退款说明");
            etInstructions.setHint("输入您的退款理由，200个字以内");
        }
        type = dex;
    }
}
