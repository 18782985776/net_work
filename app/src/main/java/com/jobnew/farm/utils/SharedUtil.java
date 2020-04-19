package com.jobnew.farm.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.ShareBoardConfig;

/**
 * Created by wangwenlang on 2017/6/1.
 * title:
 * note:
 */

public class SharedUtil {
    //Activity shareedActivity, UMShareListener umShareListener,String title,String stringContent,String url
   private static  ShareAction shareAction;
    private static  UMShareListener mUmShareListener=null;
    private static ShareAction getinstance(Activity aty){
      if(shareAction==null){
          shareAction=new ShareAction(aty);
      }
        return shareAction;
    }
    /**获取默认分享监听回调**/
    private static UMShareListener getmUmShareListener(Activity aty){
        if(mUmShareListener==null){
            mUmShareListener=new UMShareListener() {

                @Override
                public void onResult(SHARE_MEDIA share_media) {
                    Toast.makeText(aty,share_media.toString()+"分享成功",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                    Toast.makeText(aty,share_media.toString()+"分享失败",Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancel(SHARE_MEDIA share_media) {
                    Toast.makeText(aty,share_media.toString()+"取消分享",Toast.LENGTH_SHORT).show();
                }
            };
        }
        return mUmShareListener;
    }
    /**自定义监听器的回调**/
    public static void setShareAction(Activity aty,String  title,String stringContent,String url,UMShareListener umShareListener){
        getinstance(aty).withTitle(title)
        .withText(stringContent)
        .withTargetUrl(url)
        .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA)
        .setCallback(umShareListener).open();
    }
    /**
     * 使用默认UMShareListener**/
    public static void setShareAction(Activity aty,String title,String stringContent,String url){
//        ShareBoardConfig config=new ShareBoardConfig();
//        config.setTitleText("分享到");
//        config.setCancelButtonText("取消");
//        config.setTitleTextColor(Color.BLACK);
//        config.setShareboardBackgroundColor(Color.WHITE);
//        config.setCancelButtonBackground(Color.parseColor("#ff0000"));
        getinstance(aty).withTitle(title)
                .withText(stringContent)
                .withTargetUrl(url)
                .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA)
                .setCallback(getmUmShareListener(aty)).open();
    }
}
