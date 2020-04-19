// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import com.marno.easystatelibrary.EasyStatusView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ConfirmOrderActivity_ViewBinding implements Unbinder {
  private ConfirmOrderActivity target;

  private View view2131296687;

  private View view2131297214;

  @UiThread
  public ConfirmOrderActivity_ViewBinding(ConfirmOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ConfirmOrderActivity_ViewBinding(final ConfirmOrderActivity target, View source) {
    this.target = target;

    View view;
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvReceiver = Utils.findRequiredViewAsType(source, R.id.tv_receiver, "field 'tvReceiver'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_address, "field 'llAddress' and method 'onViewClicked'");
    target.llAddress = Utils.castView(view, R.id.ll_address, "field 'llAddress'", LinearLayout.class);
    view2131296687 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivFarm = Utils.findRequiredViewAsType(source, R.id.iv_farm, "field 'ivFarm'", ImageView.class);
    target.tvFarmName = Utils.findRequiredViewAsType(source, R.id.tv_farm_name, "field 'tvFarmName'", TextView.class);
    target.llFarm = Utils.findRequiredViewAsType(source, R.id.ll_farm, "field 'llFarm'", LinearLayout.class);
    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rv_content, "field 'rvContent'", RecyclerView.class);
    target.tvOrderTotalPrice = Utils.findRequiredViewAsType(source, R.id.tv_order_total_price, "field 'tvOrderTotalPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_save, "field 'tvSave' and method 'onViewClicked'");
    target.tvSave = Utils.castView(view, R.id.tv_save, "field 'tvSave'", TextView.class);
    view2131297214 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.esvMain = Utils.findRequiredViewAsType(source, R.id.esv_main, "field 'esvMain'", EasyStatusView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ConfirmOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvName = null;
    target.tvReceiver = null;
    target.tvPhone = null;
    target.tvAddress = null;
    target.llAddress = null;
    target.ivFarm = null;
    target.tvFarmName = null;
    target.llFarm = null;
    target.rvContent = null;
    target.tvOrderTotalPrice = null;
    target.tvSave = null;
    target.esvMain = null;

    view2131296687.setOnClickListener(null);
    view2131296687 = null;
    view2131297214.setOnClickListener(null);
    view2131297214 = null;
  }
}
