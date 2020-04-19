// Generated code from Butter Knife. Do not modify!
package com.jobnew.farm.module.personal.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jobnew.farm.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LogisticsActivity_ViewBinding implements Unbinder {
  private LogisticsActivity target;

  @UiThread
  public LogisticsActivity_ViewBinding(LogisticsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LogisticsActivity_ViewBinding(LogisticsActivity target, View source) {
    this.target = target;

    target.txtOrderNum = Utils.findRequiredViewAsType(source, R.id.txt_order_num, "field 'txtOrderNum'", TextView.class);
    target.txtOrderTime = Utils.findRequiredViewAsType(source, R.id.txt_order_time, "field 'txtOrderTime'", TextView.class);
    target.ll1 = Utils.findRequiredViewAsType(source, R.id.ll_1, "field 'll1'", LinearLayout.class);
    target.txtOrderCompany = Utils.findRequiredViewAsType(source, R.id.txt_order_company, "field 'txtOrderCompany'", TextView.class);
    target.txtOrderWaybill = Utils.findRequiredViewAsType(source, R.id.txt_order_waybill, "field 'txtOrderWaybill'", TextView.class);
    target.ll2 = Utils.findRequiredViewAsType(source, R.id.ll_2, "field 'll2'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LogisticsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtOrderNum = null;
    target.txtOrderTime = null;
    target.ll1 = null;
    target.txtOrderCompany = null;
    target.txtOrderWaybill = null;
    target.ll2 = null;
  }
}
