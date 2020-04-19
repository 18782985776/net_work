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

public class SignboardAdapter$ViewHolder_ViewBinding implements Unbinder {
  private SignboardAdapter.ViewHolder target;

  @UiThread
  public SignboardAdapter$ViewHolder_ViewBinding(SignboardAdapter.ViewHolder target, View source) {
    this.target = target;

    target.ivSignboard = Utils.findRequiredViewAsType(source, R.id.iv_signboard, "field 'ivSignboard'", ImageView.class);
    target.tvSignboardName = Utils.findRequiredViewAsType(source, R.id.tv_signboard_name, "field 'tvSignboardName'", TextView.class);
    target.tvSignboardPrice = Utils.findRequiredViewAsType(source, R.id.tv_signboard_price, "field 'tvSignboardPrice'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SignboardAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivSignboard = null;
    target.tvSignboardName = null;
    target.tvSignboardPrice = null;
  }
}
