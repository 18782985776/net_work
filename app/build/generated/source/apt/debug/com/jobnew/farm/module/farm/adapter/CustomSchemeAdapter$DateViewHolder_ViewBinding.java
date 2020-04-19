// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomSchemeAdapter$DateViewHolder_ViewBinding implements Unbinder {
  private CustomSchemeAdapter.DateViewHolder target;

  @UiThread
  public CustomSchemeAdapter$DateViewHolder_ViewBinding(CustomSchemeAdapter.DateViewHolder target,
      View source) {
    this.target = target;

    target.stvName = Utils.findRequiredViewAsType(source, R.id.stv_name, "field 'stvName'", SuperTextView.class);
    target.stvDate = Utils.findRequiredViewAsType(source, R.id.stv_date, "field 'stvDate'", SuperTextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvUnitPrice = Utils.findRequiredViewAsType(source, R.id.tv_unit_price, "field 'tvUnitPrice'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomSchemeAdapter.DateViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.stvName = null;
    target.stvDate = null;
    target.tvName = null;
    target.tvUnitPrice = null;
  }
}
