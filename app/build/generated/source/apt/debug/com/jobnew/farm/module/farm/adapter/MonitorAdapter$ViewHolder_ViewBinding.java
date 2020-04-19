// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.farm.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MonitorAdapter$ViewHolder_ViewBinding implements Unbinder {
  private MonitorAdapter.ViewHolder target;

  private View view2131297101;

  @UiThread
  public MonitorAdapter$ViewHolder_ViewBinding(final MonitorAdapter.ViewHolder target,
      View source) {
    this.target = target;

    View view;
    target.tvMonitorName = Utils.findRequiredViewAsType(source, R.id.tv_monitor_name, "field 'tvMonitorName'", TextView.class);
    target.ivMonitor = Utils.findRequiredViewAsType(source, R.id.iv_monitor, "field 'ivMonitor'", ImageView.class);
    target.tvMonitorLocaion = Utils.findRequiredViewAsType(source, R.id.tv_monitor_locaion, "field 'tvMonitorLocaion'", TextView.class);
    target.tvMonitorBelong = Utils.findRequiredViewAsType(source, R.id.tv_monitor_belong, "field 'tvMonitorBelong'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_demand, "field 'tvDemand' and method 'onClick'");
    target.tvDemand = Utils.castView(view, R.id.tv_demand, "field 'tvDemand'", TextView.class);
    view2131297101 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MonitorAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvMonitorName = null;
    target.ivMonitor = null;
    target.tvMonitorLocaion = null;
    target.tvMonitorBelong = null;
    target.tvDemand = null;

    view2131297101.setOnClickListener(null);
    view2131297101 = null;
  }
}
