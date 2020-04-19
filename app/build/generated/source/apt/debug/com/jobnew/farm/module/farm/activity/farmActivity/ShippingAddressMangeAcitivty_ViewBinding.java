// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.activity.farmActivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShippingAddressMangeAcitivty_ViewBinding implements Unbinder {
  private ShippingAddressMangeAcitivty target;

  @UiThread
  public ShippingAddressMangeAcitivty_ViewBinding(ShippingAddressMangeAcitivty target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShippingAddressMangeAcitivty_ViewBinding(ShippingAddressMangeAcitivty target,
      View source) {
    this.target = target;

    target.tvTitleLeft = Utils.findRequiredViewAsType(source, R.id.tv_title_left, "field 'tvTitleLeft'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvTitleRight = Utils.findRequiredViewAsType(source, R.id.tv_title_right, "field 'tvTitleRight'", TextView.class);
    target.ivTitleRight = Utils.findRequiredViewAsType(source, R.id.iv_title_right, "field 'ivTitleRight'", ImageView.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.title_bar, "field 'titleBar'", LinearLayout.class);
    target.recycleViewShippingAddressManage = Utils.findRequiredViewAsType(source, R.id.recycleView_shipping_address_manage, "field 'recycleViewShippingAddressManage'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShippingAddressMangeAcitivty target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitleLeft = null;
    target.tvTitle = null;
    target.tvTitleRight = null;
    target.ivTitleRight = null;
    target.titleBar = null;
    target.recycleViewShippingAddressManage = null;
  }
}
