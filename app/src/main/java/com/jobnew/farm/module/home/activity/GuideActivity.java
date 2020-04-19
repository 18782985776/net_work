package com.jobnew.farm.module.home.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jobnew.farm.MainActivity;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.downLoad.DownloadApi;
import com.jobnew.farm.data.downLoad.DownloadProgressHandler;
import com.jobnew.farm.data.downLoad.ProgressHelper;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.GuidEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.home.adapter.GuidPagerAdapter;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.AppManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.guid_pager)
    ViewPager guidPager;
    @BindView(R.id.welecome_img)
    ImageView welecomeImg;//http://pic39.nipic.com/20140317/4021224_211624207109_2.jpg
    ArrayList<RelativeLayout> imgs;
    LayoutInflater inflater;
    String downurl;
    boolean isInstall=false;
    /**是否第一次进入app**/
    boolean isFristIn;

//
//            drawableTypeRequest.centerCrop()
//            .dontAnimate()
//                .placeholder(sCommonPlaceholder)
//                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                .error(R.mipmap.ic_error)
//                .placeholder(R.mipmap.ic_default)
//                .into(iv);

    @Override
    protected int getLayout() {
        return R.layout.activity_guid;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
       // dealFristIn();
        inflater = LayoutInflater.from(this);
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        imgs=new ArrayList<>();
        initGuidImage();
    }

    Call<ResponseBody> call;//下载apk的请求


    private void downLoadPicture(List<GuidEntity.ImgsBean> imgurls,int guideVersion){//List<ImgsBean>
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://msoftdl.360.cn");
        Retrofit retrofitImg= retrofitBuilder.build();
        lodaImg(retrofitImg,imgurls,guideVersion);
    }
        /**循环获取引导图**/
    private void lodaImg(Retrofit retrofitImg,List<GuidEntity.ImgsBean> imgurls,int guideVersion) {
        for (int i=0;i<imgurls.size();i++){
            int finalI = i;
            retrofitImg.create(DownloadApi.class).retrofitDownloadImg(imgurls.get(i).getImgUrl()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    InputStream is = response.body().byteStream();
                    BufferedInputStream bis=new BufferedInputStream(is);
                    String imgFileName=Environment.getExternalStorageState()+"Guideimg"+ finalI+".png";
                    File file=new File(imgFileName);
                    if(!file.exists()){
                        file.mkdirs();
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d("引导", "onResponse: "+"下载好了+"+ file.getAbsolutePath());
                    FileOutputStream fos=null;
                    try {
                        fos = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = bis.read(buffer)) != -1&&fos!=null) {
                            fos.write(buffer, 0, len);
                            fos.flush();
                        }
                        if(fos!=null){
                            fos.close();
                        }
                        if(bis!=null){
                            bis.close();
                        }
                        if(is!=null){
                            is.close();
                        }
                        Log.d("引导", "onResponse: "+"下载图片大小+"+ file.length());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SPUtils.put("guideVersion",guideVersion);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    showMsg("下载失败了");
                }
            });
        }
    }


/***初始化引导页面**/
    private void initGuidImage() {//图片版本，apk版本，图片资源
        MyFarmRepository.getIns().getGuidInfo(new HashMap<>()).subscribe(new DefaultSubscriber<GuidEntity>(this,false) {
            @Override
            public void _onNext(GuidEntity entity) {
                for (int i=0;i<entity.getImgs().size();i++){
                    imgs.add((RelativeLayout) inflater.inflate(R.layout.layout_guide,null));
                    if(i==entity.getImgs().size()-1){
                        imgs.get(i).getChildAt(1).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            //  showMsg("开始浏览");
                                AppManager.jumpAndFinish(MainActivity.class);
                            }
                        });
                    }
                }
                int guideVersion = SPUtils.get("guideVersion", -1);
                if(guideVersion==entity.getGuideVersion()){//如果图片版本相等
                    File file=new File(Environment.getExternalStorageDirectory()+"Guideimg"+0+".png");
                    file.getAbsolutePath();
                    Log.d("引导", "_onNext: 已经存在");
                    if(file.exists()){//已经下载了图片的情况
                        for (int i=0;i<entity.getImgs().size();i++){
                            File myfile = new File(Environment.getExternalStorageDirectory(), "Guideimg"+i+".png");
                            ImageView img= (ImageView) imgs.get(i).getChildAt(0);
                            Glide.with(GuideActivity.this).load(myfile).centerCrop().placeholder(R.drawable.big_picture_loading).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.big_picture_loading).into(img);
                        }
                    }else {//没有下载图片的情况
                        for (int i=0;i<entity.getImgs().size();i++){
                            ImageView img= (ImageView) imgs.get(i).getChildAt(0);
                            Glide.with(GuideActivity.this).load(entity.getImgs().get(i).getImgUrl()).centerCrop().placeholder(R.drawable.big_picture_loading).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.big_picture_loading).into(img);
                            downLoadPicture(entity.getImgs(),entity.getGuideVersion());
                        }
                    }
                }else {//如果图片版本不相等
                    for (int i=0;i<entity.getImgs().size();i++){
                        ImageView img= (ImageView) imgs.get(i).getChildAt(0);
                        Glide.with(GuideActivity.this).load(entity.getImgs().get(i).getImgUrl()).centerCrop().placeholder(R.drawable.big_picture_loading).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.big_picture_loading).into(img);
                    }
                    SPUtils.put("guideVersion",entity.getGuideVersion());
                    //下载图片到本地
                    downLoadPicture(entity.getImgs(),entity.getGuideVersion());
                }
                GuidPagerAdapter adapter=new GuidPagerAdapter(imgs);
                guidPager.setAdapter(adapter);
            }
        });
    }
}
