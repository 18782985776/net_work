// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.tabFarm = Utils.findRequiredViewAsType(source, R.id.tab_farm, "field 'tabFarm'", RadioButton.class);
    target.tabBazaar = Utils.findRequiredViewAsType(source, R.id.tab_bazaar, "field 'tabBazaar'", RadioButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabFarm = null;
    target.tabBazaar = null;
  }
}
