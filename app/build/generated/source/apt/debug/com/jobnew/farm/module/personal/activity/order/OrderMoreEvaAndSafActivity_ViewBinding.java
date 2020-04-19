// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity.order;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderMoreEvaAndSafActivity_ViewBinding implements Unbinder {
  private OrderMoreEvaAndSafActivity target;

  @UiThread
  public OrderMoreEvaAndSafActivity_ViewBinding(OrderMoreEvaAndSafActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderMoreEvaAndSafActivity_ViewBinding(OrderMoreEvaAndSafActivity target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderMoreEvaAndSafActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
  }
}
