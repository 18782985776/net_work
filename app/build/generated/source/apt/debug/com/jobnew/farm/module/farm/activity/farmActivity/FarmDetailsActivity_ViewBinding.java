// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity.farmActivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jobnew.farm.R;
import com.jobnew.farm.utils.StarLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FarmDetailsActivity_ViewBinding implements Unbinder {
  private FarmDetailsActivity target;

  private View view2131297218;

  private View view2131296555;

  private View view2131296803;

  private View view2131296824;

  private View view2131296807;

  private View view2131296284;

  private View view2131296347;

  private View view2131296524;

  @UiThread
  public FarmDetailsActivity_ViewBinding(FarmDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FarmDetailsActivity_ViewBinding(final FarmDetailsActivity target, View source) {
    this.target = target;

    View view;
    target.tvTitleLeft = Utils.findRequiredViewAsType(source, R.id.tv_title_left, "field 'tvTitleLeft'", TextView.class);
    target.convenientBanner = Utils.findRequiredViewAsType(source, R.id.convenientBanner, "field 'convenientBanner'", ConvenientBanner.class);
    view = Utils.findRequiredView(source, R.id.tv_showlocation, "field 'tvShowlocation' and method 'CilckReport'");
    target.tvShowlocation = Utils.castView(view, R.id.tv_showlocation, "field 'tvShowlocation'", TextView.class);
    view2131297218 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.CilckReport(p0);
      }
    });
    target.tvDistance = Utils.findRequiredViewAsType(source, R.id.tv_distance, "field 'tvDistance'", TextView.class);
    view = Utils.findRequiredView(source, R.id.img_call, "field 'imgCall' and method 'CilckReport'");
    target.imgCall = Utils.castView(view, R.id.img_call, "field 'imgCall'", ImageView.class);
    view2131296555 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.CilckReport(p0);
      }
    });
    target.tvPlantFilter = Utils.findRequiredViewAsType(source, R.id.tv_plant_filter, "field 'tvPlantFilter'", TextView.class);
    target.tvRaiseFilter = Utils.findRequiredViewAsType(source, R.id.tv_raise_filter, "field 'tvRaiseFilter'", TextView.class);
    target.tvFarmProductsFilter = Utils.findRequiredViewAsType(source, R.id.tv_farmProducts_filter, "field 'tvFarmProductsFilter'", TextView.class);
    target.tvActivityFilter = Utils.findRequiredViewAsType(source, R.id.tv_activity_filter, "field 'tvActivityFilter'", TextView.class);
    target.tvCateringFilter = Utils.findRequiredViewAsType(source, R.id.tv_catering_filter, "field 'tvCateringFilter'", TextView.class);
    target.tvStayFilter = Utils.findRequiredViewAsType(source, R.id.tv_stay_filter, "field 'tvStayFilter'", TextView.class);
    target.recycleViewFarmDetails = Utils.findRequiredViewAsType(source, R.id.recycleView_farm_details, "field 'recycleViewFarmDetails'", RecyclerView.class);
    target.indicatorPlant = Utils.findRequiredView(source, R.id.indicator_plant, "field 'indicatorPlant'");
    target.indicatorRasie = Utils.findRequiredView(source, R.id.indicator_rasie, "field 'indicatorRasie'");
    target.indicatorProduct = Utils.findRequiredView(source, R.id.indicator_product, "field 'indicatorProduct'");
    target.indicatorActivity = Utils.findRequiredView(source, R.id.indicator_activity, "field 'indicatorActivity'");
    target.indicatorCatering = Utils.findRequiredView(source, R.id.indicator_catering, "field 'indicatorCatering'");
    target.indicatorStay = Utils.findRequiredView(source, R.id.indicator_stay, "field 'indicatorStay'");
    target.tvPlantMainTag = Utils.findRequiredViewAsType(source, R.id.tv_plant_main_tag, "field 'tvPlantMainTag'", TextView.class);
    target.tvRaiseMainTag = Utils.findRequiredViewAsType(source, R.id.tv_raise_main_tag, "field 'tvRaiseMainTag'", TextView.class);
    target.tvProductMainTag = Utils.findRequiredViewAsType(source, R.id.tv_main_product_tag, "field 'tvProductMainTag'", TextView.class);
    target.tvCateringMainTag = Utils.findRequiredViewAsType(source, R.id.tv_main_catering_tag, "field 'tvCateringMainTag'", TextView.class);
    target.tvActivityMainTag = Utils.findRequiredViewAsType(source, R.id.tv_main_activity_tag, "field 'tvActivityMainTag'", TextView.class);
    target.tvHotelMainTag = Utils.findRequiredViewAsType(source, R.id.tv_main_hotel_tag, "field 'tvHotelMainTag'", TextView.class);
    target.otherFilter = Utils.findRequiredViewAsType(source, R.id.other_filter, "field 'otherFilter'", TextView.class);
    target.tvOtherMainTag = Utils.findRequiredViewAsType(source, R.id.tv_other_main_tag, "field 'tvOtherMainTag'", TextView.class);
    target.farmStar = Utils.findRequiredViewAsType(source, R.id.farm_star, "field 'farmStar'", StarLinearLayout.class);
    view = Utils.findRequiredView(source, R.id.plant_rl, "field 'plantRl' and method 'CilckReport'");
    target.plantRl = Utils.castView(view, R.id.plant_rl, "field 'plantRl'", RelativeLayout.class);
    view2131296803 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.CilckReport(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rasie_rl, "field 'rasieRl' and method 'CilckReport'");
    target.rasieRl = Utils.castView(view, R.id.rasie_rl, "field 'rasieRl'", RelativeLayout.class);
    view2131296824 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.CilckReport(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.product_rl, "field 'productRl' and method 'CilckReport'");
    target.productRl = Utils.castView(view, R.id.product_rl, "field 'productRl'", RelativeLayout.class);
    view2131296807 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.CilckReport(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.activity_rl, "field 'activityRl' and method 'CilckReport'");
    target.activityRl = Utils.castView(view, R.id.activity_rl, "field 'activityRl'", RelativeLayout.class);
    view2131296284 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.CilckReport(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cateing_rl, "field 'cateingRl' and method 'CilckReport'");
    target.cateingRl = Utils.castView(view, R.id.cateing_rl, "field 'cateingRl'", RelativeLayout.class);
    view2131296347 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.CilckReport(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.hotel_rl, "field 'hotelRl' and method 'CilckReport'");
    target.hotelRl = Utils.castView(view, R.id.hotel_rl, "field 'hotelRl'", RelativeLayout.class);
    view2131296524 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.CilckReport(p0);
      }
    });
    target.motherRl = Utils.findRequiredViewAsType(source, R.id.other_rl, "field 'motherRl'", RelativeLayout.class);
    target.otherStayLine = Utils.findRequiredView(source, R.id.other_stay_line, "field 'otherStayLine'");
  }

  @Override
  @CallSuper
  public void unbind() {
    FarmDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitleLeft = null;
    target.convenientBanner = null;
    target.tvShowlocation = null;
    target.tvDistance = null;
    target.imgCall = null;
    target.tvPlantFilter = null;
    target.tvRaiseFilter = null;
    target.tvFarmProductsFilter = null;
    target.tvActivityFilter = null;
    target.tvCateringFilter = null;
    target.tvStayFilter = null;
    target.recycleViewFarmDetails = null;
    target.indicatorPlant = null;
    target.indicatorRasie = null;
    target.indicatorProduct = null;
    target.indicatorActivity = null;
    target.indicatorCatering = null;
    target.indicatorStay = null;
    target.tvPlantMainTag = null;
    target.tvRaiseMainTag = null;
    target.tvProductMainTag = null;
    target.tvCateringMainTag = null;
    target.tvActivityMainTag = null;
    target.tvHotelMainTag = null;
    target.otherFilter = null;
    target.tvOtherMainTag = null;
    target.farmStar = null;
    target.plantRl = null;
    target.rasieRl = null;
    target.productRl = null;
    target.activityRl = null;
    target.cateingRl = null;
    target.hotelRl = null;
    target.motherRl = null;
    target.otherStayLine = null;

    view2131297218.setOnClickListener(null);
    view2131297218 = null;
    view2131296555.setOnClickListener(null);
    view2131296555 = null;
    view2131296803.setOnClickListener(null);
    view2131296803 = null;
    view2131296824.setOnClickListener(null);
    view2131296824 = null;
    view2131296807.setOnClickListener(null);
    view2131296807 = null;
    view2131296284.setOnClickListener(null);
    view2131296284 = null;
    view2131296347.setOnClickListener(null);
    view2131296347 = null;
    view2131296524.setOnClickListener(null);
    view2131296524 = null;
  }
}
