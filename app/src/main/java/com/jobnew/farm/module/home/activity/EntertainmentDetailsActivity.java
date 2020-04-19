package com.jobnew.farm.module.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.FarmHappy.ProductEntity;
import com.jobnew.farm.entity.HomeEntertainment.HomeEntertainmentDetailsEntity;
import com.jobnew.farm.entity.NoteEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.plan.FarmHappyOrderItemEntity;
import com.jobnew.farm.module.farm.activity.ScanPictureActivity;
import com.jobnew.farm.module.home.adapter.EntertainMentHolderView;
import com.jobnew.farm.module.home.adapter.EntertainmentDetailsAdapter;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.RundImageView;
import com.jobnew.farm.widget.TitleBarHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class EntertainmentDetailsActivity extends BaseActivity {

    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.entertainment_nametv)
    TextView entertainmentNametv;
    @BindView(R.id.p)
    TextView p;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.date_tv)
    TextView dateTv;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.head_img)
    RundImageView headImg;
    @BindView(R.id.conversation_img)
    ImageView conversationImg;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.tv_farm_name)
    TextView farmNameTv;
    @BindView(R.id.phone_img)
    ImageView phoneImg;
    @BindView(R.id.tv_activity_intro)
    TextView activityIntroTv;
    @BindView(R.id.img_add_message)
    ImageView addMessageImg;
    @BindView(R.id.tv_leave_message_num)
    TextView tvLeaveMessageNum;
    @BindView(R.id.bt_join)
    Button joinBt;
    int pageSize=20;
    int pageNo=1;
    boolean isDataOver=false;
    boolean isLoading=false;
    int lastVisibleItemPosition;

    EntertainmentDetailsAdapter adapter;
    SimpleDateFormat simpleDateFormat;
    int id;//传进来的活动id
    ArrayList<NoteEntity.ListBean> arrayList;//评论数据源
    String key="activity_farm";//活动订单
    int mfarmId;//农场id
    int productId;//活动id
    int buyNum=1;//人数
    String farmImg;//农场图片
    String farmName;//农场名字
    double produtcPrice;//产品价格
    String productImg;//产品图片
    String productName;//产品名称
    String pruductIntro;//产品介绍
    long soldOutDate;//活动结束时间
    @Override
    protected int getLayout() {
        return R.layout.activity_entertainment_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Intent jumpIntent= getIntent();
        id = jumpIntent.getIntExtra("id", 0);
        arrayList = new ArrayList<>();
        adapter=new EntertainmentDetailsAdapter(arrayList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(adapter);
        recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(adapter.getItemCount()-1==lastVisibleItemPosition&&!isLoading
                        &&newState==RecyclerView.SCROLL_STATE_IDLE&&!isDataOver){
                    initMessage();
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
        initData();
        initMessage();
    }
    /***查询活动留言****/
    private void initMessage() {
        HashMap<String,String> map=new HashMap<>();
        map.put("productId",id+"");
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        isLoading=true;
        MyFarmRepository.getIns().queryActivityMessage(map).subscribe(new DefaultSubscriber<BaseEntity<NoteEntity>>(this,false) {
            @Override
            public void _onNext(BaseEntity<NoteEntity> entity) {
                NoteEntity data = entity.data;
                int reviewCount = data.getReviewCount();
                tvLeaveMessageNum.setText("("+reviewCount+")");
                List<NoteEntity.ListBean> list = data.getList();
                if(pageNo==1){
                    arrayList.clear();
                    arrayList.addAll(list);
                }else {
                    arrayList.addAll(list);
                }
                if(list.size()<20){
                    isDataOver = true;
                }else {
                    pageNo++;
                }
                adapter.notifyDataSetChanged();
                isLoading=false;
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });

    }
    /***初始化除留言以外的数据**/
    private void initData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("longitude", Constant.LONGITUDE+"");
        map.put("latitude",Constant.LATITUDE+"");

        MyFarmRepository.getIns().queryActivityDetials(id+"",map).
                subscribe(new DefaultSubscriber<BaseEntity<HomeEntertainmentDetailsEntity>>(this,false) {
                    @Override
                    public void _onNext(BaseEntity<HomeEntertainmentDetailsEntity> entity) {
                        HomeEntertainmentDetailsEntity data = entity.data;
                        setViewData(data);
                        mfarmId = data.getFarm().getId();
                        productId = data.getId();
                        farmImg=data.getFarm().getImg();
                        farmName=data.getFarm().getName();
                        produtcPrice=data.getPrice();
                        productImg=data.getPImg();
                        productName=data.getName();
                        pruductIntro=data.getIntro();
                        soldOutDate=data.getSoldOutDate();
                    }

                    @Override
                    public void _onError(Throwable e, String msg) {
                        super._onError(e, msg);
                        error(msg);
                    }
                });

    }
    /***根据请求到的活动详情数据设置视图***/
    private void setViewData(HomeEntertainmentDetailsEntity data) {
        initBanner((List<ProductEntity.ProductImagesBean>) data.getProductImages());
        data.getProductImages();
        entertainmentNametv.setText(data.getName());
        numTv.setText(data.getMaxStock()-data.getStock()+"/"+data.getMaxStock());
        dateTv.setText("日期："+simpleDateFormat.format(new Date(data.getSaleDate()))+"至"+simpleDateFormat.format(new Date(data.getSoldOutDate())));
        addressTv.setText(data.getFarm().getAddress());
        textView4.setText("距离"+(int)data.getDistance()/1000+"km");
        priceTv.setText(data.getPrice()+"/"+data.getUnitName());
        farmNameTv.setText(data.getFarm().getName());
        GlideManager.loadImg(data.getFarm().getImg(),headImg);
        conversationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showMsg("聊天");
            }
        });
        phoneImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsg(data.getFarm().getPhone());
            }
        });
        activityIntroTv.setText(data.getIntro());
        addMessageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsg("添加留言");
                showAddMessageDialog();
            }
        });
        joinBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsg("报名");
                Intent orderIntent=new Intent(EntertainmentDetailsActivity.this,EntertainmentCofirmOrderActivity.class);
                orderIntent.putExtra("mfarmId",mfarmId);
                orderIntent.putExtra("key",key);
                orderIntent.putExtra("productId",productId);
                orderIntent.putExtra("buyNum",buyNum);
                orderIntent.putExtra("farmImg",farmImg);
                orderIntent.putExtra("farmName",farmName);
                orderIntent.putExtra("produtcPrice",produtcPrice);
                orderIntent.putExtra("productImg",productImg);
                orderIntent.putExtra("productName",productName);
                orderIntent.putExtra("pruductIntro",pruductIntro);
                orderIntent.putExtra("soldOutDate",soldOutDate);
                 startActivity(orderIntent);
                finish();
            }
        });
    }



    /****初始化轮播图***/
    private void initBanner(List<ProductEntity.ProductImagesBean> bannerimgs) {
        ArrayList<String> list=new ArrayList<>();
        if(bannerimgs.isEmpty()){
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495706920225&di=9484e62b44d7d79cfa6fdde9d76f6784&imgtype=0&src=http%3A%2F%2Fpic6.wed114.cn%2F20150728%2F2015072814481569663791.jpg");
        }else {
            list.clear();
            for (int i=0;i<bannerimgs.size();i++){
                list.add(i,bannerimgs.get(i).getImgUrl());
            }
        }

        banner.setPages(new CBViewHolderCreator<EntertainMentHolderView>() {
            @Override
            public EntertainMentHolderView createHolder() {
                return new EntertainMentHolderView();
            }
        }, list);
        banner.startTurning(5000);    //设置自动切换（同时设置了切换时间间隔）
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
        titleBar.setTitleMainText("活动详情");
        titleBar.getLeftTextView().setVisibility(View.VISIBLE);
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        titleBar.getRightTextView().setText("发起");
//        titleBar.getRightTextView().setVisibility(View.VISIBLE);
//        titleBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppManager.jump(LaunchedActivity.class);
//            }
//        });
    }

    Button msurebt;
    Button mcanclebtn;
    EditText maddEditMessage;
    public void showAddMessageDialog(){
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_add_message, null);
        msurebt= (Button) inflate.findViewById(R.id.btn_sure);
        mcanclebtn= (Button) inflate.findViewById(R.id.btn_cancel);
        maddEditMessage= (EditText) inflate.findViewById(R.id.et_name);
        ;
        AlertDialog dialog = new AlertDialog.Builder(this).setView(inflate).create();
        msurebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMessageNet(maddEditMessage.getText().toString());
                dialog.dismiss();
            }
        });
        mcanclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    /***添加留言***/
    private void addMessageNet(String s) {
        HashMap<String,String> map=new HashMap<>();
        map.put("productId",id+"");
        map.put("content",s);
        ArrayList<String> messageList = new ArrayList<>();
        String s1 = new Gson().toJson(messageList);
        map.put("imgList",s1);
        MyFarmRepository.getIns().addMessage(map).subscribe(new DefaultSubscriber<BaseEntity>(this,false) {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("提交成功");
                pageNo=1;//刷新留言
                initMessage();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                showMsg("提交失败");
            }
        });

    }
}
