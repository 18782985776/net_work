// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BreedingPlanActivity_ViewBinding implements Unbinder {
  private BreedingPlanActivity target;

  private View view2131296992;

  private View view2131296996;

  private View view2131296978;

  private View view2131296975;

  private View view2131296976;

  private View view2131296977;

  private View view2131296260;

  private View view2131296979;

  private View view2131297214;

  @UiThread
  public BreedingPlanActivity_ViewBinding(BreedingPlanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BreedingPlanActivity_ViewBinding(final BreedingPlanActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.stv_planting_scheme, "field 'mStvPlantingScheme' and method 'onClick'");
    target.mStvPlantingScheme = Utils.castView(view, R.id.stv_planting_scheme, "field 'mStvPlantingScheme'", SuperTextView.class);
    view2131296992 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvPlantingScheme = Utils.findRequiredViewAsType(source, R.id.tv_planting_scheme, "field 'mTvPlantingScheme'", TextView.class);
    view = Utils.findRequiredView(source, R.id.stv_signboard, "field 'mStvSignboard' and method 'onClick'");
    target.mStvSignboard = Utils.castView(view, R.id.stv_signboard, "field 'mStvSignboard'", SuperTextView.class);
    view2131296996 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvSignboard = Utils.findRequiredViewAsType(source, R.id.tv_signboard, "field 'mTvSignboard'", TextView.class);
    view = Utils.findRequiredView(source, R.id.stv_execution_steward, "field 'mStvSteward' and method 'onClick'");
    target.mStvSteward = Utils.castView(view, R.id.stv_execution_steward, "field 'mStvSteward'", SuperTextView.class);
    view2131296978 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.stv_distribution, "field 'mStvDistribution' and method 'onClick'");
    target.mStvDistribution = Utils.castView(view, R.id.stv_distribution, "field 'mStvDistribution'", SuperTextView.class);
    view2131296975 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.stv_distribution_addr, "field 'mStvDistributionAddr' and method 'onClick'");
    target.mStvDistributionAddr = Utils.castView(view, R.id.stv_distribution_addr, "field 'mStvDistributionAddr'", SuperTextView.class);
    view2131296976 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.stv_donate, "field 'mStvDonate' and method 'onClick'");
    target.mStvDonate = Utils.castView(view, R.id.stv_donate, "field 'mStvDonate'", SuperTextView.class);
    view2131296977 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.Stv_product_processing, "field 'mStvProcessing' and method 'onClick'");
    target.mStvProcessing = Utils.castView(view, R.id.Stv_product_processing, "field 'mStvProcessing'", SuperTextView.class);
    view2131296260 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvProcessing = Utils.findRequiredViewAsType(source, R.id.tv_product_processing, "field 'mTvProcessing'", TextView.class);
    view = Utils.findRequiredView(source, R.id.stv_insurance, "field 'mStvInsurance' and method 'onClick'");
    target.mStvInsurance = Utils.castView(view, R.id.stv_insurance, "field 'mStvInsurance'", SuperTextView.class);
    view2131296979 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'mTvTotalPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_save, "field 'mTvSave' and method 'onClick'");
    target.mTvSave = Utils.castView(view, R.id.tv_save, "field 'mTvSave'", TextView.class);
    view2131297214 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvUnitPrice = Utils.findRequiredViewAsType(source, R.id.tv_unit_price, "field 'tvUnitPrice'", TextView.class);
    target.etName1 = Utils.findRequiredViewAsType(source, R.id.et_name1, "field 'etName1'", EditText.class);
    target.etName2 = Utils.findRequiredViewAsType(source, R.id.et_name2, "field 'etName2'", EditText.class);
    target.etName3 = Utils.findRequiredViewAsType(source, R.id.et_name3, "field 'etName3'", EditText.class);
    target.tvDistributioPrice = Utils.findRequiredViewAsType(source, R.id.tv_distributio_price, "field 'tvDistributioPrice'", TextView.class);
    target.numView = Utils.findRequiredView(source, R.id.quantity_num, "field 'numView'");
  }

  @Override
  @CallSuper
  public void unbind() {
    BreedingPlanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mStvPlantingScheme = null;
    target.mTvPlantingScheme = null;
    target.mStvSignboard = null;
    target.mTvSignboard = null;
    target.mStvSteward = null;
    target.mStvDistribution = null;
    target.mStvDistributionAddr = null;
    target.mStvDonate = null;
    target.mStvProcessing = null;
    target.mTvProcessing = null;
    target.mStvInsurance = null;
    target.mTvTotalPrice = null;
    target.mTvSave = null;
    target.tvPrice = null;
    target.tvUnitPrice = null;
    target.etName1 = null;
    target.etName2 = null;
    target.etName3 = null;
    target.tvDistributioPrice = null;
    target.numView = null;

    view2131296992.setOnClickListener(null);
    view2131296992 = null;
    view2131296996.setOnClickListener(null);
    view2131296996 = null;
    view2131296978.setOnClickListener(null);
    view2131296978 = null;
    view2131296975.setOnClickListener(null);
    view2131296975 = null;
    view2131296976.setOnClickListener(null);
    view2131296976 = null;
    view2131296977.setOnClickListener(null);
    view2131296977 = null;
    view2131296260.setOnClickListener(null);
    view2131296260 = null;
    view2131296979.setOnClickListener(null);
    view2131296979 = null;
    view2131297214.setOnClickListener(null);
    view2131297214 = null;
  }
}
