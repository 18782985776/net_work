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

public class ConfirmOrderAdapter$MainViewHolder_ViewBinding implements Unbinder {
  private ConfirmOrderAdapter.MainViewHolder target;

  @UiThread
  public ConfirmOrderAdapter$MainViewHolder_ViewBinding(ConfirmOrderAdapter.MainViewHolder target,
      View source) {
    this.target = target;

    target.ivProduct = Utils.findRequiredViewAsType(source, R.id.iv_product, "field 'ivProduct'", ImageView.class);
    target.tvProductName = Utils.findRequiredViewAsType(source, R.id.tv_product_name, "field 'tvProductName'", TextView.class);
    target.tvHireTimeName = Utils.findRequiredViewAsType(source, R.id.tv_hire_time_name, "field 'tvHireTimeName'", TextView.class);
    target.tvHireTime = Utils.findRequiredViewAsType(source, R.id.tv_hire_time, "field 'tvHireTime'", TextView.class);
    target.tvCountName = Utils.findRequiredViewAsType(source, R.id.tv_count_name, "field 'tvCountName'", TextView.class);
    target.tvCount = Utils.findRequiredViewAsType(source, R.id.tv_count, "field 'tvCount'", TextView.class);
    target.tvUnitPrice = Utils.findRequiredViewAsType(source, R.id.tv_unit_price, "field 'tvUnitPrice'", TextView.class);
    target.tvUnit = Utils.findRequiredViewAsType(source, R.id.tv_unit, "field 'tvUnit'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ConfirmOrderAdapter.MainViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivProduct = null;
    target.tvProductName = null;
    target.tvHireTimeName = null;
    target.tvHireTime = null;
    target.tvCountName = null;
    target.tvCount = null;
    target.tvUnitPrice = null;
    target.tvUnit = null;
  }
}
