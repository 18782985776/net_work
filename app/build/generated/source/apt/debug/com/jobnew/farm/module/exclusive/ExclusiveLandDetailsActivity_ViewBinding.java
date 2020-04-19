// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.exclusive;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExclusiveLandDetailsActivity_ViewBinding implements Unbinder {
  private ExclusiveLandDetailsActivity target;

  private View view2131297131;

  private View view2131296620;

  private View view2131296619;

  private View view2131297064;

  private View view2131297214;

  private View view2131296868;

  @UiThread
  public ExclusiveLandDetailsActivity_ViewBinding(ExclusiveLandDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ExclusiveLandDetailsActivity_ViewBinding(final ExclusiveLandDetailsActivity target,
      View source) {
    this.target = target;

    View view;
    target.convenientBanner = Utils.findRequiredViewAsType(source, R.id.convenientBanner, "field 'convenientBanner'", ConvenientBanner.class);
    view = Utils.findRequiredView(source, R.id.tv_land_address, "field 'tvLandAddress' and method 'onViewClicked'");
    target.tvLandAddress = Utils.castView(view, R.id.tv_land_address, "field 'tvLandAddress'", TextView.class);
    view2131297131 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvDistance = Utils.findRequiredViewAsType(source, R.id.tv_distance, "field 'tvDistance'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_call, "field 'ivCall' and method 'onViewClicked'");
    target.ivCall = Utils.castView(view, R.id.iv_call, "field 'ivCall'", ImageView.class);
    view2131296620 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvFarmAffiliation = Utils.findRequiredViewAsType(source, R.id.tv_farm_affiliation, "field 'tvFarmAffiliation'", TextView.class);
    target.tvFarmName = Utils.findRequiredViewAsType(source, R.id.tv_farm_name, "field 'tvFarmName'", TextView.class);
    target.ivFarm = Utils.findRequiredViewAsType(source, R.id.iv_farm, "field 'ivFarm'", ImageView.class);
    target.tvAreaTitle = Utils.findRequiredViewAsType(source, R.id.tv_area_title, "field 'tvAreaTitle'", TextView.class);
    target.tvArea = Utils.findRequiredViewAsType(source, R.id.tv_area, "field 'tvArea'", TextView.class);
    target.tvUnitPriceTitle = Utils.findRequiredViewAsType(source, R.id.tv_unit_price_title, "field 'tvUnitPriceTitle'", TextView.class);
    target.tvUnitPrice = Utils.findRequiredViewAsType(source, R.id.tv_unit_price, "field 'tvUnitPrice'", TextView.class);
    target.tvLandFitTitle = Utils.findRequiredViewAsType(source, R.id.tv_land_fit_title, "field 'tvLandFitTitle'", TextView.class);
    target.tvLandFit = Utils.findRequiredViewAsType(source, R.id.tv_land_fit, "field 'tvLandFit'", TextView.class);
    target.ivLandFit = Utils.findRequiredViewAsType(source, R.id.iv_land_fit, "field 'ivLandFit'", ImageView.class);
    target.tvInfro = Utils.findRequiredViewAsType(source, R.id.tv_intro, "field 'tvInfro'", TextView.class);
    target.leaseTime = Utils.findRequiredView(source, R.id.lease_time, "field 'leaseTime'");
    target.tvDateUnit = Utils.findRequiredViewAsType(source, R.id.tv_date_unit, "field 'tvDateUnit'", TextView.class);
    target.tvTotalPriceName = Utils.findRequiredViewAsType(source, R.id.tv_total_price_name, "field 'tvTotalPriceName'", TextView.class);
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_agreement, "field 'ivAgreement' and method 'onViewClicked'");
    target.ivAgreement = Utils.castView(view, R.id.iv_agreement, "field 'ivAgreement'", ImageView.class);
    view2131296619 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvUse = Utils.findRequiredViewAsType(source, R.id.tv_use, "field 'tvUse'", TextView.class);
    target.rgUse = Utils.findRequiredViewAsType(source, R.id.rg_use, "field 'rgUse'", RadioGroup.class);
    target.rbPlant = Utils.findRequiredViewAsType(source, R.id.rb_plant, "field 'rbPlant'", RadioButton.class);
    target.rbBreed = Utils.findRequiredViewAsType(source, R.id.rb_breed, "field 'rbBreed'", RadioButton.class);
    target.rbActivity = Utils.findRequiredViewAsType(source, R.id.rb_activity, "field 'rbActivity'", RadioButton.class);
    target.rbOther = Utils.findRequiredViewAsType(source, R.id.rb_other, "field 'rbOther'", RadioButton.class);
    target.tvUseIntro = Utils.findRequiredViewAsType(source, R.id.tv_use_intro, "field 'tvUseIntro'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_agreement, "method 'onViewClicked'");
    view2131297064 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_save, "method 'onViewClicked'");
    view2131297214 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_farm_affiliation, "method 'onViewClicked'");
    view2131296868 = view;
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
    ExclusiveLandDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.convenientBanner = null;
    target.tvLandAddress = null;
    target.tvDistance = null;
    target.ivCall = null;
    target.tvFarmAffiliation = null;
    target.tvFarmName = null;
    target.ivFarm = null;
    target.tvAreaTitle = null;
    target.tvArea = null;
    target.tvUnitPriceTitle = null;
    target.tvUnitPrice = null;
    target.tvLandFitTitle = null;
    target.tvLandFit = null;
    target.ivLandFit = null;
    target.tvInfro = null;
    target.leaseTime = null;
    target.tvDateUnit = null;
    target.tvTotalPriceName = null;
    target.tvTotalPrice = null;
    target.ivAgreement = null;
    target.tvUse = null;
    target.rgUse = null;
    target.rbPlant = null;
    target.rbBreed = null;
    target.rbActivity = null;
    target.rbOther = null;
    target.tvUseIntro = null;

    view2131297131.setOnClickListener(null);
    view2131297131 = null;
    view2131296620.setOnClickListener(null);
    view2131296620 = null;
    view2131296619.setOnClickListener(null);
    view2131296619 = null;
    view2131297064.setOnClickListener(null);
    view2131297064 = null;
    view2131297214.setOnClickListener(null);
    view2131297214 = null;
    view2131296868.setOnClickListener(null);
    view2131296868 = null;
  }
}
