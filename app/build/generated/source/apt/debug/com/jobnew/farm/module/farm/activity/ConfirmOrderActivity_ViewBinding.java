// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ConfirmOrderActivity_ViewBinding implements Unbinder {
  private ConfirmOrderActivity target;

  private View view2131297214;

  @UiThread
  public ConfirmOrderActivity_ViewBinding(ConfirmOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ConfirmOrderActivity_ViewBinding(final ConfirmOrderActivity target, View source) {
    this.target = target;

    View view;
    target.tvFarmName = Utils.findRequiredViewAsType(source, R.id.tv_farm_name, "field 'tvFarmName'", TextView.class);
    target.ivImageView = Utils.findRequiredViewAsType(source, R.id.iv_farm, "field 'ivImageView'", ImageView.class);
    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rv_content, "field 'rvContent'", RecyclerView.class);
    target.tvOrderTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_order_total_price, "field 'tvOrderTotalPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_save, "field 'tvSave' and method 'onClick'");
    target.tvSave = Utils.castView(view, R.id.tv_save, "field 'tvSave'", TextView.class);
    view2131297214 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ConfirmOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvFarmName = null;
    target.ivImageView = null;
    target.rvContent = null;
    target.tvOrderTotalPrice = null;
    target.tvSave = null;

    view2131297214.setOnClickListener(null);
    view2131297214 = null;
  }
}
