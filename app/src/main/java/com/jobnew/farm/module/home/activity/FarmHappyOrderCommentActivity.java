package com.jobnew.farm.module.home.activity;

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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.personal.adapter.EvaluationAdapter;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.ImgCompresUtil;
import com.jobnew.farm.utils.StarLinearLayout;
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
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by wc on 2017/6/20.
 * Function：订单评价
 * desc：
 */

public class FarmHappyOrderCommentActivity extends BaseActivity {
    @BindView(R.id.star_score)
    StarLinearLayout starScore;
    @BindView(R.id.feed_gridview)
    GridView gridView;
    String[] mPermissionList = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    int PhotoNum = 1;
    @BindView(R.id.et_content)
    EditText etContent;
    private int imgUnm=0;
    private List<String> dataImg;
    int orderItemId;
    int orderId;
    @Override
    protected int getLayout() {
        return R.layout.activity_order_evaluate;
    }
    int score = 4;
    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("评价", true);
        Intent intent = getIntent();
        orderId=intent.getIntExtra("orderId",-1);
        orderItemId= intent.getIntExtra("orderItemId",-1);
        starScore.setChangeListener(new StarLinearLayout.ChangeListener() {
            @Override
            public void Change(int level) {
                score = level;
            }
        });
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
        imagePicker.setSelectLimit(3);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(400);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(800);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);                         //保存文件的高度。单位像素
    }

    private void initGridView() {
        pathData = new ArrayList<>();
        pathData.add("addpic");
        gridAdapter = new EvaluationAdapter(pathData, mContext, 3);
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
        if (gridAdapter.getItemCount() == 3) {
            showMsg("最多选择3张照片");
            return;
        }
        if (photoNum == 1) {
            ImagePicker.getInstance().setSelectLimit(3 - gridAdapter.getItemCount());
            Intent intent = new Intent(mContext, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else {
            //打开选择,本次允许选择的数量
            ImagePicker.getInstance().setSelectLimit(3 - gridAdapter.getItemCount());
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

    @OnClick(R.id.submit_eva)
    public void onViewClicked() {
        List<String> listPaths = gridAdapter.getListPaths();
        checkData();
       // showMsg("----提交评分"+score+"----提交照片张数"+listPaths.size());

    }
    private class GlideImageLoader implements ImageLoader {
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
    private void checkData() {
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

    /**
     * 上传图片
     * @param file
     */
    private void upImage(File file) {
        imgUnm++;
        TestRepository.getIns().upload("photo",file).subscribe(new DefaultSubscriber<BaseEntity>(this,"上传图片...") {
            @Override
            public void _onNext(BaseEntity entity) {
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
        map.put("orderId",orderId+"");
        map.put("orderItemId",orderItemId+"");
        map.put("score",score+"");
        map.put("content",etContent.getText().toString());
        if (gridAdapter.getListPaths().size()==0){
            map.put("images","[]");
        }else{
            String s = new Gson().toJson(dataImg);
            map.put("images",s.toString());
        }
        TestRepository.getIns().addEvaluate(map).subscribe(new DefaultSubscriber<BaseEntity>(this,"提交中...") {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("评论成功");
                finish();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                showMsg(msg);
                super._onError(e, msg);
            }
        });
    }
}
