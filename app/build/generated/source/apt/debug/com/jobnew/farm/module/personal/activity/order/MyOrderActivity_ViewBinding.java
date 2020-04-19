// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity.order;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyOrderActivity_ViewBinding implements Unbinder {
  private MyOrderActivity target;

  @UiThread
  public MyOrderActivity_ViewBinding(MyOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyOrderActivity_ViewBinding(MyOrderActivity target, View source) {
    this.target = target;

    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
    target.viewpager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewpager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabLayout = null;
    target.viewpager = null;
  }
}
