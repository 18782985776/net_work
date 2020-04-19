package com.jobnew.farm.module.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.AllBusniessEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.base.FarmEntity;
import com.jobnew.farm.module.loginAndRegister.activity.LoginActivity;
import com.jobnew.farm.module.personal.adapter.CollectionHappyFarmAdapter;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;
import com.marno.easystatelibrary.EasyStatusView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class FarmHappyActivity extends BaseRefreshAndLoadActivity {
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
    @BindView(R.id.tv_ranking_filter)
    TextView tvRankingFilter;
    @BindView(R.id.tv_hot_filter)
    TextView tvHotFilter;
    @BindView(R.id.tv_distance_filter)
    TextView tvDistanceFilter;
    @BindView(R.id.tv_super_filter)
    TextView tvSuperFilter;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.tv_super_cancle)
    TextView tvSuperCancle;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.tv_fishing)
    TextView tvFishing;
    @BindView(R.id.img3)
    ImageView img3;
    @BindView(R.id.tv_chess_and_card)
    TextView tvChessAndCard;
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.tv_catering)
    TextView tvCatering;
    @BindView(R.id.img5)
    ImageView img5;
    @BindView(R.id.tv_hotel)
    TextView tvHotel;
    @BindView(R.id.img6)
    ImageView img6;
    @BindView(R.id.businesstv6)
    TextView businesstv6;
    @BindView(R.id.img7)
    ImageView img7;
    @BindView(R.id.businesstv7)
    TextView businesstv7;
    @BindView(R.id.img8)
    ImageView img8;
    @BindView(R.id.businesstv8)
    TextView businesstv8;
    @BindView(R.id.super_filter_menue)
    HorizontalScrollView superFilterMenue;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.ptr_layout)
    PtrFrameLayout ptrLayout;
    @BindView(R.id.esv_main)
    EasyStatusView esvMain;
    @BindView(R.id.hot_img)
    ImageView hotImg;
    @BindView(R.id.distance_img)
    ImageView distanceImg;
    @BindView(R.id.super_img)
    ImageView superImg;

    CollectionHappyFarmAdapter adapter;
    ArrayList<FarmEntity> arrayList;
    /***装综合排序，人气，距离的数组**/
    ArrayList<TextView> tabTvArrays;
    /***高级筛选目录数组**/
    ArrayList<TextView> tableSuperArray;
    int TagHot = 0;//人气标识符
    int TagDistance = 0;//距离标识符
    TextView currentTv;//当前选中的主目录tv,eg:人气，距离，综合排序
    int TagSuper = 0;//高级筛选开关
    Drawable rightUpDrawable;//选中右边向上箭头
    Drawable rightDownDrawable;//选中向下箭头
    Drawable rightNoselectDrawable;//没有选中箭头
    Drawable noSuperSelectDrawable;//高级筛选没选中
    Drawable superSelectDrawable;//高级筛选选中

    /***这是装高级筛选动态选项的数组**/
    List<AllBusniessEntity.BusinessBean> business;
    ArrayList<ImageView> imgs;

    /**
     * 主营业务
     **/
    int mainBusiness = -1;

    private String province = "四川省";
    private String city = "成都市";
    private int pageNo = 1;
    private int pageSize = 20;
    /**
     * 排序排序：normal综合，position距离，grade人气
     **/
    private String sort = "normal";
    private String orderBy = "asc";///**排序规则：ase：顺序，desc：倒序*默认由近到远*/
    private String type = "agrimnt";
    /**
     * 经度
     **/
    double longitude = Constant.LONGITUDE;
    /**
     * 纬度
     **/
    double latidude = Constant.LATITUDE;
    boolean isDataOver = false;


    @Override
    protected int getLayout() {
        return R.layout.activity_farm_happy;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);
        netForMainBusniess();
        initTabTvArray();
        arrayList = new ArrayList<>();
        dealDirectory(tvRankingFilter);
        adapter.setNewData(arrayList);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int farmId = arrayList.get(position).getId();
                String farmName = arrayList.get(position).getName();
                Intent intent=new Intent();
                intent.putExtra("farmName",farmName);
                intent.putExtra("farmId",farmId);
                AppManager.jump(FarmHappyDetatilsActivity.class,intent);
            }
        });
    }


    /**
     * 动态初始化高级筛选器的容器
     **/
    private void initTabSuperArray(List<AllBusniessEntity.BusinessBean> business) {
        imgs = new ArrayList<ImageView>();
        tableSuperArray = new ArrayList<TextView>();
        tableSuperArray.add(tvSuperCancle);
        tableSuperArray.add(tvFishing);
        tableSuperArray.add(tvChessAndCard);
        tableSuperArray.add(tvCatering);
        tableSuperArray.add(tvHotel);
        tableSuperArray.add(businesstv6);
        tableSuperArray.add(businesstv7);
        tableSuperArray.add(businesstv8);
        imgs.add(img1);
        imgs.add(img2);
        imgs.add(img3);
        imgs.add(img4);
        imgs.add(img5);
        imgs.add(img6);
        imgs.add(img7);
        imgs.add(img8);
        for (int i = 0; i < business.size(); i++) {
            tableSuperArray.get(i).setText(business.get(i).getName());
            GlideManager.loadImg(business.get(i).getIcon(), imgs.get(i));
        }
        for (int i = business.size(); i < tableSuperArray.size(); i++) {
            LinearLayout ll = (LinearLayout) tableSuperArray.get(i).getParent();
            ll.setVisibility(View.GONE);
        }
    }

    /**
     * 取高级筛选下目录选项，并让菜单开始的时候是隐藏状态
     **/
    private void netForMainBusniess() {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        MyFarmRepository.getIns().getAllBusinessType(map).subscribe(new DefaultSubscriber<AllBusniessEntity>(this, false) {
            @Override
            public void _onNext(AllBusniessEntity entity) {
                if (entity.getBusiness() == null) {
                    empty();
                    return;
                }
                business = entity.getBusiness();
                initTabSuperArray(business);
                superFilterMenue.setVisibility(View.GONE);//然菜单开始的时候是隐藏状态
                fristLoadData();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }

    @Override
    protected void loadData() {
        super.loadData();

    }

    /**
     * 初始化装主菜单的数组
     **/
    private void initTabTvArray() {
        tabTvArrays = new ArrayList<TextView>();
        tabTvArrays.add(tvRankingFilter);
        tabTvArrays.add(tvHotFilter);
        tabTvArrays.add(tvDistanceFilter);
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.setTitleMainText("农家乐");
        titleBar.getLeftTextView().setVisibility(View.VISIBLE);
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleBar.getRightImageView().setVisibility(View.VISIBLE);
        titleBar.getRightImageView().setImageResource(R.mipmap.public_icon_search1);
        titleBar.getRightImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FarmToastUtils.show("搜索");
            }
        });
        titleBar.setRightTextDrawable(R.mipmap.people, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyApplication.isLogin()){
                    AppManager.jump(MyFarmHappyOrderActivity.class);
                }else {
                    AppManager.jump(LoginActivity.class);
                }

            }
        });
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        adapter = new CollectionHappyFarmAdapter(R.layout.item_collection_farmhouse, arrayList);
        return adapter;
    }

    /***处理选中目录颜色控制**/
    public void dealDirectory(TextView tView) {
        for (TextView tv : tabTvArrays) {
            if (tv == tView) {
                tv.setTextColor(Color.parseColor("#90b659"));
            } else {
                tv.setTextColor(Color.parseColor("#7b7b7b"));
            }
        }
        tvHotFilter.setCompoundDrawablePadding(10);
        tvSuperFilter.setCompoundDrawablePadding(10);
        tvDistanceFilter.setCompoundDrawablePadding(10);
        tvDistanceFilter.setCompoundDrawables(null, null, rightNoselectDrawable, null);
        tvHotFilter.setCompoundDrawables(null, null, rightNoselectDrawable, null);
    }


    @OnClick({R.id.tv_ranking_filter, R.id.tv_hot_filter, R.id.tv_distance_filter, R.id.tv_super_filter, R.id.tv_super_cancle, R.id.tv_fishing,
            R.id.tv_chess_and_card, R.id.tv_catering, R.id.tv_hotel, R.id.businesstv6, R.id.businesstv7, R.id.businesstv8})
    public void ControlData(TextView view) {
        switch (view.getId()) {
            case R.id.tv_ranking_filter:
                if (currentTv == view) {
                    return;
                }
                sort = "normal";
                dealDirectory(view);
                currentTv = view;
                fristLoadData();
                break;
            case R.id.tv_hot_filter:
                TagHot++;
                sort = "grade";
                dealDirectory(view);
                currentTv = view;
                distanceImg.setVisibility(View.GONE);
                hotImg.setVisibility(View.VISIBLE);
                if (TagHot % 2 == 0) {
                    orderBy = "asc";
                    hotImg.setImageResource(R.drawable.farm_icon_screen4);
                  //  tvHotFilter.setCompoundDrawables(null, null, rightDownDrawable, null);
                } else {
                    orderBy = "desc";
                    hotImg.setImageResource(R.drawable.farm_icon_screen3);
                   // tvHotFilter.setCompoundDrawables(null, null, rightUpDrawable, null);
                }
                fristLoadData();
                break;
            case R.id.tv_distance_filter:
                dealDirectory(view);
                sort = "position";
                TagDistance++;
                currentTv = view;
                distanceImg.setVisibility(View.VISIBLE);
                hotImg.setVisibility(View.GONE);
                if (TagDistance % 2 == 0) {
                    orderBy = "asc";
                    distanceImg.setImageResource(R.drawable.farm_icon_screen4);
                   // tvDistanceFilter.setCompoundDrawables(null, null, rightDownDrawable, null);
                } else {
                    orderBy = "desc";
                    distanceImg.setImageResource(R.drawable.farm_icon_screen3);
                   // tvDistanceFilter.setCompoundDrawables(null, null, rightUpDrawable, null);
                }
                fristLoadData();
                break;
            case R.id.tv_super_filter:
                TagSuper++;
                if (TagSuper % 2 == 1) {//开高级筛选
                    view.setTextColor(Color.parseColor("#90b659"));
                    superImg.setImageResource(R.drawable.farm_icon_scree6);
                   // tvSuperFilter.setCompoundDrawables(null, null, superSelectDrawable, null);
                    superFilterMenue.setVisibility(View.VISIBLE);
                } else {//关高级筛选
                    tvSuperFilter.setTextColor(Color.parseColor("#7b7b7b"));
                    superImg.setImageResource(R.drawable.farm_icon_screen2);
                  //  tvSuperFilter.setCompoundDrawables(null, null, noSuperSelectDrawable, null);
                    superFilterMenue.setVisibility(View.GONE);
                    mainBusiness = -1;
                    fristLoadData();
                }
                break;
            case R.id.tv_super_cancle://取消高级筛选
                dealSuperDirectory(view);
                try {
                    mainBusiness = business.get(0).getId();
                }catch (NullPointerException e){
                  //  showMsg("空指针");
                }
                fristLoadData();
                break;
            case R.id.tv_fishing://钓鱼选项
                dealSuperDirectory(view);
                try {
                    mainBusiness = business.get(1).getId();
                }catch (NullPointerException e){
                   // showMsg("空指针");
                }
                fristLoadData();
                break;
            case R.id.tv_chess_and_card://棋牌
                dealSuperDirectory(view);
                try {
                    mainBusiness = business.get(2).getId();
                }catch (NullPointerException e){
                   // showMsg("空指针");
                }
                fristLoadData();
                break;
            case R.id.tv_catering://餐饮
                dealSuperDirectory(view);
                try {
                    mainBusiness = business.get(3).getId();
                }catch (NullPointerException e){
                  //  showMsg("空指针");
                }
                fristLoadData();
                break;
            case R.id.tv_hotel://住宿
                dealSuperDirectory(view);
                try {
                    mainBusiness = business.get(4).getId();
                }catch (NullPointerException e){
                   // showMsg("空指针");
                }
                fristLoadData();
                break;
            case R.id.businesstv6:
                dealSuperDirectory(view);
                try {
                    mainBusiness = business.get(5).getId();
                }catch (NullPointerException e){
                  //  showMsg("空指针");
                }
                fristLoadData();
                break;
            case R.id.businesstv7:
                dealSuperDirectory(view);
                try {
                    mainBusiness = business.get(6).getId();
                }catch (NullPointerException e){
                   // showMsg("空指针");
                }
                fristLoadData();
                break;
            case R.id.businesstv8:
                dealSuperDirectory(view);
                try {
                    mainBusiness = business.get(7).getId();
                }catch (NullPointerException e){
                    //showMsg("空指针");
                }
                fristLoadData();
                break;

        }
    }

    /***处理高级筛选目录颜色变化**/
    private void dealSuperDirectory(TextView view) {
        try {
            for (TextView tv : tableSuperArray) {
                if (view == tv) {
                    tv.setTextColor(Color.parseColor("#90b659"));//绿色
                } else {
                    tv.setTextColor(Color.parseColor("#7b7b7b"));
                }
            }
        }catch (NullPointerException e){
           // showMsg("空指针");
        }
    }

    @Override
    public void errorChickData() {
        super.errorChickData();
        loading();
        fristLoadData();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onLoadMoreRequested() {
        adapter.loadMoreEnd(false);
    }

    /***第一次加载数据**/
    private void fristLoadData() {
        pageNo = 1;
        loading();
        HashMap<String, String> map = new HashMap<>();
        map.put("province", province);
        map.put("city", city);
        map.put("latitude", latidude + "");
        map.put("longitude", longitude + "");
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        map.put("sort", sort);
        map.put("orderBy", orderBy);
        map.put("type", type);
        if (mainBusiness != -1) {
            map.put("mainBusiness", mainBusiness + "");
        }
        MyFarmRepository.getIns().getFarmMsg(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmEntity>>>(this, false) {
            @Override
            public void _onNext(BaseEntity<List<FarmEntity>> entity) {
                List<FarmEntity> data = entity.data;
                if (data.size() == 0) {
                    empty();
                    return;
                }
                if (arrayList != null) {
                    arrayList.clear();
                } else {
                    arrayList = new ArrayList<FarmEntity>();
                }
                arrayList.addAll(data);
                adapter.setNewData(arrayList);
                if (data.size() < 20) {
                    isDataOver = true;
                    adapter.loadMoreEnd(false);
                } else {
                    pageNo++;
                }
                content();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {//下拉刷新
        pageNo = 1;
        HashMap<String, String> map = new HashMap<>();
        map.put("province", province);
        map.put("latitude", latidude + "");
        map.put("longitude", longitude + "");
        map.put("city", city);
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        map.put("sort", sort);
        map.put("orderBy", orderBy);
        map.put("type", type);
        if (mainBusiness != -1) {
            map.put("mainBusiness", mainBusiness + "");
        }
        MyFarmRepository.getIns().getFarmMsg(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmEntity>>>(this, false) {
            @Override
            public void _onNext(BaseEntity<List<FarmEntity>> entity) {
                List<FarmEntity> data = entity.data;
                if (data.size() == 0) {
                    empty();
                    return;
                }
                if (arrayList != null) {
                    arrayList.clear();
                } else {
                    arrayList = new ArrayList<FarmEntity>();
                }
                arrayList.addAll(data);
                adapter.setNewData(arrayList);
                if (data.size() < 20) {
                    isDataOver = true;
                    adapter.loadMoreEnd(false);
                } else {
                    pageNo++;
                }
                content();
                frame.refreshComplete();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
                frame.refreshComplete();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
