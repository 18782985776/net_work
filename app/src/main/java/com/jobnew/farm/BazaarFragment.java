package com.jobnew.farm;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.adapter.BazaarOneAdapter;
import com.jobnew.farm.adapter.BazaarThreeAdapter;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.bazaar.BazaarBig;
import com.jobnew.farm.entity.bazaar.BazaarSmall;
import com.jobnew.farm.module.farm.activity.farmActivity.ProductDetailsActivity;
import com.jobnew.farm.module.home.activity.SearchActivityForHome;
import com.jobnew.farm.module.personal.activity.KindMyShelfActivity;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wangcheng on 2017/5/22.
 * title：集市界面
 * note：
 */

public class BazaarFragment extends BaseRefreshLoadFragment {

    @BindView(R.id.ll_TopTitle)
    LinearLayout llTopTitle;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.ll_title_top3)
    LinearLayout llTitleTop3;
    Unbinder unbinder;
    @BindView(R.id.recycler_view_one)
    RecyclerView recyclerViewOne;
    BazaarOneAdapter adapterOne;
    @BindView(R.id.recycler_view_two)
    RecyclerView recyclerViewTwo;
    BazaarOneAdapter adapterTwo;
    @BindView(R.id.ll_title_top2)
    LinearLayout llTitleTop2;
    @BindView(R.id.ll_title_top2_2)
    LinearLayout llTitleTop22;
    /* @BindView(R.id.recycler_view_three)
     RecyclerView recyclerViewThree;*/
    BazaarThreeAdapter adapterThree;
    @BindView(R.id.two_5)
    TextView two5;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.txt_location)
    TextView txtLocation;
    /**
     * 大的一级的分类  0 代表蔬菜  1 代表 肉类  2 代表 水产  3 代表 瓜果 4 代表 花草
     */
    private int titleType = 0;
    /**
     * 大的二级的分类  1 代表 综合排序  2 代表 销量最高  3 代表 距离最近  4 代表 价格高低
     */
    private int twoType = 1;
    /**
     * 是否预售 true 代表预售 ，false 代表不预售
     */
    private boolean isBooking = false;
    /**
     * 二级分类里面的价格   1 代表 正常  2 代表 价格从低到高 3 代表 价格从高到底
     */
    private int twoTypePrice = 1;
    /**
     * 大分类数据，还有大分类里面的小分类数据
     */
    List<BazaarBig> dataBig;
    /**
     * 小分类数据
     */
    List<BazaarBig.ChildrenEntity> children;

    private String Fid = "";//分类id
    private String FidName = "";
    private int page = 1;//页数

    private boolean isFirstError = false;

    public static BazaarFragment newInstance() {
        return new BazaarFragment();
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_bazaar;
    }

    @Override
    protected void initView(Bundle bundle, View view) {
        setTitleBarPadding(llTopTitle);
        txtLocation.setText(Constant.CITY);
        requestOne();
    }

    /**
     * 第一次请求网络
     */
    private void requestOne() {
        TestRepository.getIns().getBazaarBig(new HashMap<>())
                .subscribe(new DefaultSubscriber<BaseEntity<List<BazaarBig>>>(this, true) {
                    @Override
                    public void _onNext(BaseEntity<List<BazaarBig>> entity) {
                        dataBig = entity.data;
                        llAll.setVisibility(View.VISIBLE);
                        isFirstError = false;
                        initRecyclerViewOne();
                    }

                    @Override
                    public void _onError(Throwable e, String msg) {
                        super._onError(e, msg);
                        llAll.setVisibility(View.GONE);
                        isFirstError = true;
                        error(msg);
                    }
                });
    }

    private void initRecyclerViewOne() {
        children = dataBig.get(titleType).getChildren();

        Fid = dataBig.get(titleType).getChildren().get(0).getId() + "";
        FidName = dataBig.get(titleType).getChildren().get(0).getName();
        adapterOne = new BazaarOneAdapter(R.layout.fragment_bazaar_item_one, children);
        LinearLayoutManager lm = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewOne.setLayoutManager(lm);
        recyclerViewOne.setAdapter(adapterOne);
        adapterOne.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapterOne.setPosition(position);
                adapterTwo.setPosition(position);
                Fid = dataBig.get(titleType).getChildren().get(position).getId() + "";
                FidName = dataBig.get(titleType).getChildren().get(position).getName();
                initData(false);
            }
        });
        adapterTwo = new BazaarOneAdapter(R.layout.fragment_bazaar_item_one, children);
        GridLayoutManager lm1 = new GridLayoutManager(mContext, 4);
        recyclerViewTwo.setLayoutManager(lm1);
        recyclerViewTwo.setAdapter(adapterTwo);
        adapterTwo.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapterOne.setPosition(position);
                adapterTwo.setPosition(position);
                llTitleTop3.setVisibility(View.VISIBLE);
                llTitleTop2.setVisibility(View.VISIBLE);
                llTitleTop22.setVisibility(View.GONE);
                Fid = dataBig.get(titleType).getChildren().get(position).getId() + "";
                FidName = dataBig.get(titleType).getChildren().get(position).getName() + "";

                initData(false);
            }
        });
        initData(false);
        adapterThree.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int item = adapterThree.getItem(position).getId();
                AppManager.jump(ProductDetailsActivity.class, "id", item);
            }
        });
    }

    List<BazaarSmall> dataAll;

    @Override
    public void errorChickData() {
        if (isFirstError) {
            requestOne();
        } else {
            initData(false);
        }
    }

    private int pageSize = 6;//加载条数

    /**
     * 加载数据
     */
    private void initData(boolean isLoad) {
        if (!isLoad) {
            page = 1;
        } else {
            page++;
        }
        Map<String, String> map = new HashMap<>();
        map.put("categoryId", Fid);
        if (twoType == 1) {//综合
            map.put("orderType", "normal");
            map.put("orderBy", "desc");
        } else if (twoType == 2) {//销量
            map.put("orderType", "sale");
            map.put("orderBy", "desc");
        } else if (twoType == 3) {
            map.put("orderType", "position");
            map.put("orderBy", "asc");
        } else if (twoType == 4) {
            map.put("orderType", "price");
            if (twoTypePrice == 3) {
                map.put("orderBy", "desc");
            } else if (twoTypePrice == 2) {
                map.put("orderBy", "asc");
            }
        }
        map.put("longitude", Constant.LONGITUDE+"");
        map.put("latitude", Constant.LATITUDE+"");
        map.put("onlyPreSale", isBooking + "");
        map.put("pageNo", page + "");
        map.put("pageSize", pageSize + "");
        TestRepository.getIns().queryBazaarSmall(map)
                .subscribe(new DefaultSubscriber<BaseEntity<List<BazaarSmall>>>(this, true) {
                    @Override
                    public void _onNext(BaseEntity<List<BazaarSmall>> entity) {
                        Log.e("----------111222", entity.data.toString());
                        List<BazaarSmall> dataSmall = entity.data;
                        if (page == 1) {
                            if (dataSmall.size() == 0) {
                                empty();
                            } else {
                                content();
                            }
                        }
                        if (!isLoad) {
                            dataAll.clear();
                        }
                        dataAll.addAll(dataSmall);
                        Log.e("-------1111", dataAll.size() + "");
                        adapterThree.loadMoreComplete();
                        ptrLayout.refreshComplete();
                        if (dataSmall.size() < pageSize || dataSmall.size() == 0) {
                            adapterThree.loadMoreEnd(false);
                        } else {
                            adapterThree.setEnableLoadMore(true);
                        }
                        adapterThree.notifyDataSetChanged();
                    }

                    @Override
                    public void _onError(Throwable e, String msg) {
                        super._onError(e, msg);
                        error(msg);
                    }
                });
    }

    @OnClick({R.id.rl_1, R.id.rl_2, R.id.rl_3, R.id.rl_4, R.id.rl_5
            , R.id.two_1, R.id.two_2, R.id.two_3, R.id.two_4, R.id.two_5
            , R.id.ll_more, R.id.ll_shrinkage, R.id.img_kind_my_shelf,R.id.rl_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_1:
                changeTitleType(0);
                break;
            case R.id.rl_2:
                changeTitleType(1);
                break;
            case R.id.rl_3:
                changeTitleType(2);
                break;
            case R.id.rl_4:
                changeTitleType(3);
                break;
            case R.id.rl_5:
                changeTitleType(4);
                break;
            case R.id.two_1:
                changeTwoType(1);
                break;
            case R.id.two_2:
                changeTwoType(2);
                break;
            case R.id.two_3:
                changeTwoType(3);
                break;
            case R.id.two_4:
                changeTwoType(4);
                break;
            case R.id.two_5:
                changeTwoType(5);
                break;
            case R.id.ll_more:
                llTitleTop3.setVisibility(View.GONE);
                llTitleTop2.setVisibility(View.GONE);
                llTitleTop22.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_shrinkage:
                llTitleTop3.setVisibility(View.VISIBLE);
                llTitleTop2.setVisibility(View.VISIBLE);
                llTitleTop22.setVisibility(View.GONE);
                break;
            case R.id.img_kind_my_shelf:
                AppManager.jump(KindMyShelfActivity.class, "name", FidName);
                break;
            case R.id.rl_search:
                AppManager.jump(SearchActivityForHome.class);
                break;
        }
    }

    /**
     * 改变二级类别
     *
     * @param dex
     */
    private void changeTwoType(int dex) {
        if (dex != 5) {
            if (dex != 4) {
                if (twoType == dex) return;
                twoTypePrice = 1;
            } else {
                if (twoTypePrice == 1) {
                    twoTypePrice = 2;
                } else if (twoTypePrice == 2) {
                    twoTypePrice = 3;
                } else if (twoTypePrice == 3) {
                    twoTypePrice = 2;
                }
            }
            View viewOne = llTitleTop3.getChildAt(dex - 1);
            if (viewOne instanceof TextView) {
                ((TextView) viewOne).setTextColor(getResources().getColor(R.color.main_color));
            } else if (viewOne instanceof RelativeLayout) {
                for (int i = 0; i < ((RelativeLayout) viewOne).getChildCount(); i++) {
                    if (((RelativeLayout) viewOne).getChildAt(i) instanceof TextView) {
                        ((TextView) ((RelativeLayout) viewOne).getChildAt(i)).setTextColor(getResources().getColor(R.color.main_color));
                    } else if (((RelativeLayout) viewOne).getChildAt(i) instanceof ImageView) {
                        if (twoTypePrice == 2) {
                            ((ImageView) ((RelativeLayout) viewOne).getChildAt(i)).setImageResource(R.drawable.farm_icon_screen3);
                        } else if (twoTypePrice == 3) {
                            ((ImageView) ((RelativeLayout) viewOne).getChildAt(i)).setImageResource(R.drawable.farm_icon_screen4);
                        } else {
                            ((ImageView) ((RelativeLayout) viewOne).getChildAt(i)).setImageResource(R.drawable.farm_icon_screen1);
                        }
                    }
                }
            }
            View viewTwo = llTitleTop3.getChildAt(twoType - 1);
            if (viewTwo instanceof TextView) {
                ((TextView) viewTwo).setTextColor(getResources().getColor(R.color.c_txt_68));
            } else if (viewTwo instanceof RelativeLayout) {
                for (int i = 0; i < ((RelativeLayout) viewTwo).getChildCount(); i++) {
                    if (((RelativeLayout) viewTwo).getChildAt(i) instanceof TextView) {
                        if (twoTypePrice == 1) {
                            ((TextView) ((RelativeLayout) viewTwo).getChildAt(i)).setTextColor(getResources().getColor(R.color.c_txt_68));
                        } else {
                            ((TextView) ((RelativeLayout) viewTwo).getChildAt(i)).setTextColor(getResources().getColor(R.color.main_color));
                        }
                    } else if (((RelativeLayout) viewTwo).getChildAt(i) instanceof ImageView) {
                        if (twoTypePrice == 2) {
                            ((ImageView) ((RelativeLayout) viewTwo).getChildAt(i)).setImageResource(R.drawable.farm_icon_screen3);
                        } else if (twoTypePrice == 3) {
                            ((ImageView) ((RelativeLayout) viewTwo).getChildAt(i)).setImageResource(R.drawable.farm_icon_screen4);
                        } else {
                            ((ImageView) ((RelativeLayout) viewTwo).getChildAt(i)).setImageResource(R.drawable.farm_icon_screen1);
                        }
                    }
                }
            }
            twoType = dex;
            isBooking = false;
            two5.setTextColor(getResources().getColor(R.color.c_txt_68));
        } else {
            isBooking = !isBooking;
            if (isBooking) {
                two5.setTextColor(getResources().getColor(R.color.main_color));
            } else {
                two5.setTextColor(getResources().getColor(R.color.c_txt_68));
            }
        }
        initData(false);
    }

    /**
     * 改变一级类别
     *
     * @param dex
     */
    private void changeTitleType(int dex) {
        if (titleType == dex) return;
        RelativeLayout rl_dex = (RelativeLayout) llTitle.getChildAt(dex);
        for (int i = 0; i < rl_dex.getChildCount(); i++) {
            if (rl_dex.getChildAt(i) instanceof TextView) {
                ((TextView) rl_dex.getChildAt(i)).setTextColor(getResources().getColor(R.color.main_color));
            } else {
                rl_dex.getChildAt(i).setVisibility(View.VISIBLE);
            }
        }
        RelativeLayout rl_type = (RelativeLayout) llTitle.getChildAt(titleType);
        for (int i = 0; i < rl_dex.getChildCount(); i++) {
            if (rl_type.getChildAt(i) instanceof TextView) {
                ((TextView) rl_type.getChildAt(i)).setTextColor(getResources().getColor(R.color.c_txt_38));
            } else {
                rl_type.getChildAt(i).setVisibility(View.INVISIBLE);
            }
        }
        titleType = dex;
        changeTwoType(1);
        initRecyclerViewOne();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        dataAll = new ArrayList<>();
        adapterThree = new BazaarThreeAdapter(R.layout.fragment_bazaar_item_three, dataAll, mContext);
        return adapterThree;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void onLoadMoreRequested() {
        initData(true);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        initData(true);
    }
}
