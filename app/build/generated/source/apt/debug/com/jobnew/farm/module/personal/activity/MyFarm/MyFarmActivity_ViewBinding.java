// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity.MyFarm;

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

public class MyFarmActivity_ViewBinding implements Unbinder {
  private MyFarmActivity target;

  @UiThread
  public MyFarmActivity_ViewBinding(MyFarmActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyFarmActivity_ViewBinding(MyFarmActivity target, View source) {
    this.target = target;

    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
    target.viewpager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewpager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyFarmActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabLayout = null;
    target.viewpager = null;
  }
}
