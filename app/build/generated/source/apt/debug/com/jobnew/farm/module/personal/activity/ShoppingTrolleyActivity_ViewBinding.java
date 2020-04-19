// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShoppingTrolleyActivity_ViewBinding implements Unbinder {
  private ShoppingTrolleyActivity target;

  private View view2131296690;

  private View view2131297214;

  @UiThread
  public ShoppingTrolleyActivity_ViewBinding(ShoppingTrolleyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShoppingTrolleyActivity_ViewBinding(final ShoppingTrolleyActivity target, View source) {
    this.target = target;

    View view;
    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rv_content, "field 'rvContent'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.ll_all_select, "field 'mLlAllSelect' and method 'onViewClicked'");
    target.mLlAllSelect = Utils.castView(view, R.id.ll_all_select, "field 'mLlAllSelect'", LinearLayout.class);
    view2131296690 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_total_price, "field 'tvTotalPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_save, "method 'onViewClicked'");
    view2131297214 = view;
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
    ShoppingTrolleyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvContent = null;
    target.mLlAllSelect = null;
    target.tvTotalPrice = null;

    view2131296690.setOnClickListener(null);
    view2131296690 = null;
    view2131297214.setOnClickListener(null);
    view2131297214 = null;
  }
}
