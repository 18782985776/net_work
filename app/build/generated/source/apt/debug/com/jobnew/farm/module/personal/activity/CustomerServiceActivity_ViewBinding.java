// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomerServiceActivity_ViewBinding implements Unbinder {
  private CustomerServiceActivity target;

  private View view2131296881;

  private View view2131296885;

  @UiThread
  public CustomerServiceActivity_ViewBinding(CustomerServiceActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CustomerServiceActivity_ViewBinding(final CustomerServiceActivity target, View source) {
    this.target = target;

    View view;
    target.txtPhone = Utils.findRequiredViewAsType(source, R.id.txt_phone, "field 'txtPhone'", TextView.class);
    target.txtQq = Utils.findRequiredViewAsType(source, R.id.txt_qq, "field 'txtQq'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_phone, "method 'onViewClicked'");
    view2131296881 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_qq, "method 'onViewClicked'");
    view2131296885 = view;
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
    CustomerServiceActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtPhone = null;
    target.txtQq = null;

    view2131296881.setOnClickListener(null);
    view2131296881 = null;
    view2131296885.setOnClickListener(null);
    view2131296885 = null;
  }
}
