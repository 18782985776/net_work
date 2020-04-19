package com.jobnew.farm.utils;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.jobnew.farm.R;
import com.jobnew.farm.widget.*;
import com.jobnew.farm.widget.StatusBarUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by wangwenlang on 2017/6/12.
 * title:
 * note:
 */

public class PopUtils {
    private static ShareAction shareAction;
    private static UMShareListener mUmShareListener=null;
    private PopupWindow popupWindow;
    private static  PopUtils instance;
    LayoutInflater inflater;
    public  static  PopUtils getInstance(){
        if(instance==null){
            instance=new PopUtils();
        }
        return instance;
    }
    private void dismissPop(){
        if(popupWindow!=null){
            popupWindow.dismiss();
        }
    }
    private UMShareListener getUMShareListener(Activity sourceActivity){
        if(mUmShareListener==null){
            mUmShareListener=new UMShareListener() {
                @Override
                public void onResult(SHARE_MEDIA share_media) {
                    Toast.makeText(sourceActivity,share_media+"分享成功",Toast.LENGTH_SHORT).show();
                    dismissPop();
                }

                @Override
                public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                    Toast.makeText(sourceActivity,share_media+"分享失败",Toast.LENGTH_SHORT).show();
                    dismissPop();
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media) {
                    Toast.makeText(sourceActivity,share_media+"取消分享",Toast.LENGTH_SHORT).show();
                    dismissPop();
                }
            };
        }
        return mUmShareListener;
    }

    public void getPopShow(Activity aty,int resourceId,String url){
        if(inflater==null){
            inflater=LayoutInflater.from(aty);
        }
        getShareAction(aty);
        View contentView= (LinearLayout) inflater.inflate(R.layout.poputils_layout,null);
        setContentListener(aty,contentView,url);
        popupWindow=new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,false);
        popupWindow.showAtLocation(aty.findViewById(resourceId), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,contentView.getWidth(),contentView.getHeight());
    }
/**获取ShareAction**/
    private ShareAction getShareAction(Activity sourceActivity) {
            shareAction=new ShareAction(sourceActivity);
        return  shareAction;
    }

    /**设置监听**/
    private void setContentListener(Activity aty ,View contentView,String url) {
        contentView.findViewById(R.id.img_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareBegin(aty,getShareAction(aty),"分享测试",url,SHARE_MEDIA.QQ);
            }
        });
        contentView.findViewById(R.id.img_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareBegin(aty,getShareAction(aty),"分享测试",url,SHARE_MEDIA.WEIXIN);
            }
        });
        contentView.findViewById(R.id.img_weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareBegin(aty,getShareAction(aty),"分享测试",url,SHARE_MEDIA.SINA);
            }
        });
        contentView.findViewById(R.id.img_pyq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareBegin(aty,getShareAction(aty),"分享测试",url,SHARE_MEDIA.WEIXIN_CIRCLE);

            }
        });
        contentView.findViewById(R.id.img_zone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareBegin(aty,getShareAction(aty),"分享测试",url,SHARE_MEDIA.QZONE);
            }
        });
        contentView.findViewById(R.id.cancletv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow.isShowing()){
                    Toast.makeText(aty,"面板取消",Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                }
            }
        });
      //  shareBegin(getShareAction(aty),"okjkj","hsdb",null, SHARE_MEDIA.QQ);
    }

    public void shareBegin(ShareAction shareAction,String stringContent,String url,UMShareListener umShareListener,SHARE_MEDIA share_plat){
        shareAction.withText(stringContent).withTargetUrl(url).setPlatform(share_plat).setCallback(umShareListener).share();
    }
    public void shareBegin(Activity aty,ShareAction shareAction,String stringContent,String url,SHARE_MEDIA share_plat){
       // aty.getActionBar()
            shareAction.withText(stringContent).withTargetUrl(url).setPlatform(share_plat).setCallback(getUMShareListener(aty)).share();
    }
}
