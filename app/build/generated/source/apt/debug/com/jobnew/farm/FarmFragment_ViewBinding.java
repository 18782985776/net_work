// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FarmFragment_ViewBinding implements Unbinder {
  private FarmFragment target;

  private View view2131297202;

  private View view2131297121;

  private View view2131297104;

  private View view2131297229;

  @UiThread
  public FarmFragment_ViewBinding(final FarmFragment target, View source) {
    this.target = target;

    View view;
    target.titleAddressTv = Utils.findRequiredViewAsType(source, R.id.title_address_tv, "field 'titleAddressTv'", TextView.class);
    target.titleTv = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'titleTv'", TextView.class);
    target.farmSearchImg = Utils.findRequiredViewAsType(source, R.id.farm_search_img, "field 'farmSearchImg'", ImageView.class);
    target.messageImg = Utils.findRequiredViewAsType(source, R.id.message_img, "field 'messageImg'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_ranking_filter, "field 'tvRankingFilter' and method 'startFilter'");
    target.tvRankingFilter = Utils.castView(view, R.id.tv_ranking_filter, "field 'tvRankingFilter'", TextView.class);
    view2131297202 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startFilter(Utils.<TextView>castParam(p0, "doClick", 0, "startFilter", 0));
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_hot_filter, "field 'tvHotFilter' and method 'startFilter'");
    target.tvHotFilter = Utils.castView(view, R.id.tv_hot_filter, "field 'tvHotFilter'", TextView.class);
    view2131297121 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startFilter(Utils.<TextView>castParam(p0, "doClick", 0, "startFilter", 0));
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_distance_filter, "field 'tvDistanceFilter' and method 'startFilter'");
    target.tvDistanceFilter = Utils.castView(view, R.id.tv_distance_filter, "field 'tvDistanceFilter'", TextView.class);
    view2131297104 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startFilter(Utils.<TextView>castParam(p0, "doClick", 0, "startFilter", 0));
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_super_filter, "field 'tvSuperFilter' and method 'startFilter'");
    target.tvSuperFilter = Utils.castView(view, R.id.tv_super_filter, "field 'tvSuperFilter'", TextView.class);
    view2131297229 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startFilter(Utils.<TextView>castParam(p0, "doClick", 0, "startFilter", 0));
      }
    });
    target.superFilterMenue = Utils.findRequiredViewAsType(source, R.id.super_filter_menue, "field 'superFilterMenue'", HorizontalScrollView.class);
    target.tvMainBusinessPlant = Utils.findRequiredViewAsType(source, R.id.tv_main_business_plant, "field 'tvMainBusinessPlant'", TextView.class);
    target.tvMainBusinessRaise = Utils.findRequiredViewAsType(source, R.id.tv_main_business_raise, "field 'tvMainBusinessRaise'", TextView.class);
    target.tvMainBusinessCatering = Utils.findRequiredViewAsType(source, R.id.tv_main_business_catering, "field 'tvMainBusinessCatering'", TextView.class);
    target.tvMainBusinessHotel = Utils.findRequiredViewAsType(source, R.id.tv_main_business_hotel, "field 'tvMainBusinessHotel'", TextView.class);
    target.tvMainBusinessGarden = Utils.findRequiredViewAsType(source, R.id.tv_main_business_garden, "field 'tvMainBusinessGarden'", TextView.class);
    target.tvMainBusinessProduct = Utils.findRequiredViewAsType(source, R.id.tv_main_business_product, "field 'tvMainBusinessProduct'", TextView.class);
    target.tvMainBusinessCancle = Utils.findRequiredViewAsType(source, R.id.tv_main_business_cancle, "field 'tvMainBusinessCancle'", TextView.class);
    target.tvMainBusinessOther = Utils.findRequiredViewAsType(source, R.id.tv_main_business_other, "field 'tvMainBusinessOther'", TextView.class);
    target.img1 = Utils.findRequiredViewAsType(source, R.id.img1, "field 'img1'", ImageView.class);
    target.img2 = Utils.findRequiredViewAsType(source, R.id.img2, "field 'img2'", ImageView.class);
    target.img3 = Utils.findRequiredViewAsType(source, R.id.img3, "field 'img3'", ImageView.class);
    target.img4 = Utils.findRequiredViewAsType(source, R.id.img4, "field 'img4'", ImageView.class);
    target.img5 = Utils.findRequiredViewAsType(source, R.id.img5, "field 'img5'", ImageView.class);
    target.img6 = Utils.findRequiredViewAsType(source, R.id.img6, "field 'img6'", ImageView.class);
    target.img7 = Utils.findRequiredViewAsType(source, R.id.img7, "field 'img7'", ImageView.class);
    target.img8 = Utils.findRequiredViewAsType(source, R.id.img8, "field 'img8'", ImageView.class);
    target.hotImg = Utils.findRequiredViewAsType(source, R.id.hot_img, "field 'hotImg'", ImageView.class);
    target.distanceImg = Utils.findRequiredViewAsType(source, R.id.distance_img, "field 'distanceImg'", ImageView.class);
    target.superImg = Utils.findRequiredViewAsType(source, R.id.super_img, "field 'superImg'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FarmFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleAddressTv = null;
    target.titleTv = null;
    target.farmSearchImg = null;
    target.messageImg = null;
    target.tvRankingFilter = null;
    target.tvHotFilter = null;
    target.tvDistanceFilter = null;
    target.tvSuperFilter = null;
    target.superFilterMenue = null;
    target.tvMainBusinessPlant = null;
    target.tvMainBusinessRaise = null;
    target.tvMainBusinessCatering = null;
    target.tvMainBusinessHotel = null;
    target.tvMainBusinessGarden = null;
    target.tvMainBusinessProduct = null;
    target.tvMainBusinessCancle = null;
    target.tvMainBusinessOther = null;
    target.img1 = null;
    target.img2 = null;
    target.img3 = null;
    target.img4 = null;
    target.img5 = null;
    target.img6 = null;
    target.img7 = null;
    target.img8 = null;
    target.hotImg = null;
    target.distanceImg = null;
    target.superImg = null;

    view2131297202.setOnClickListener(null);
    view2131297202 = null;
    view2131297121.setOnClickListener(null);
    view2131297121 = null;
    view2131297104.setOnClickListener(null);
    view2131297104 = null;
    view2131297229.setOnClickListener(null);
    view2131297229 = null;
  }
}
