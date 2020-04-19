package com.jobnew.farm;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.AllBusniessEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.base.FarmEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.farm.adapter.farmAdapter.SecondFarmAdapter;
import com.jobnew.farm.module.loginAndRegister.activity.LoginActivity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.AppManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wangcheng on 2017/5/22.
 * title：
 * note：
 */

public class FarmFragment extends BaseRefreshLoadFragment {
    @BindView(R.id.title_address_tv)
    TextView titleAddressTv;
    @BindView(R.id.tv_title)
    TextView titleTv;
    @BindView(R.id.farm_search_img)
    ImageView farmSearchImg;
    @BindView(R.id.message_img)
    ImageView messageImg;
    @BindView(R.id.tv_ranking_filter)
    TextView tvRankingFilter;
    @BindView(R.id.tv_hot_filter)
    TextView tvHotFilter;
    @BindView(R.id.tv_distance_filter)
    TextView tvDistanceFilter;
    @BindView(R.id.tv_super_filter)
    TextView tvSuperFilter;
    @BindView(R.id.super_filter_menue)
    HorizontalScrollView superFilterMenue;
    @BindView(R.id.tv_main_business_plant)
    TextView tvMainBusinessPlant;
    @BindView(R.id.tv_main_business_raise)
    TextView tvMainBusinessRaise;
    @BindView(R.id.tv_main_business_catering)
    TextView tvMainBusinessCatering;
    @BindView(R.id.tv_main_business_hotel)
    TextView tvMainBusinessHotel;
    @BindView(R.id.tv_main_business_garden)
    TextView tvMainBusinessGarden;
    @BindView(R.id.tv_main_business_product)
    TextView tvMainBusinessProduct;
    @BindView(R.id.tv_main_business_cancle)
    TextView tvMainBusinessCancle;
    @BindView(R.id.tv_main_business_other)
    TextView tvMainBusinessOther;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img3)
    ImageView img3;
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.img5)
    ImageView img5;
    @BindView(R.id.img6)
    ImageView img6;
    @BindView(R.id.img7)
    ImageView img7;
    @BindView(R.id.img8)
    ImageView img8;
    @BindView(R.id.hot_img)
    ImageView hotImg;
    @BindView(R.id.distance_img)
    ImageView distanceImg;
    @BindView(R.id.super_img)
    ImageView superImg;
    Unbinder unbinder;
    SecondFarmAdapter farmAdapter;
    ArrayList<FarmEntity> arrayList;
    /**主目录筛选器容器数组**/
    ArrayList<TextView> tobleArray;
    ArrayList<TextView> tableSuperArray;
    ArrayList<ImageView> imgs;

    public static final  int NOSELECET=0;
    public static final int HOTUP=1;
    public static final int HOTDOWN=2;
    public static final int DISTANCEUP=1;
    public  static  final  int DISTANCEDOWN=2;
    public  int hotTag;
    public int distanceTag;
    /**控制加载数据类型的标志**/
    public static final String NORMALTAG="normal";
    public static final String POSITIONUPTAG="positionUp";
    public static final String POSITIONDOWNTAG="positionDown";
    public static final String HOTUPTAG="hotUp";
    public static final String HOTDOWNTAG="hotDown";
    public static final String SUPERTAG="superTag";
    /**即将加载的标志**/
    public String forwordLoadTag;
    /**当前的加载标志**/
    public String currentLoadTag;
//    Drawable rightUpDrawable;
//    Drawable rightDownDrawable;
//    Drawable rightNoselectDrawable;
//    Drawable noSuperSelectDrawable;
//    Drawable superSelectDrawable;
    /**经度**/
    double  longitude=Constant.LONGITUDE;
    /**纬度**/
    double  latidude=Constant.LATITUDE;
    /**排序排序：normal综合，position距离，grade人气**/
    String sort="normal";
    /**页码**/
    int pageNo=1;
    /**获取条数**/
    int pageSize=20;
    /**省份**/
    String province;
    /**城市**/
    String city;
    /**排序规则：ase：顺序，desc：倒序*默认由近到远*/
    String orderBy="asc";
    /**主营业务**/
    int mainBusiness=-1;
    boolean isLoad=false;
    /***这是装高级筛选动态选项的数组**/
    List<AllBusniessEntity.BusinessBean> business;
    boolean superOpen=false;
    public static FarmFragment newInstance() {
        return new FarmFragment();
    }

    public FarmFragment() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_farm;
    }
    @Override
    protected void initView(Bundle bundle, View view) {
        unbinder = ButterKnife.bind(this.getActivity());
        setMyTitle();
        arrayList = new ArrayList<FarmEntity>();
        //   initRightDrawable();
        initTag();
        initTabArray();
        farmAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HashMap<String,Serializable> map=new HashMap<String, Serializable>();
                map.put("farmId",arrayList.get(position).getId()+"");
                map.put("farmName",arrayList.get(position).getName());
                //  map.put("phone",arrayList.get(position).getPhone());//现在这些数据通过产品分类接口返回
                // map.put("mainBusiness",arrayList.get(position).getMainBusiness());
                if(MyApplication.isLogin()){
                    AppManager.jump(FarmDetailsActivity.class,map);
                }else {
                    showMsg("请先登录");
                    AppManager.jump(LoginActivity.class);
                }

            }
        });
        solveCheckedTv(tvRankingFilter);
        forwordLoadTag=NORMALTAG;
        currentLoadTag=NORMALTAG;
        setSuperFilterListener();
        superImg.setImageResource(R.drawable.farm_icon_screen2);
        //tvSuperFilter.setCompoundDrawables(null,null,noSuperSelectDrawable,null);//避免第一次进来的时候使用布局里的图片，导致不同分辨率不同显示
    }


    /**设置高级筛选里面的监听，控制排序颜色处理和逻辑控制**/
    private void setSuperFilterListener() {
          tvMainBusinessCancle.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  solveTabSuperArray((TextView) v);
                  try {
                      mainBusiness=business.get(0).getId();
                  }catch (NullPointerException e){
                    //  showMsg(e.toString());
                  }
                  tvSuperFilter.setTextColor(Color.parseColor("#90b659"));
                  loadDataRefreshBegin();
              }
          });
          tvMainBusinessPlant.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  solveTabSuperArray((TextView) v);
                  try {
                      mainBusiness=business.get(1).getId();
                  }catch (NullPointerException e){
                     // showMsg("空指针");
                  }
                  loadDataRefreshBegin();
              }
          });
          tvMainBusinessRaise.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  solveTabSuperArray((TextView) v);
                  try {
                      mainBusiness=business.get(2).getId();
                  }catch (NullPointerException e){
                     // showMsg("空指针");
                  }
                  loadDataRefreshBegin();
              }
          });
          tvMainBusinessCatering.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  solveTabSuperArray((TextView) v);
                  try {
                      mainBusiness=business.get(3).getId();
                  }catch (NullPointerException e){
                     // showMsg("空指针");
                  }
                  loadDataRefreshBegin();
              }
          });
          tvMainBusinessHotel.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  solveTabSuperArray((TextView) v);
                  try {
                      mainBusiness=business.get(4).getId();
                  }catch (NullPointerException e){
                     // showMsg("空指针");
                  }
                  loadDataRefreshBegin();
              }
          });
          tvMainBusinessGarden.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  solveTabSuperArray((TextView) v);
                  try {
                      mainBusiness=business.get(5).getId();
                  }catch (NullPointerException e){
                   //   showMsg("空指针");
                  }
                  loadDataRefreshBegin();
              }
          });
          tvMainBusinessProduct.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  solveTabSuperArray((TextView) v);
                  try {
                      mainBusiness=business.get(6).getId();
                  }catch (NullPointerException e){
                    //  showMsg("空指针");
                  }
                  loadDataRefreshBegin();
              }
          });
          tvMainBusinessOther.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  solveTabSuperArray((TextView) v);
                  try {
                      mainBusiness=business.get(7).getId();
                  }catch (NullPointerException e){
                     // showMsg("空指针");
                  }
                  loadDataRefreshBegin();
              }
          });
    }

    /**初始化右边的图标**/
    private void initRightDrawable() {
//
//        rightUpDrawable = mContext.getResources().getDrawable(R.drawable.farm_icon_screen3);//zuo
//        rightDownDrawable = mContext.getResources().getDrawable(R.drawable.farm_icon_screen4);//zuo
//        rightNoselectDrawable = mContext.getResources().getDrawable(R.drawable.farm_icon_screen1);//zuo
//        noSuperSelectDrawable=getResources().getDrawable(R.drawable.farm_icon_screen2);//zuo
//        superSelectDrawable=getResources().getDrawable(R.drawable.farm_icon_scree6);
//        rightDownDrawable.setBounds(0,0,10,20);
//        rightUpDrawable.setBounds(0,0,10,20);
//        rightNoselectDrawable.setBounds(0,0,10,20);
//        noSuperSelectDrawable.setBounds(0,0,18,18);
//        superSelectDrawable.setBounds(0,0,18,18);
    }
    /**初次加载数据**/
    @Override
    protected void loadData() {
        super.loadData();
        loading();
        if(business!=null&&forwordLoadTag.equals(NORMALTAG)){//加这句是排除点击高级删选后，再点击距离和人气，导致数据重置
            mainBusiness=business.get(0).getId();
        }
        loadDataRefreshBegin();
    }

    /**设置人气和距离没选中**/
    private void initTag() {
        hotTag=0;
        distanceTag=0;
    }

    /**初始化tab**并且动态初始化高级筛选**/
    private void initTabArray() {
        tobleArray = new ArrayList<TextView>();
        tobleArray.add(tvRankingFilter);
        tobleArray.add(tvHotFilter);
        tobleArray.add(tvDistanceFilter);
        // tobleArray.add(tvSuperFilter);
        netForMainBusniess();
    }
    /**取高级筛选下目录选项**/
    private void netForMainBusniess() {
        HashMap<String ,String> map=new HashMap<>();
        map.put("type","farm");
        MyFarmRepository.getIns().getAllBusinessType(map).subscribe(new DefaultSubscriber<AllBusniessEntity>(this,false) {
            @Override
            public void _onNext(AllBusniessEntity entity) {
                if(entity.getBusiness()==null){
                    empty();
                    return;
                }
                business = entity.getBusiness();
                initTabSuperArray(business);
                loadData();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }


    /**动态初始化高级筛选器的容器**/
    private void initTabSuperArray(List<AllBusniessEntity.BusinessBean> business) {
        imgs=new ArrayList<ImageView>();
        tableSuperArray=new ArrayList<TextView>();
        tableSuperArray.add(tvMainBusinessCancle);
        tableSuperArray.add(tvMainBusinessPlant);
        tableSuperArray.add(tvMainBusinessRaise);
        tableSuperArray.add(tvMainBusinessCatering);
        tableSuperArray.add(tvMainBusinessHotel);
        tableSuperArray.add(tvMainBusinessGarden);
        tableSuperArray.add(tvMainBusinessProduct);
        tableSuperArray.add(tvMainBusinessOther);
        imgs.add(img1);
        imgs.add(img2);
        imgs.add(img3);
        imgs.add(img4);
        imgs.add(img5);
        imgs.add(img6);
        imgs.add(img7);
        imgs.add(img8);
        for(int i=0;i<business.size();i++){
            tableSuperArray.get(i).setText(business.get(i).getName());
            GlideManager.loadImg(business.get(i).getIcon(),imgs.get(i));
        }
        for (int i=business.size();i<tableSuperArray.size();i++){
            LinearLayout ll= (LinearLayout) tableSuperArray.get(i).getParent();
            ll.setVisibility(View.GONE);
        }
    }
    /**根据传入的TextView，设置高级筛选目录下的textView的颜色**/
    private void solveTabSuperArray(TextView tabTv) {
        if(tableSuperArray==null){
            return;
        }
        for (TextView sTv:tableSuperArray){
            if(sTv==tabTv){
                tabTv.setTextColor(Color.parseColor("#90b659"));//绿色
            }else {
                sTv.setTextColor(Color.parseColor("#7b7b7b"));//未选中
            }
        }
    }
    /**标题栏初始化监听**/
    public void setMyTitle(){
        titleAddressTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  FarmToastUtils.show("定位");
                //  AppManager.jump(PayOrderActivity.class);
            }
        });
        farmSearchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FarmToastUtils.show("消息");
//               AppManager.jump(LoveDonationActivity.class);

            }
        });
        messageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  AppManager.jump(ShippingAddressMangeAcitivty.class);
            }
        });
    }
//    @Override
//    protected void setTitleBar(TitleBarHelper titleBar) {
//        titleBar.setLeftText("成都");
//        titleBar.getLeftTextView().setTextColor(Color.WHITE);
//        titleBar.setTitleMainText("农场");
//        titleBar.getLeftTextView().setCompoundDrawablePadding(10);
//        titleBar.setLeftTextDrawable(R.mipmap.ic_select, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FarmToastUtils.show("定位");
//               // startActivity(new Intent(getActivity(), PayOrderActivity.class));
//                AppManager.jump(PayOrderActivity.class);
//            }
//        });
//        titleBar.getRightImageView().setImageResource(R.mipmap.public_icon_search1);
//
//        ViewGroup.LayoutParams layoutParams = titleBar.getRightImageView().getLayoutParams();
//
//        layoutParams.height= DensitiyUtil.dip2px(getActivity(),15);
//        layoutParams.width=DensitiyUtil.dip2px(getActivity(),15);
//        titleBar.getRightImageView().setLayoutParams(layoutParams);
//        titleBar.getRightImageView().setVisibility(View.VISIBLE);
//        titleBar.setRightTextDrawable(R.mipmap.ic_msg_normal, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FarmToastUtils.show("消息");
//               AppManager.jump(LoveDonationActivity.class);
//              //  startActivity(new Intent(getActivity(), LoveDonationActivity.class));
//            }
//        });
//        setTitleClick(titleBar);
//
//    }
///**设置标题栏点击**/
//    private void setTitleClick(TitleBarHelper titleBar) {
//        titleBar.getRightImageView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),"搜索",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(), ShippingAddressMangeAcitivty.class));
//            }
//        });
//    }

    @OnClick({R.id.tv_ranking_filter, R.id.tv_hot_filter, R.id.tv_distance_filter, R.id.tv_super_filter})
    public void startFilter(TextView view) {//排序条件
        // mainBusiness=null;
        solveCheckedTv(view);
        pageNo=1;
        switch (view.getId()) {
            case R.id.tv_ranking_filter:
                sort="normal";
                forwordLoadTag=NORMALTAG;
                if(!forwordLoadTag.equals(currentLoadTag)){//如果是当前的TAG和当前TAG一样，则重新加载
                    loadData();
                }
                break;
            case R.id.tv_hot_filter:
                sort="grade";
                hotTag++;
                hotImg.setVisibility(View.VISIBLE);
                distanceImg.setVisibility(View.GONE);
                if(hotTag%2==0){
                    orderBy="asc";//人气由近到远
                    forwordLoadTag=HOTDOWNTAG;
                    //tvHotFilter.setCompoundDrawables(null,null,rightDownDrawable,null);
                    hotImg.setImageResource(R.drawable.farm_icon_screen4);
                }else {
                    orderBy="desc";//人气由远到近
                    forwordLoadTag=HOTUPTAG;
                    hotImg.setImageResource(R.drawable.farm_icon_screen3);
                    // tvHotFilter.setCompoundDrawables(null,null,rightUpDrawable,null);
                }
                if(!forwordLoadTag.equals(currentLoadTag)){//如果是当前的TAG和当前TAG一样，则重新加载
                    loadData();
                }
                break;
            case R.id.tv_distance_filter:
                sort="position";
                distanceTag++;
                distanceImg.setVisibility(View.VISIBLE);
                hotImg.setVisibility(View.GONE);
                if(distanceTag%2==0){
                    orderBy="desc";//距离由近到远desc
                    forwordLoadTag=POSITIONDOWNTAG;
                    distanceImg.setImageResource(R.drawable.farm_icon_screen3);
                    //  tvDistanceFilter.setCompoundDrawables(null,null,rightDownDrawable,null);
                }else {
                    orderBy="asc";//距离由远到近asc
                    forwordLoadTag=POSITIONUPTAG;
                    // tvDistanceFilter.setCompoundDrawables(null,null,rightUpDrawable,null);
                    distanceImg.setImageResource(R.drawable.farm_icon_screen4);
                }

                if(!forwordLoadTag.equals(currentLoadTag)){//如果是当前的TAG和当前TAG一样，则重新加载
                    loadData();
                }
                break;
            case R.id.tv_super_filter:
                if(!superOpen){
                    superFilterMenue.setVisibility(View.VISIBLE);
                    //  forwordLoadTag=SUPERTAG;
//                if(mainBusiness){//如果没有选中主营业务，则设置Cancle按钮为绿色
//                    tvMainBusinessCancle.setTextColor(Color.parseColor("#90b659"));
//                }
                    tvSuperFilter.setTextColor(Color.parseColor("#90b659"));
                    superImg.setImageResource(R.drawable.farm_icon_scree6);
                    // tvSuperFilter.setCompoundDrawables(null,null,superSelectDrawable,null);

                    superOpen=true;
                }else {
                    superFilterMenue.setVisibility(View.GONE);
                    //  forwordLoadTag=SUPERTAG;
//                if(mainBusiness){//如果没有选中主营业务，则设置Cancle按钮为绿色
//                    tvMainBusinessCancle.setTextColor(Color.parseColor("#90b659"));
//                }
                    tvSuperFilter.setTextColor(Color.parseColor("#686868"));
                    superImg.setImageResource(R.drawable.farm_icon_screen2);
                    //  tvSuperFilter.setCompoundDrawables(null,null,noSuperSelectDrawable,null);

                    superOpen=false;
                    for(int i=0;i<tableSuperArray.size();i++){
                        tableSuperArray.get(i).setTextColor(Color.parseColor("#686868"));
                    }
                    mainBusiness=-1;
                }

                break;
        }
    }
    /**处理点击到的主目录排序控件颜色**/
    private void solveCheckedTv(TextView  sourceTv) {
        // superFilterMenue.setVisibility(View.GONE);
        tvHotFilter.setCompoundDrawablePadding(10);
        tvSuperFilter.setCompoundDrawablePadding(10);
        tvDistanceFilter.setCompoundDrawablePadding(10);
        if(sourceTv.getId()==R.id.tv_super_filter){
            return;
        }
        for(TextView myCheckedtv:tobleArray){
            if(myCheckedtv!=sourceTv){
                myCheckedtv.setTextColor(Color.parseColor("#7b7b7b"));//没选中
            }else {
                myCheckedtv.setTextColor(Color.parseColor("#90b659"));//绿色
            }
            hotImg.setImageResource(R.drawable.farm_icon_screen1);
            // tvHotFilter.setCompoundDrawables(null,null,rightNoselectDrawable,null);
            distanceImg.setImageResource(R.drawable.farm_icon_screen1);
            //tvDistanceFilter.setCompoundDrawables(null,null,rightNoselectDrawable,null);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        farmAdapter=new SecondFarmAdapter(R.layout.listview_farm_item,arrayList );
        return farmAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void errorChickData() {
        super.errorChickData();
        loadDataRefreshBegin();
    }

    @Override
    public void onLoadMoreRequested() {//加载更多,上拉
        pageNo++;
        HashMap<String ,String> map=new HashMap<>();
        map.put("longitude",Constant.LONGITUDE+"");
        map.put("latitude",Constant.LATITUDE+"");
        map.put("sort",sort);
        map.put("pageNo",pageNo+"");
        map.put("pageSize",20+"");
        map.put("orderBy",orderBy);
        map.put("province","四川省");
        if(mainBusiness!=-1){
            map.put("mainBusiness",mainBusiness+"");
        }
        MyFarmRepository.getIns().getFarmMsg(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<FarmEntity>> entity) {
                if(entity.data==null){
                    empty();
                    return;
                }
                arrayList.addAll(entity.data);
                farmAdapter.addData(entity.data);
                farmAdapter.notifyDataSetChanged();
                currentLoadTag=forwordLoadTag;
                if(entity.data.size()<pageSize){//如果获取到的数据小于20条，表示没有数据了
                    farmAdapter.loadMoreEnd(false);//boolean表示显示隐藏“没有更多数据了”
                }
                content();
            }
            @Override
            public void _onError(Throwable e, String msg) {
                //super._onError(e, msg);
                error(msg);
            }
        });
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {//下拉刷新
        loadDataRefreshBegin();
        ptrLayout.refreshComplete();
    }
    /**刚刚开始加载的时候**/
    protected void loadDataRefreshBegin() {
        pageNo=1;
        HashMap<String ,String> map=new HashMap<>();
        map.put("longitude", Constant.LONGITUDE+"");
        map.put("latitude",Constant.LATITUDE+"");
        map.put("sort",sort);
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        map.put("orderBy",orderBy);
        map.put("province","四川省");
        if(mainBusiness!=-1){
            map.put("mainBusiness",mainBusiness+"");
        }
        MyFarmRepository.getIns().getFarmMsg(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<FarmEntity>> entity) {
                if(entity.data==null|entity.data.size()==0){//主要是为了高级选项里面是空数据写的
                    empty();
                    return;
                }
                arrayList=new ArrayList<FarmEntity>();
                arrayList.addAll(entity.data);
                farmAdapter.setNewData(arrayList);
                farmAdapter.notifyDataSetChanged();
                currentLoadTag=forwordLoadTag;
                if(entity.data.size()<pageSize){//如果获取到的数据小于20条，表示没有数据了
                    farmAdapter.loadMoreEnd(false);
                }
                content();
            }
            @Override
            public void _onError(Throwable e, String msg) {
                //super._onError(e, msg);
                error(msg);
            }
        });
    }
}
