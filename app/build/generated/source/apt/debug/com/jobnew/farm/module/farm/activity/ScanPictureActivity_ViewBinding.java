// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScanPictureActivity_ViewBinding implements Unbinder {
  private ScanPictureActivity target;

  @UiThread
  public ScanPictureActivity_ViewBinding(ScanPictureActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ScanPictureActivity_ViewBinding(ScanPictureActivity target, View source) {
    this.target = target;

    target.scanPager = Utils.findRequiredViewAsType(source, R.id.scan_pager, "field 'scanPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ScanPictureActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.scanPager = null;
  }
}
