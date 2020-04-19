// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WithdrawActivity_ViewBinding implements Unbinder {
  private WithdrawActivity target;

  private View view2131296853;

  private View view2131296893;

  @UiThread
  public WithdrawActivity_ViewBinding(WithdrawActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WithdrawActivity_ViewBinding(final WithdrawActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rl_ali, "method 'onViewClicked'");
    view2131296853 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_weixin, "method 'onViewClicked'");
    view2131296893 = view;
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
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131296853.setOnClickListener(null);
    view2131296853 = null;
    view2131296893.setOnClickListener(null);
    view2131296893 = null;
  }
}
