// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.exclusive;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ConfirmExclusiveLandOrderActivity_ViewBinding implements Unbinder {
  private ConfirmExclusiveLandOrderActivity target;

  private View view2131297214;

  @UiThread
  public ConfirmExclusiveLandOrderActivity_ViewBinding(ConfirmExclusiveLandOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ConfirmExclusiveLandOrderActivity_ViewBinding(final ConfirmExclusiveLandOrderActivity target,
      View source) {
    this.target = target;

    View view;
    target.ivFarm = Utils.findRequiredViewAsType(source, R.id.iv_farm, "field 'ivFarm'", ImageView.class);
    target.tvFarmName = Utils.findRequiredViewAsType(source, R.id.tv_farm_name, "field 'tvFarmName'", TextView.class);
    target.ivProduct = Utils.findRequiredViewAsType(source, R.id.iv_product, "field 'ivProduct'", ImageView.class);
    target.tvProductName = Utils.findRequiredViewAsType(source, R.id.tv_product_name, "field 'tvProductName'", TextView.class);
    target.tvCount = Utils.findRequiredViewAsType(source, R.id.tv_count, "field 'tvCount'", TextView.class);
    target.tvUnitPrice = Utils.findRequiredViewAsType(source, R.id.tv_unit_price, "field 'tvUnitPrice'", TextView.class);
    target.tvOrderTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_order_total_price, "field 'tvOrderTotalPrice'", TextView.class);
    target.time = Utils.findRequiredView(source, R.id.view_time, "field 'time'");
    view = Utils.findRequiredView(source, R.id.tv_save, "method 'onViewClicked'");
    view2131297214 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ConfirmExclusiveLandOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivFarm = null;
    target.tvFarmName = null;
    target.ivProduct = null;
    target.tvProductName = null;
    target.tvCount = null;
    target.tvUnitPrice = null;
    target.tvOrderTotalPrice = null;
    target.time = null;

    view2131297214.setOnClickListener(null);
    view2131297214 = null;
  }
}
