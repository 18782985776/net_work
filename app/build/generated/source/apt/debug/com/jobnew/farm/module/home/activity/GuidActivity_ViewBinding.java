// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GuidActivity_ViewBinding implements Unbinder {
  private GuidActivity target;

  @UiThread
  public GuidActivity_ViewBinding(GuidActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GuidActivity_ViewBinding(GuidActivity target, View source) {
    this.target = target;

    target.guidPager = Utils.findRequiredViewAsType(source, R.id.guid_pager, "field 'guidPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GuidActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.guidPager = null;
  }
}
