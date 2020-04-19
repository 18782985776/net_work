// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CateringDetailsActivity_ViewBinding implements Unbinder {
  private CateringDetailsActivity target;

  private View view2131296555;

  private View view2131296840;

  private View view2131296598;

  private View view2131296396;

  private View view2131296512;

  @UiThread
  public CateringDetailsActivity_ViewBinding(CateringDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CateringDetailsActivity_ViewBinding(final CateringDetailsActivity target, View source) {
    this.target = target;

    View view;
    target.banner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'banner'", ConvenientBanner.class);
    target.nameTv = Utils.findRequiredViewAsType(source, R.id.name_tv, "field 'nameTv'", TextView.class);
    target.priceTv = Utils.findRequiredViewAsType(source, R.id.price_tv, "field 'priceTv'", TextView.class);
    target.storeTv = Utils.findRequiredViewAsType(source, R.id.store_tv, "field 'storeTv'", TextView.class);
    target.addressTv = Utils.findRequiredViewAsType(source, R.id.address_tv, "field 'addressTv'", TextView.class);
    target.distanceTv = Utils.findRequiredViewAsType(source, R.id.distance_tv, "field 'distanceTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.img_call, "field 'imgCall' and method 'dealClick'");
    target.imgCall = Utils.castView(view, R.id.img_call, "field 'imgCall'", ImageView.class);
    view2131296555 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.reduce_img, "field 'reduceImg' and method 'dealClick'");
    target.reduceImg = Utils.castView(view, R.id.reduce_img, "field 'reduceImg'", ImageView.class);
    view2131296840 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.contentTv = Utils.findRequiredViewAsType(source, R.id.content_tv, "field 'contentTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.increase_img, "field 'increaseImg' and method 'dealClick'");
    target.increaseImg = Utils.castView(view, R.id.increase_img, "field 'increaseImg'", ImageView.class);
    view2131296598 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.unitTv = Utils.findRequiredViewAsType(source, R.id.unit_tv, "field 'unitTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.date_tv, "field 'dateTv' and method 'dealClick'");
    target.dateTv = Utils.castView(view, R.id.date_tv, "field 'dateTv'", TextView.class);
    view2131296396 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.introduceTv = Utils.findRequiredViewAsType(source, R.id.introduce_tv, "field 'introduceTv'", TextView.class);
    target.detailsTv = Utils.findRequiredViewAsType(source, R.id.details_tv, "field 'detailsTv'", TextView.class);
    target.totalPriceTv = Utils.findRequiredViewAsType(source, R.id.total_price_tv, "field 'totalPriceTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.go_buy_bt, "field 'goBuyBt' and method 'dealClick'");
    target.goBuyBt = Utils.castView(view, R.id.go_buy_bt, "field 'goBuyBt'", Button.class);
    view2131296512 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.dealClick(p0);
      }
    });
    target.updateDateTv = Utils.findRequiredViewAsType(source, R.id.tv_update_date_name, "field 'updateDateTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CateringDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.banner = null;
    target.nameTv = null;
    target.priceTv = null;
    target.storeTv = null;
    target.addressTv = null;
    target.distanceTv = null;
    target.imgCall = null;
    target.reduceImg = null;
    target.contentTv = null;
    target.increaseImg = null;
    target.unitTv = null;
    target.dateTv = null;
    target.introduceTv = null;
    target.detailsTv = null;
    target.totalPriceTv = null;
    target.goBuyBt = null;
    target.updateDateTv = null;

    view2131296555.setOnClickListener(null);
    view2131296555 = null;
    view2131296840.setOnClickListener(null);
    view2131296840 = null;
    view2131296598.setOnClickListener(null);
    view2131296598 = null;
    view2131296396.setOnClickListener(null);
    view2131296396 = null;
    view2131296512.setOnClickListener(null);
    view2131296512 = null;
  }
}
