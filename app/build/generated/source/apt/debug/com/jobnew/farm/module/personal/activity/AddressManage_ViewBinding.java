// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddressManage_ViewBinding implements Unbinder {
  private AddressManage target;

  @UiThread
  public AddressManage_ViewBinding(AddressManage target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddressManage_ViewBinding(AddressManage target, View source) {
    this.target = target;

    target.recyclerViewAdressManage = Utils.findRequiredViewAsType(source, R.id.recyclerView_adressManage, "field 'recyclerViewAdressManage'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddressManage target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerViewAdressManage = null;
  }
}
