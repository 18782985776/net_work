// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InsuranceAdapter$ViewHolder_ViewBinding implements Unbinder {
  private InsuranceAdapter.ViewHolder target;

  @UiThread
  public InsuranceAdapter$ViewHolder_ViewBinding(InsuranceAdapter.ViewHolder target, View source) {
    this.target = target;

    target.ivInsuranceSelect = Utils.findRequiredViewAsType(source, R.id.iv_insurance_select, "field 'ivInsuranceSelect'", ImageView.class);
    target.tvInsuranceName = Utils.findRequiredViewAsType(source, R.id.tv_insurance_name, "field 'tvInsuranceName'", TextView.class);
    target.tvInsuranceUnitPrive = Utils.findRequiredViewAsType(source, R.id.tv_insurance_unit_prive, "field 'tvInsuranceUnitPrive'", TextView.class);
    target.tvInsurancePrice = Utils.findRequiredViewAsType(source, R.id.tv_insurance_price, "field 'tvInsurancePrice'", TextView.class);
    target.tvInsuranceDescribe = Utils.findRequiredViewAsType(source, R.id.tv_insurance_describe, "field 'tvInsuranceDescribe'", TextView.class);
    target.tvCompensationRatio = Utils.findRequiredViewAsType(source, R.id.tv_compensation_ratio, "field 'tvCompensationRatio'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InsuranceAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivInsuranceSelect = null;
    target.tvInsuranceName = null;
    target.tvInsuranceUnitPrive = null;
    target.tvInsurancePrice = null;
    target.tvInsuranceDescribe = null;
    target.tvCompensationRatio = null;
  }
}
