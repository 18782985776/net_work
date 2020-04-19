// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

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

public class MyFarmHappyOrderActivity_ViewBinding implements Unbinder {
  private MyFarmHappyOrderActivity target;

  @UiThread
  public MyFarmHappyOrderActivity_ViewBinding(MyFarmHappyOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyFarmHappyOrderActivity_ViewBinding(MyFarmHappyOrderActivity target, View source) {
    this.target = target;

    target.tablayout = Utils.findRequiredViewAsType(source, R.id.tablayout, "field 'tablayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyFarmHappyOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tablayout = null;
    target.viewPager = null;
  }
}
