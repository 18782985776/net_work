// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LandDetailsActivity_ViewBinding implements Unbinder {
  private LandDetailsActivity target;

  private View view2131296376;

  private View view2131297131;

  private View view2131297237;

  private View view2131297214;

  private View view2131296620;

  @UiThread
  public LandDetailsActivity_ViewBinding(LandDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LandDetailsActivity_ViewBinding(final LandDetailsActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.convenientBanner, "field 'convenientBanner' and method 'onViewClicked'");
    target.convenientBanner = Utils.castView(view, R.id.convenientBanner, "field 'convenientBanner'", ConvenientBanner.class);
    view2131296376 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvLandName = Utils.findRequiredViewAsType(source, R.id.tv_land_name, "field 'tvLandName'", TextView.class);
    target.tvUnitPrive = Utils.findRequiredViewAsType(source, R.id.tv_unit_prive, "field 'tvUnitPrive'", TextView.class);
    target.tvUpdateDateValus = Utils.findRequiredViewAsType(source, R.id.tv_update_date_valus, "field 'tvUpdateDateValus'", TextView.class);
    target.tvLandSize = Utils.findRequiredViewAsType(source, R.id.tv_land_size, "field 'tvLandSize'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_land_address, "field 'tvLandAddress' and method 'onViewClicked'");
    target.tvLandAddress = Utils.castView(view, R.id.tv_land_address, "field 'tvLandAddress'", TextView.class);
    view2131297131 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvLandDesc = Utils.findRequiredViewAsType(source, R.id.tv_land_desc, "field 'tvLandDesc'", TextView.class);
    target.tvCommentCount = Utils.findRequiredViewAsType(source, R.id.tv_comment_count, "field 'tvCommentCount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_total_conment, "field 'tvTotalConment' and method 'onViewClicked'");
    target.tvTotalConment = Utils.castView(view, R.id.tv_total_conment, "field 'tvTotalConment'", TextView.class);
    view2131297237 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvCropName = Utils.findRequiredViewAsType(source, R.id.tv_crop_name, "field 'tvCropName'", TextView.class);
    target.tvMaturityCycle = Utils.findRequiredViewAsType(source, R.id.tv_maturity_cycle, "field 'tvMaturityCycle'", TextView.class);
    target.tvCropUnitPrive = Utils.findRequiredViewAsType(source, R.id.tv_crop_unit_prive, "field 'tvCropUnitPrive'", TextView.class);
    target.tvCropUnit = Utils.findRequiredViewAsType(source, R.id.tv_crop_unit, "field 'tvCropUnit'", TextView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    target.view = Utils.findRequiredView(source, R.id.quantity_area, "field 'view'");
    view = Utils.findRequiredView(source, R.id.tv_save, "field 'tvSave' and method 'onViewClicked'");
    target.tvSave = Utils.castView(view, R.id.tv_save, "field 'tvSave'", TextView.class);
    view2131297214 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rv_content, "field 'recyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.iv_call, "method 'onViewClicked'");
    view2131296620 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LandDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.convenientBanner = null;
    target.tvLandName = null;
    target.tvUnitPrive = null;
    target.tvUpdateDateValus = null;
    target.tvLandSize = null;
    target.tvLandAddress = null;
    target.tvLandDesc = null;
    target.tvCommentCount = null;
    target.tvTotalConment = null;
    target.tvCropName = null;
    target.tvMaturityCycle = null;
    target.tvCropUnitPrive = null;
    target.tvCropUnit = null;
    target.tvTotalPrice = null;
    target.view = null;
    target.tvSave = null;
    target.recyclerView = null;

    view2131296376.setOnClickListener(null);
    view2131296376 = null;
    view2131297131.setOnClickListener(null);
    view2131297131 = null;
    view2131297237.setOnClickListener(null);
    view2131297237 = null;
    view2131297214.setOnClickListener(null);
    view2131297214 = null;
    view2131296620.setOnClickListener(null);
    view2131296620 = null;
  }
}
