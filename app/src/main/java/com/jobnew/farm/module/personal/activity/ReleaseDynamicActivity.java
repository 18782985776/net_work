package com.jobnew.farm.module.personal.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.module.personal.adapter.EvaluationAdapter;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.ImgCompresUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by wc on 2017/7/12.
 * Function：发布动态
 * desc：
 */

public class ReleaseDynamicActivity extends BaseActivity {
    @BindView(R.id.feed_gridview)
    GridView gridView;
    @BindView(R.id.et_content)
    EditText etContent;
    String[] mPermissionList = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    int PhotoNum = 1;
    @Override
    protected int getLayout() {
        return R.layout.activity_release_dynamic;
    }
    private List<String> pathData;
    private EvaluationAdapter gridAdapter;
    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("发布动态", true);
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
        imagePicker.setSelectLimit(6);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(400);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(800);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);                         //保存文件的高度。单位像素
    }


    private void initGridView() {
        pathData = new ArrayList<>();
        pathData.add("addpic");
        gridAdapter = new EvaluationAdapter(pathData, mContext, 6);
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
    @OnClick({R.id.submit_eva})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit_eva:
                List<String> listPaths = gridAdapter.getListPaths();
                showMsg("----提交照片张数"+listPaths.size());
                break;
        }
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
        if (gridAdapter.getItemCount() == 6) {
            showMsg("最多选择6张照片");
            return;
        }
        if (photoNum == 1) {
            ImagePicker.getInstance().setSelectLimit(6 - gridAdapter.getItemCount());
            Intent intent = new Intent(mContext, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else {
            //打开选择,本次允许选择的数量
            ImagePicker.getInstance().setSelectLimit(6 - gridAdapter.getItemCount());
            Intent intent1 = new Intent(mContext, ImageGridActivity.class);
            startActivityForResult(intent1, REQUEST_CODE_SELECT);
        }
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
