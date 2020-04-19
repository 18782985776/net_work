package com.jobnew.farm.module.farm.activity.farmActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.utils.FarmToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wangwenlang on 2017/6/13.
 * title:
 * note:
 */

public class ShareUtilsActivity extends Activity {
    private  ShareAction shareAction;
    private  UMShareListener mUmShareListener;
//    /**是否需要自定义监听*如果需要自定义必须传值为*/
//    private boolean  taggleUMShareListener=false;
    @BindView(R.id.img_qq)
    ImageView imgQq;
    @BindView(R.id.img_weixin)
    ImageView imgWeixin;
    @BindView(R.id.img_weibo)
    ImageView imgWeibo;
    @BindView(R.id.img_pyq)
    ImageView imgPyq;
    @BindView(R.id.img_zone)
    ImageView imgZone;
    @BindView(R.id.cancletv)
    TextView cancletv;
    Unbinder unbinder;
    /**分享的内容**/
    String stringContent;
    /**分享地址**/
    String url;
    Intent intent;
//    @Override
//    protected int getLayout() {
//        return R.layout.poputils_layout;
//    }
//
//    @Override
//    protected void initView(Bundle savedInstanceState) {
//
//    }
public void showMsg(String msg){
    FarmToastUtils.show(msg);
}

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poputils_layout);
        setDialogWidth(this);
        intent=getIntent();
        stringContent= intent.getStringExtra("stringContent");
        url=intent.getStringExtra("url");
        //taggleUMShareListener=getIntent().getBooleanExtra("taggleUMShareListener",false);
        mUmShareListener = new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA share_media) {
                showMsg(share_media + "分享成功");
                intent.putExtra("share_media",share_media.toString());
                setResult(1,intent);
                finish();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                showMsg(share_media + "分享失败");
                intent.putExtra("share_media",share_media.toString());
                setResult(2,intent);
                finish();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                showMsg(share_media + "取消分享");
                intent.putExtra("share_media",share_media.toString());
                setResult(3,intent);
                finish();
            }
        };
        shareAction = new ShareAction(this);
        unbinder=ButterKnife.bind(this);
    }
/**设置对话框的宽度**/
    private void setDialogWidth(ShareUtilsActivity shareUtilsActivity) {
        Window win = this.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;//设置对话框置顶显示
        win.setAttributes(lp);
    }

    @Override
    public void onBackPressed() {
        intent.putExtra("share_media","取消分享");
        setResult(3,intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    @OnClick({R.id.img_qq,R.id.img_weixin,R.id.img_weibo,R.id.img_pyq,R.id.img_zone,R.id.cancletv,R.id.view})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.img_qq:
                    shareAction.withText(stringContent).withTargetUrl(url).setPlatform(SHARE_MEDIA.QQ).setCallback(mUmShareListener).share();
                break;
            case R.id.img_weixin:
                shareAction.withText(stringContent).withTargetUrl(url).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(mUmShareListener).share();
                break;
            case R.id.img_weibo:
                shareAction.withText(stringContent).withTargetUrl(url).setPlatform(SHARE_MEDIA.SINA).setCallback(mUmShareListener).share();
                break;
            case R.id.img_pyq:
                shareAction.withText(stringContent).withTargetUrl(url).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(mUmShareListener).share();
                break;
            case R.id.img_zone:
                shareAction.withText(stringContent).withTargetUrl(url).setPlatform(SHARE_MEDIA.QZONE).setCallback(mUmShareListener).share();
                break;
            case R.id.cancletv:
            case R.id.view:
                intent.putExtra("share_media","取消分享");
                setResult(3,intent);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
