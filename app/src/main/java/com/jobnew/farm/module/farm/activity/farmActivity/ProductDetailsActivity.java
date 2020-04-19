package com.jobnew.farm.module.farm.activity.farmActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.FarmHappy.ProductEntity;
import com.jobnew.farm.entity.HomeEntertainment.HomeEntertainmentDetailsEntity;
import com.jobnew.farm.entity.NoteEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.farm.activity.ScanPictureActivity;
import com.jobnew.farm.module.personal.adapter.NetworkImageHolderView;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.RundImageView;
import com.jobnew.farm.widget.StatusBarView;
import com.jobnew.farm.widget.TitleBarHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProductDetailsActivity extends BaseActivity {
    Unbinder unbinder;
    @BindView(R.id.tv_title_left)
    TextView tvTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.title_bar)
    LinearLayout titleBar;
    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.unit_tv)
    TextView unitTv;
    @BindView(R.id.describe_tv)
    TextView describeTv;
    @BindView(R.id.suyuan_bt)
    Button suyuanBt;
    @BindView(R.id.reduce_img)
    ImageView reduceImg;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.increase_img)
    ImageView increaseImg;
    @BindView(R.id.postage_tv)
    TextView postageTv;
    @BindView(R.id.noPostage_tv)
    TextView noPostageTv;
    @BindView(R.id.saler_tv)
    TextView salerTv;
    @BindView(R.id.go_to_farm)
    View goToFarmTv;
    @BindView(R.id.comment_num_tv)
    TextView commentNumTv;
    @BindView(R.id.all_comment_tv)
    TextView allCommentTv;
    @BindView(R.id.rund_img1)
    ImageView rundImg1;
    @BindView(R.id.key_word1)
    TextView keyWord1;
    @BindView(R.id.star_view1)
    StatusBarView starView1;
    @BindView(R.id.date_tv1)
    TextView dateTv1;
    @BindView(R.id.describe_tv1)
    TextView describeTv1;
    @BindView(R.id.show_img1)
    ImageView showImg1;
    @BindView(R.id.show_img2)
    ImageView showImg2;
    @BindView(R.id.show_img3)
    ImageView showImg3;
    @BindView(R.id.picture_linear1)
    LinearLayout pictureLinear1;
    @BindView(R.id.manager_comment_back1)
    TextView managerCommentBack1;
    @BindView(R.id.item1)
    LinearLayout item1;
    @BindView(R.id.rund_img2)
    ImageView rundImg2;
    @BindView(R.id.key_word2)
    TextView keyWord2;
    @BindView(R.id.star_view2)
    StatusBarView starView2;
    @BindView(R.id.date_tv2)
    TextView dateTv2;
    @BindView(R.id.describe_tv2)
    TextView describeTv2;
    @BindView(R.id.show_img4)
    ImageView showImg4;
    @BindView(R.id.show_img5)
    ImageView showImg5;
    @BindView(R.id.show_img6)
    ImageView showImg6;
    @BindView(R.id.picture_linear2)
    LinearLayout pictureLinear2;
    @BindView(R.id.manager_comment_back2)
    TextView managerCommentBack2;
    @BindView(R.id.item2)
    LinearLayout item2;
    @BindView(R.id.more)
    View moreView;
    @BindView(R.id.tv_total_price)
    TextView totalPriceTv;
    @BindView(R.id.ll_comment)
    LinearLayout llconmment;
    int num = 1;
    int id;//产品id即是productId
    double price;
    String key = "general";
    int pageNo = 1;
    int pageSize = 20;
    @BindView(R.id.bt_add_car)
    TextView btAddCar;
    @BindView(R.id.bt_sure_buy)
    TextView btSureBuy;
    int farmId;//初始化数据的时候去获取的农场id
    HomeEntertainmentDetailsEntity homeEntermentDetailsEntity;

    @Override
    protected int getLayout() {
        return R.layout.activity_product_details;
    }

    /***搜集生成订单需要的数据，成功后跳转农产品专属生成订单界面***/
    public void makeOder() {
        Intent orderIntent = new Intent(ProductDetailsActivity.this,ProductSureOrderActivity.class);
        orderIntent.putExtra("productId",id);//产品id
        orderIntent.putExtra("quantity",num);//购买数量
        if(homeEntermentDetailsEntity.getUnitName()!=null){
            orderIntent.putExtra("unitName",homeEntermentDetailsEntity.getUnitName());//价格单位
        }
        orderIntent.putExtra("intro",homeEntermentDetailsEntity.getIntro());//产品描述
        orderIntent.putExtra("price",homeEntermentDetailsEntity.getPrice());//产品价格
        orderIntent.putExtra("farmId",homeEntermentDetailsEntity.getFarmId());//农场id
        orderIntent.putExtra("productName",homeEntermentDetailsEntity.getName());//产品名称
        orderIntent.putExtra("productImg",homeEntermentDetailsEntity.getPImg());//产品图片
        orderIntent.putExtra("farmName",homeEntermentDetailsEntity.getFarm().getName());//农场名称
        orderIntent.putExtra("farmImg",homeEntermentDetailsEntity.getFarm().getImg());//农场图片
        startActivity(orderIntent);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        initData();
        initComment();
    }

    /***初始化除评论外的数据***/
    private void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("longitude", Constant.LONGITUDE + "");
        map.put("latitude", Constant.LATITUDE + "");
        MyFarmRepository.getIns().queryActivityDetials(id + "", map)
                .subscribe(new DefaultSubscriber<BaseEntity<HomeEntertainmentDetailsEntity>>(this, false) {
                    @Override
                    public void _onNext(BaseEntity<HomeEntertainmentDetailsEntity> entity) {
                        HomeEntertainmentDetailsEntity data = entity.data;
                        homeEntermentDetailsEntity = data;
                        farmId = data.getFarm().getId();
                        setBaseView(data);
                    }

                    @Override
                    public void _onError(Throwable e, String msg) {
                        super._onError(e, msg);
                        error(msg);
                    }
                });
    }

    /****根据铲平详情接口返回数据，初始化页面***/
    private void setBaseView(HomeEntertainmentDetailsEntity data) {
        price = data.getPrice();
        nameTv.setText(data.getName());
        priceTv.setText(data.getPrice() + "");
        unitTv.setText("/" + data.getUnitName());
        numTv.setText(num + "");
        //   postageTv.setText(data.get);//邮费他们没弄好，说找产品
        salerTv.setText(data.getFarm().getName());
        // commentNumTv.setText("("+data.);
        describeTv.setText(data.getIntro());
        totalPriceTv.setText(num * price + "");
        List<ProductEntity.ProductImagesBean> productImages = data.getProductImages();
        initBanner(productImages);
    }

    /****初始化评论数据***/
    private void initComment() {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        map.put("productId", id + "");
        MyFarmRepository.getIns().queryActivityMessage(map).subscribe(new DefaultSubscriber<BaseEntity<NoteEntity>>(this, false) {
            @Override
            public void _onNext(BaseEntity<NoteEntity> entity) {
                if(entity.data.getReviewCount()!=0){
                    commentNumTv.setText("("+entity.data.getReviewCount()+")");
                }else {
                    commentNumTv.setText("("+0+")");
                }
                if(entity.data.getList().isEmpty()){
                    llconmment.setVisibility(View.GONE);
                    return;
                }else {
                    setViewByCommentData(entity.data.getList());
                }
            }
        });
    }
    /***根据返回的评论数据初始化评论视图****/
    private void setViewByCommentData( List<NoteEntity.ListBean> list) {
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        if(list.size()==1){
            item2.setVisibility(View.GONE);
            item1.setVisibility(View.VISIBLE);
            NoteEntity.ListBean listBean = list.get(0);
            GlideManager.loadRoundImg(listBean.getUser().getAvatar(),rundImg1);
            keyWord1.setText(listBean.getUser().getName());
            dateTv1.setText(sf.format(new Date(listBean.getCreateDate())));
            describeTv1.setText(listBean.getContent());
            List<String> imgList = listBean.getImgList();
            if(imgList!=null){
                if(imgList.size()!=0) {
                    if (imgList.size() == 1) {
                        GlideManager.loadImg(imgList.get(0), showImg1);
                        showImg2.setVisibility(View.GONE);
                        showImg3.setVisibility(View.GONE);
                    } else if (imgList.size() == 2) {
                        GlideManager.loadImg(imgList.get(0), showImg1);
                        GlideManager.loadImg(imgList.get(1), showImg2);
                        showImg3.setVisibility(View.GONE);
                    } else {
                        GlideManager.loadImg(imgList.get(0), showImg1);
                        GlideManager.loadImg(imgList.get(1), showImg2);
                        GlideManager.loadImg(imgList.get(2), showImg3);
                    }

                }
            }else {//没图片
                pictureLinear1.setVisibility(View.GONE);
            }
            if(listBean.getReplyMsg()!=null){
                managerCommentBack1.setText(listBean.getReplyMsg());
            }else {
                managerCommentBack1.setVisibility(View.GONE);
            }

        }else{//数据大圩等于2
            item2.setVisibility(View.VISIBLE);
            item1.setVisibility(View.VISIBLE);
            NoteEntity.ListBean listBean1 = list.get(0);
            GlideManager.loadRoundImg(listBean1.getUser().getAvatar(),rundImg1);
            keyWord1.setText(listBean1.getUser().getName());
            dateTv1.setText(sf.format(new Date(listBean1.getCreateDate())));
            describeTv1.setText(listBean1.getContent());
            List<String> imgsw = listBean1.getImgList();
            if(imgsw!=null) {
                if(imgsw.size()!=0){
                    if (imgsw.size() == 1) {
                        GlideManager.loadImg(imgsw.get(0), showImg1);
                        showImg2.setVisibility(View.GONE);
                        showImg3.setVisibility(View.GONE);
                    } else if (imgsw.size() == 2) {
                        GlideManager.loadImg(imgsw.get(0), showImg1);
                        GlideManager.loadImg(imgsw.get(1), showImg2);
                        showImg3.setVisibility(View.GONE);
                    } else {
                        GlideManager.loadImg(imgsw.get(0), showImg1);
                        GlideManager.loadImg(imgsw.get(1), showImg2);
                        GlideManager.loadImg(imgsw.get(2), showImg3);
                    }
                }else {
                    pictureLinear1.setVisibility(View.GONE);
                }
            }else {//imglist为空
                pictureLinear1.setVisibility(View.GONE);
            }
            if(listBean1.getReplyMsg()!=null){
                managerCommentBack1.setText("店主:"+listBean1.getReplyMsg());
            }else {
                managerCommentBack1.setVisibility(View.GONE);
            }
            NoteEntity.ListBean listBean2 = list.get(1);
            GlideManager.loadImg(listBean2.getUser().getAvatar(),rundImg2);
            keyWord2.setText(listBean2.getUser().getName());
            dateTv2.setText(sf.format(new Date(listBean2.getCreateDate())));
            describeTv2.setText(listBean2.getContent());
            List<String> imgsw2 = listBean2.getImgList();
            if(imgsw2!=null) {
                if(imgsw2.size()!=0){
                    if (imgsw2.size() == 1) {
                        GlideManager.loadImg(imgsw2.get(0), showImg4);
                        showImg5.setVisibility(View.GONE);
                        showImg6.setVisibility(View.GONE);
                    } else if (imgsw2.size() == 2) {
                        GlideManager.loadImg(imgsw2.get(0), showImg4);
                        GlideManager.loadImg(imgsw2.get(1), showImg5);
                        showImg6.setVisibility(View.GONE);
                    } else {
                        GlideManager.loadImg(imgsw2.get(0), showImg4);
                        GlideManager.loadImg(imgsw2.get(1), showImg5);
                        GlideManager.loadImg(imgsw2.get(2), showImg6);
                    }
                }else {
                    pictureLinear2.setVisibility(View.GONE);
                }
            }else {
                pictureLinear2.setVisibility(View.GONE);
            }
            if(listBean2.getReplyMsg()!=null){
                managerCommentBack2.setText("店主:"+listBean2.getReplyMsg());
            }else {
                managerCommentBack2.setVisibility(View.GONE);
            }
        }
    }

    private void initBanner(List<ProductEntity.ProductImagesBean> productImages) {
        ArrayList<String> list = new ArrayList<>();
        if(productImages==null){
            list.add("http://pic.58pic.com/58pic/13/87/72/73t58PICjpT_1024.jpg");
        }else {
            if(productImages.size()==0){
                list.add("http://pic.58pic.com/58pic/13/87/72/73t58PICjpT_1024.jpg");
            }else {
                list.clear();
                for (int i=0;i<productImages.size();i++){
                    list.add(i,productImages.get(i).getImgUrl());
                }
            }
        }

        banner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, list).startTurning(2000);

        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent scanIntent=new Intent();
                scanIntent.putStringArrayListExtra("pictures",list);
                AppManager.jump(ScanPictureActivity.class,scanIntent);
            }
        });
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.getLeftTextView().setVisibility(View.VISIBLE);
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleBar.setTitleMainText("商品详情");
        titleBar.getRightImageView().setImageResource(R.drawable.shop_car_selector);
        titleBar.getRightImageView().setVisibility(View.VISIBLE);
        titleBar.getRightImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsg("购物车");
            }
        });
        titleBar.setRightTextDrawable(R.drawable.commucation_selector);
        titleBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsg("聊天");
            }
        });
    }

    @OnClick({R.id.more, R.id.go_to_farm, R.id.increase_img, R.id.reduce_img, R.id.suyuan_bt, R.id.bt_sure_buy, R.id.bt_add_car})
    public void dealClick(View view) {
        switch (view.getId()) {
            case R.id.more:
                Intent commentIntent=new Intent(ProductDetailsActivity.this,ProductMoreCommentActivity.class);
                commentIntent.putExtra("productId",id);
                AppManager.jump(ProductMoreCommentActivity.class,commentIntent);
                break;
            case R.id.go_to_farm:
                Intent intent = new Intent();
                intent.putExtra("farmId", farmId+"");
                intent.putExtra("farmName", "花果山农场");
                AppManager.jump(FarmDetailsActivity.class, intent);
                break;
            case R.id.increase_img:
                num++;
                numTv.setText(num + "");
                totalPriceTv.setText(num * price + "");
                break;
            case R.id.reduce_img:
                if (num > 1) {
                    num--;
                } else {
                    showMsg("已经是最低了");
                }
                totalPriceTv.setText(num * price + "");
                numTv.setText(num + "");
                break;
            case R.id.suyuan_bt:
                Intent scrIntent=new Intent(ProductDetailsActivity.this,ProductSureOrderActivity.class);
                scrIntent.putExtra("productId",id);
                AppManager.jump(ProductGoToSource.class,scrIntent);
                break;
            case R.id.bt_sure_buy:
                try {
                    makeOder();
                }catch (NullPointerException e){
                    //  showMsg("空指针");
                }

                break;
            case R.id.bt_add_car:
                addShoppingCar();
                break;
        }
    }
    /***添加购物车***/
    private void addShoppingCar() {
        HashMap<String,String> map=new HashMap<>();
        map.put("productId",id+"");
        map.put("quantity",num+"");
        MyFarmRepository.getIns().addShoppingCar(map).subscribe(new DefaultSubscriber<BaseEntity>(this,false) {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("添加成功");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
