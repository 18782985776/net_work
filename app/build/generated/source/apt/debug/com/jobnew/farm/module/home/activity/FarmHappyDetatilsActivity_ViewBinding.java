// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class FarmHappyDetatilsActivity_ViewBinding implements Unbinder {
  private FarmHappyDetatilsActivity target;

  private View view2131296555;

  private View view2131296803;

  private View view2131296824;

  private View view2131296807;

  private View view2131296284;

  private View view2131296347;

  private View view2131296524;

  @UiThread
  public FarmHappyDetatilsActivity_ViewBinding(FarmHappyDetatilsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FarmHappyDetatilsActivity_ViewBinding(final FarmHappyDetatilsActivity target,
      View source) {
    this.target = target;

    View view;
    target.tvTitleLeft = Utils.findRequiredViewAsType(source, R.id.tv_title_left, "field 'tvTitleLeft'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvTitleRight = Utils.findRequiredViewAsType(source, R.id.tv_title_right, "field 'tvTitleRight'", TextView.class);
    target.ivTitleRight = Utils.findRequiredViewAsType(source, R.id.iv_title_right, "field 'ivTitleRight'", ImageView.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.title_bar, "field 'titleBar'", LinearLayout.class);
    target.farmHappyBanner = Utils.findRequiredViewAsType(source, R.id.farm_happy_banner, "field 'farmHappyBanner'", ConvenientBanner.class);
    target.farmHappyStar = Utils.findRequiredViewAsType(source, R.id.farm_happy_star, "field 'farmHappyStar'", StarLinearLayout.class);
    target.tvShowlocation = Utils.findRequiredViewAsType(source, R.id.tv_showlocation, "field 'tvShowlocation'", TextView.class);
    target.tvDistance = Utils.findRequiredViewAsType(source, R.id.tv_distance, "field 'tvDistance'", TextView.class);
    view = Utils.findRequiredView(source, R.id.img_call, "field 'imgCall' and method 'dealClick'");
    target.imgCall = Utils.castView(view, R.id.img_call, "field 'imgCall'", ImageView.class);
    view2131296555 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.tvCatingFilter = Utils.findRequiredViewAsType(source, R.id.tv_cating_filter, "field 'tvCatingFilter'", TextView.class);
    target.tvPlantMainTag = Utils.findRequiredViewAsType(source, R.id.tv_plant_main_tag, "field 'tvPlantMainTag'", TextView.class);
    target.indicatorPlant = Utils.findRequiredView(source, R.id.indicator_plant, "field 'indicatorPlant'");
    view = Utils.findRequiredView(source, R.id.plant_rl, "field 'plantRl' and method 'dealClick'");
    target.plantRl = Utils.castView(view, R.id.plant_rl, "field 'plantRl'", RelativeLayout.class);
    view2131296803 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.tvChessAndCardFilter = Utils.findRequiredViewAsType(source, R.id.tv_chess_and_card_filter, "field 'tvChessAndCardFilter'", TextView.class);
    target.tvRaiseMainTag = Utils.findRequiredViewAsType(source, R.id.tv_raise_main_tag, "field 'tvRaiseMainTag'", TextView.class);
    target.indicatorRasie = Utils.findRequiredView(source, R.id.indicator_rasie, "field 'indicatorRasie'");
    view = Utils.findRequiredView(source, R.id.rasie_rl, "field 'rasieRl' and method 'dealClick'");
    target.rasieRl = Utils.castView(view, R.id.rasie_rl, "field 'rasieRl'", RelativeLayout.class);
    view2131296824 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.tvFinishFilter = Utils.findRequiredViewAsType(source, R.id.tv_finish_filter, "field 'tvFinishFilter'", TextView.class);
    target.tvMainProductTag = Utils.findRequiredViewAsType(source, R.id.tv_main_product_tag, "field 'tvMainProductTag'", TextView.class);
    target.indicatorProduct = Utils.findRequiredView(source, R.id.indicator_product, "field 'indicatorProduct'");
    view = Utils.findRequiredView(source, R.id.product_rl, "field 'productRl' and method 'dealClick'");
    target.productRl = Utils.castView(view, R.id.product_rl, "field 'productRl'", RelativeLayout.class);
    view2131296807 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.tvActivityFilter = Utils.findRequiredViewAsType(source, R.id.tv_activity_filter, "field 'tvActivityFilter'", TextView.class);
    target.tvMainActivityTag = Utils.findRequiredViewAsType(source, R.id.tv_main_activity_tag, "field 'tvMainActivityTag'", TextView.class);
    target.indicatorActivity = Utils.findRequiredView(source, R.id.indicator_activity, "field 'indicatorActivity'");
    view = Utils.findRequiredView(source, R.id.activity_rl, "field 'activityRl' and method 'dealClick'");
    target.activityRl = Utils.castView(view, R.id.activity_rl, "field 'activityRl'", RelativeLayout.class);
    view2131296284 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.tvFiveFilter = Utils.findRequiredViewAsType(source, R.id.tv_five_filter, "field 'tvFiveFilter'", TextView.class);
    target.tvMainCateringTag = Utils.findRequiredViewAsType(source, R.id.tv_main_catering_tag, "field 'tvMainCateringTag'", TextView.class);
    target.indicatorCatering = Utils.findRequiredView(source, R.id.indicator_catering, "field 'indicatorCatering'");
    view = Utils.findRequiredView(source, R.id.cateing_rl, "field 'cateingRl' and method 'dealClick'");
    target.cateingRl = Utils.castView(view, R.id.cateing_rl, "field 'cateingRl'", RelativeLayout.class);
    view2131296347 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.tvSixFilter = Utils.findRequiredViewAsType(source, R.id.tv_six_filter, "field 'tvSixFilter'", TextView.class);
    target.tvMainHotelTag = Utils.findRequiredViewAsType(source, R.id.tv_main_hotel_tag, "field 'tvMainHotelTag'", TextView.class);
    target.indicatorStay = Utils.findRequiredView(source, R.id.indicator_stay, "field 'indicatorStay'");
    view = Utils.findRequiredView(source, R.id.hotel_rl, "field 'hotelRl' and method 'dealClick'");
    target.hotelRl = Utils.castView(view, R.id.hotel_rl, "field 'hotelRl'", RelativeLayout.class);
    view2131296524 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.farmHappyRecycle = Utils.findRequiredViewAsType(source, R.id.farm_happy_recycle, "field 'farmHappyRecycle'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FarmHappyDetatilsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitleLeft = null;
    target.tvTitle = null;
    target.tvTitleRight = null;
    target.ivTitleRight = null;
    target.titleBar = null;
    target.farmHappyBanner = null;
    target.farmHappyStar = null;
    target.tvShowlocation = null;
    target.tvDistance = null;
    target.imgCall = null;
    target.tvCatingFilter = null;
    target.tvPlantMainTag = null;
    target.indicatorPlant = null;
    target.plantRl = null;
    target.tvChessAndCardFilter = null;
    target.tvRaiseMainTag = null;
    target.indicatorRasie = null;
    target.rasieRl = null;
    target.tvFinishFilter = null;
    target.tvMainProductTag = null;
    target.indicatorProduct = null;
    target.productRl = null;
    target.tvActivityFilter = null;
    target.tvMainActivityTag = null;
    target.indicatorActivity = null;
    target.activityRl = null;
    target.tvFiveFilter = null;
    target.tvMainCateringTag = null;
    target.indicatorCatering = null;
    target.cateingRl = null;
    target.tvSixFilter = null;
    target.tvMainHotelTag = null;
    target.indicatorStay = null;
    target.hotelRl = null;
    target.farmHappyRecycle = null;

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
