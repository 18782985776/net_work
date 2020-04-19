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

public class ProductProcessAdapter$ViewHolder_ViewBinding implements Unbinder {
  private ProductProcessAdapter.ViewHolder target;

  @UiThread
  public ProductProcessAdapter$ViewHolder_ViewBinding(ProductProcessAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.ivProductProcessSelect = Utils.findRequiredViewAsType(source, R.id.iv_product_process_select, "field 'ivProductProcessSelect'", ImageView.class);
    target.tvProductProcessName = Utils.findRequiredViewAsType(source, R.id.tv_product_process_name, "field 'tvProductProcessName'", TextView.class);
    target.tvPpUnitPrive = Utils.findRequiredViewAsType(source, R.id.tv_pp_unit_prive, "field 'tvPpUnitPrive'", TextView.class);
    target.tvPpPrice = Utils.findRequiredViewAsType(source, R.id.tv_pp_price, "field 'tvPpPrice'", TextView.class);
    target.tvPpDescribe = Utils.findRequiredViewAsType(source, R.id.tv_pp_describe, "field 'tvPpDescribe'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProductProcessAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivProductProcessSelect = null;
    target.tvProductProcessName = null;
    target.tvPpUnitPrive = null;
    target.tvPpPrice = null;
    target.tvPpDescribe = null;
  }
}
