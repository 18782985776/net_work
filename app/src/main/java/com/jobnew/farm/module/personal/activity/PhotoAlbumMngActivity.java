package com.jobnew.farm.module.personal.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.UserPhotoBean;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.personal.adapter.PhotoAlbumAdapter;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.ImgCompresUtil;
import com.jobnew.farm.widget.TitleBarHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by wc on 2017/5/27.
 * Function：相册管理
 * desc：
 */

public class PhotoAlbumMngActivity extends BaseActivity {
    private static final int REQUEST_CODE_SELECT = 100;
    @BindView(R.id.feed_gridview)
    GridView feedGridview;
    private PhotoAlbumAdapter gridAdapter;
    private List<UserPhotoBean> pathDatas;
    String[] mPermissionList = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    int PhotoNum = 1;
    @Override
    protected int getLayout() {
        return R.layout.activity_photo_album_mng;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initGridView();
        initImagePicker();
    }
    /**
     * 初始化banner数据
     */
    private void initData() {
        TestRepository.getIns().QueryUserPhoto(new HashMap<>()).subscribe(new DefaultSubscriber<BaseEntity<List<UserPhotoBean>>>(this,"",false) {
            @Override
            public void _onNext(BaseEntity<List<UserPhotoBean>> entity) {
                List<UserPhotoBean> data = entity.data;
                gridAdapter.addImagePaths(data);
            }
        });
    }

    private void initGridView() {
        pathDatas = new ArrayList<>();
        UserPhotoBean userPhotoBean = new UserPhotoBean(-1,"add",System.currentTimeMillis());
        pathDatas.add(userPhotoBean);
        gridAdapter = new PhotoAlbumAdapter(pathDatas, mContext, 9);
        feedGridview.setAdapter(gridAdapter);
        initData();
        gridAdapter.setOnAddPicListener(new PhotoAlbumAdapter.onAddPicListener() {
            @Override
            public void addPic() {
                gendDialog(2);
            }
            @Override
            public void deletePic(UserPhotoBean stringPath, int position) {
                AlertUtil.show(mContext, "确定要删除选中的照片吗?", "取消", "删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==DialogInterface.BUTTON_POSITIVE){
                            SureDeletePic(stringPath,position);
                        }
                    }
                });
            }
        });
    }

    /**
     * 确定删除1
     * @param userPhoto
     */
    private void SureDeletePic(UserPhotoBean userPhoto,int position) {
        TestRepository.getIns().DeleteUserPhoto(userPhoto.getId()+"",new HashMap<>()).subscribe(new DefaultSubscriber<BaseEntity>(this,"删除中...") {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("删除成功");
                gridAdapter.deletePosition(position);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.size() > 0) {
                    for (int i = 0; i <images.size() ; i++) {
                        String compressPath = ImgCompresUtil.getInstance(mContext).compressImage(images.get(i).path,800, 400, 380);
                        upImage(new File(compressPath));
                    }
                }
            }
        }
    }

    /**
     * 上传图片
     * @param file
     */
    private void upImage(File file) {
        TestRepository.getIns().upload("photo",file).subscribe(new DefaultSubscriber<BaseEntity>(this,"上传图片...") {
            @Override
            public void _onNext(BaseEntity entity) {
                savePhoto(entity.data.toString());
            }
        });
    }
    /**
     * 保存图片
     * @param data
     */
    private void savePhoto(String data) {
        Map<String, String> map=new HashMap<String, String>();
        map.put("url",data);
        TestRepository.getIns().SaveUserPhoto(map).subscribe(new DefaultSubscriber<BaseEntity<Integer>>(this,"",false) {
            @Override
            public void _onNext(BaseEntity<Integer> entity) {
                Integer data1 = entity.data;
                gridAdapter.addUserPhotoBean(new UserPhotoBean(data1,data,System.currentTimeMillis()));
            }
        });
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
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        photo(PhotoNum);
    }
    /**
     * 拍照和相册
     *
     * @param photoNum 1代表拍照 2代表相册
     */
    private void photo(int photoNum) {
        if (gridAdapter.getItemCount()==8){
            showMsg("最多选择8张照片");
            return;
        }
        if (photoNum == 1) {
            ImagePicker.getInstance().setSelectLimit(8-gridAdapter.getItemCount());
            Intent intent = new Intent(mContext, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else {
            //打开选择,本次允许选择的数量
            ImagePicker.getInstance().setSelectLimit(8-gridAdapter.getItemCount());
            Intent intent1 = new Intent(mContext, ImageGridActivity.class);
            startActivityForResult(intent1, REQUEST_CODE_SELECT);
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
    private boolean isDelete=false;
    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        setTitle("管理相册", true);
        titleBar.setRightText("编辑");
        titleBar.setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gridAdapter.getItemCount()!=0) {
                    gridAdapter.changeDelete();
                    isDelete = !isDelete;
                    if (isDelete) {
                        titleBar.setRightText("取消编辑");
                    } else {
                        titleBar.setRightText("编辑");
                    }
                }else{
                    showMsg("当前还没有照片，不能编辑");
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path)))
                .placeholder(R.mipmap.ic_default)
                .error(R.mipmap.ic_error)//设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }
    @Override
    public void clearMemoryCache() {

    }
}
